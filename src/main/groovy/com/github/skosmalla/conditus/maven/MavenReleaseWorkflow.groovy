package com.github.skosmalla.conditus.maven

import com.github.skosmalla.conditus.ConditusWorkflow
import com.github.skosmalla.conditus.ScmUrlUtil
import com.github.skosmalla.conditus.domain.ProjectInformation
import com.github.skosmalla.conditus.maven.step.*

import java.nio.file.Paths


class MavenReleaseWorkflow implements ConditusWorkflow{

    ProjectInformation projectInformation

    MavenReleaseWorkflow(ProjectInformation projectInformation) {
        this.projectInformation = projectInformation
    }

    @Override
    void execute() {

        new ValidateParentVersionStep(Paths.get(projectInformation.checkoutDir + '/pom.xml')).execute()
        new ValidateScmInformationStep().execute()

        def prepareScmStep = PrepareScmForTagStepFactory.getInstance(projectInformation.scmUrl)
        prepareScmStep.execute()

        new SetVersionStep(projectInformation.releaseVersion).execute()
        new ChangeScmInformationStep(Paths.get(projectInformation.checkoutDir + '/pom.xml'),'tags/' + projectInformation.releaseVersion).execute()

        new ValidateStep().execute()
        new ScmCommitStep('preparation for tag').execute()

        new ChangeScmInformationStep(Paths.get(projectInformation.checkoutDir + '/pom.xml'),'trunk').execute()

        new SetVersionStep(projectInformation.nextDevVersion).execute()
        new ChangeScmInformationStep(Paths.get(projectInformation.checkoutDir + '/pom.xml'),'trunk').execute()

        new ScmTagStep(projectInformation.releaseVersion).execute()
        new ScmCommitStep("next dev version").execute()

        new ScmCheckoutStep(ScmUrlUtil.replace(projectInformation.scmUrl, 'tags/' + projectInformation.releaseVersion), 'target/tag').execute()
        new DeployReleaseStep(projectInformation.checkoutDir + '/target/tag').execute()


        new DeploySnapshotStep().execute()

    }
}
