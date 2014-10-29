package com.github.skosmalla.conditus.step

import com.github.skosmalla.conditus.ConditusStep
import com.github.skosmalla.conditus.CommandLineExecutor
import com.github.skosmalla.conditus.OsSpecifics

/**
 * @author skosmalla
 * @since 11.06.14
 */
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
