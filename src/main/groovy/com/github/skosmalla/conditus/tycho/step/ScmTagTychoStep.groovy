package com.github.skosmalla.conditus.tycho.step

import com.github.skosmalla.conditus.ConditusStep
import com.github.skosmalla.conditus.OsSpecifics
import com.github.skosmalla.conditus.CommandLineExecutor

/**
 * @author skosmalla
 * @since 11.06.14
 */
class ScmTagTychoStep implements ConditusStep{

    String tagName

    /**
     * Important: connectionUrl has to be cared for in project's pom.
     *
     * @param tagName
     */
    ScmTagTychoStep(String tagName) {
       this.tagName = tagName
    }

    @Override
    void execute() {
        String[] command = [OsSpecifics.getOsName().commandLinePrefix + "mvn", "-Dtycho.mode=maven","scm:tag", "-Dmessage=\"[maven-scm-plugin] commit tag\"","-Dtag=" + tagName]

       CommandLineExecutor.getInstance().executeCommand(command)
    }
}
