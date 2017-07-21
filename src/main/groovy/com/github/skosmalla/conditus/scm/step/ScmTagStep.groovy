package com.github.skosmalla.conditus.scm.step

import com.github.skosmalla.conditus.CommandLineExecutor
import com.github.skosmalla.conditus.ConditusStep
import com.github.skosmalla.conditus.OsSpecifics


class ScmTagStep implements ConditusStep{

    String tagName

    /**
     * Important: connectionUrl has to be cared for in project's pom.
     *
     * @param tagName
     */
    ScmTagStep(String tagName) {
       this.tagName = tagName
    }

    @Override
    void execute() {
        String[] command = [OsSpecifics.getOsName().commandLinePrefix + "mvn", "-Dtycho.mode=maven","scm:tag", "-Dmessage=\"[maven-scm-plugin] commit tag\"","-Dtag=" + tagName]

       CommandLineExecutor.getInstance().executeCommand(command)
    }
}
