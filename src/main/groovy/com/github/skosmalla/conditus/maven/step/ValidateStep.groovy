package com.github.skosmalla.conditus.maven.step

import com.github.skosmalla.conditus.CommandLineExecutor
import com.github.skosmalla.conditus.ConditusStep
import com.github.skosmalla.conditus.OsSpecifics

/**
 * @author skosmalla
 * @since 07.07.14
 */
class ValidateStep implements ConditusStep {
    @Override
    void execute() {
        def command = OsSpecifics.osName.commandLinePrefix + "mvn clean install"

        CommandLineExecutor.instance.executeCommand(command)
    }
}