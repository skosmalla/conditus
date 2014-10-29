package com.github.skosmalla.conditus.tycho

import com.github.skosmalla.conditus.ConditusWorkflow
import com.github.skosmalla.conditus.ScmUrlUtil
import com.github.skosmalla.conditus.domain.ProjectInformation
import com.github.skosmalla.conditus.step.ChangeScmInformationStep
import com.github.skosmalla.conditus.step.DeployReleaseStep
import com.github.skosmalla.conditus.step.DeploySnapshotStep
import com.github.skosmalla.conditus.step.PrepareScmForTagStepFactory
import com.github.skosmalla.conditus.step.ScmCheckoutStep
import com.github.skosmalla.conditus.step.ValidateScmInformationStep
import com.github.skosmalla.conditus.step.ValidateStep
import com.github.skosmalla.conditus.tycho.step.ScmCommitTychoStep
import com.github.skosmalla.conditus.tycho.step.ScmTagTychoStep
import com.github.skosmalla.conditus.tycho.step.SetVersionTychoStep

import java.nio.file.Paths

/**
 * Created by skosmalla on 08.06.14.
 */
class TychoReleaseWorkflow implements ConditusWorkflow{

    ProjectInformation projectInformation

    TychoReleaseWorkflow(ProjectInformation projectInformation) {
        this.projectInformation = projectInformation
    }

    @Override
    void execute() {
        new ValidateScmInformationStep().execute()

        def prepareScmStep = PrepareScmForTagStepFactory.getInstance(projectInformation.scmUrl)
        prepareScmStep.execute()

        new SetVersionTychoStep(projectInformation.releaseVersion).execute()
        new ChangeScmInformationStep(Paths.get(projectInformation.checkoutDir + '/pom.xml'),'tags/' + projectInformation.releaseVersion).execute()

        new ValidateStep().execute()
        new ScmCommitTychoStep('preparation for tag').execute()

        new ChangeScmInformationStep(Paths.get(projectInformation.checkoutDir + '/pom.xml'),'trunk').execute()

        new SetVersionTychoStep(projectInformation.nextDevVersion).execute()
        new ChangeScmInformationStep(Paths.get(projectInformation.checkoutDir + '/pom.xml'),'trunk').execute()

        new ScmTagTychoStep(projectInformation.releaseVersion).execute()
        new ScmCommitTychoStep("next dev version").execute()

        new ScmCheckoutStep(ScmUrlUtil.replace(projectInformation.scmUrl, 'tags/' + projectInformation.releaseVersion), 'target/tag').execute()
        new DeployReleaseStep(projectInformation.checkoutDir + '/target/tag').execute()


        new DeploySnapshotStep().execute()

    }
}
