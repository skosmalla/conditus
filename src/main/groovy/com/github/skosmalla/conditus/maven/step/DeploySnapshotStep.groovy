package com.github.skosmalla.conditus.maven.step

import com.github.skosmalla.conditus.CommandLineExecutor
import com.github.skosmalla.conditus.ConditusStep
import com.github.skosmalla.conditus.OsSpecifics


class DeploySnapshotStep implements ConditusStep {

    String workingDirectory

    DeploySnapshotStep() {
    }

    DeploySnapshotStep(String workingDirectory) {
        this.workingDirectory = workingDirectory
    }

    @Override
    void execute() {
        def command = OsSpecifics.getOsName().commandLinePrefix + "mvn clean deploy -DskipTests"

        if(workingDirectory == null) {
            CommandLineExecutor.getInstance().executeCommand(command)
        } else {
            new CommandLineExecutor(workingDirectory).executeCommand(command)
        }
    }
}
