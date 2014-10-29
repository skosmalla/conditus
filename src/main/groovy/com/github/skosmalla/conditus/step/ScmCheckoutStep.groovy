package com.github.skosmalla.conditus.step

import com.github.skosmalla.conditus.ConditusStep
import com.github.skosmalla.conditus.OsSpecifics
import com.github.skosmalla.conditus.CommandLineExecutor

/**
 * @author skosmalla
 * @since 11.06.14
 */
class ScmCheckoutStep implements ConditusStep{
    String scmUrl
    String checkoutDir

    ScmCheckoutStep(String scmUrl, String checkoutDir) {
        this.scmUrl = scmUrl
        this.checkoutDir = checkoutDir
    }

    @Override
    void execute() {
        def command = OsSpecifics.getOsName().commandLinePrefix + "mvn scm:checkout -DconnectionUrl=" + scmUrl + " -DcheckoutDirectory=" + checkoutDir

        CommandLineExecutor.getInstance().executeCommand(command)
    }
}
