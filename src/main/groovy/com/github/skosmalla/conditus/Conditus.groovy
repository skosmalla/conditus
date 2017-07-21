package com.github.skosmalla.conditus

import com.github.skosmalla.conditus.maven.MavenReleaseWorkflow
import com.github.skosmalla.conditus.scm.step.ScmCheckoutStep


class Conditus {

    static void main(String... args) {
        def cliParser = new CommandLineParser()
        def options = cliParser.parse(args)

        def projectInfoParser = new ProjectInformationParser()
        def projectsInformation = projectInfoParser.parse(options.jsonFile)

        if(options.validateJsonFile) {
            println("Given JSON file is syntactically correct.")
            return
        }

        for (def projectInfo : projectsInformation) {
            new ScmCheckoutStep(projectInfo.scmUrl, projectInfo.checkoutDir).execute()
            CommandLineExecutor.setInstance(new CommandLineExecutor((projectInfo.checkoutDir)))
            def mavenWorkflow = new MavenReleaseWorkflow(projectInfo)
            mavenWorkflow.execute()
        }


    }
}
