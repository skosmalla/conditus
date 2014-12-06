package com.github.skosmalla.conditus.maven.step

import com.github.skosmalla.conditus.ConditusStep
import com.github.skosmalla.conditus.CommandLineExecutor
import com.github.skosmalla.conditus.OsSpecifics

/**
 * @author skosmalla
 * @since 11.06.14
 */
class SetVersionStep implements ConditusStep{
    String newVersion

    SetVersionStep(String newVersion) {
        this.newVersion = newVersion
    }

    @Override
    void execute() {
        def command = OsSpecifics.getOsName().commandLinePrefix + "mvn -Dtycho.mode=maven org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=" + newVersion

        CommandLineExecutor.getInstance().executeCommand(command)
    }
}
