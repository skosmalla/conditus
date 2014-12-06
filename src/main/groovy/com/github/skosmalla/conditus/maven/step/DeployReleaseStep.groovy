package com.github.skosmalla.conditus.maven.step

import com.github.skosmalla.conditus.ConditusStep
import com.github.skosmalla.conditus.CommandLineExecutor
import com.github.skosmalla.conditus.OsSpecifics

/**
 * @author skosmalla
 * @since 11.06.14
 */
class DeployReleaseStep implements ConditusStep {
    String workingDir

    DeployReleaseStep(String workingDir) {
        this.workingDir = workingDir
    }

    @Override
    void execute() {
//        def command = OsSpecifics.getOsName().commandLinePrefix + "mvn clean deploy -DskipTests"
        String[] command = [OsSpecifics.getOsName().commandLinePrefix + "mvn", "clean", "deploy", "-DskipTests"]


        new CommandLineExecutor(workingDir).executeCommand(command)
    }
}
