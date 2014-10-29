package com.github.skosmalla.conditus.tycho.step

import com.github.skosmalla.conditus.ConditusStep
import com.github.skosmalla.conditus.OsSpecifics
import com.github.skosmalla.conditus.CommandLineExecutor

/**
 * @author skosmalla
 * @since 11.06.14
 */
class ScmCommitTychoStep implements ConditusStep{

    String commitMessage

    ScmCommitTychoStep(String commitMessage) {
        this.commitMessage = commitMessage
    }

    @Override
    void execute() {
        String[] command = [OsSpecifics.getOsName().commandLinePrefix + "mvn", "-Dtycho.mode=maven", "scm:checkin", "-Dmessage=\"[maven-scm-plugin] " + commitMessage + "\" "]

        CommandLineExecutor.getInstance().executeCommand(command)
    }
}
