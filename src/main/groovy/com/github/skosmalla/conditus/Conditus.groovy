package com.github.skosmalla.conditus

import com.github.skosmalla.conditus.step.ScmCheckoutStep
import com.github.skosmalla.conditus.tycho.TychoReleaseWorkflow

/**
 * @author skosmalla
 * @since 03.07.14
 */
class Conditus {

    static void main(String... args) {
        def cliParser = new CommandLineParser()
        def options = cliParser.parse(args)

        def projectInfoParser = new ProjectInformationParser()
        def projectsInformation = projectInfoParser.parse(options.jsonFile)

        for (def projectInfo : projectsInformation) {
            new ScmCheckoutStep(projectInfo.scmUrl, projectInfo.checkoutDir).execute()
            CommandLineExecutor.setInstance(new CommandLineExecutor((projectInfo.checkoutDir)))
            def tychoWorkflow = new TychoReleaseWorkflow(projectInfo)
            tychoWorkflow.execute()
        }


    }
}
