package com.github.skosmalla.conditus.scm.step

import com.github.skosmalla.conditus.CommandLineExecutor
import com.github.skosmalla.conditus.ConditusStep
import com.github.skosmalla.conditus.OsSpecifics


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
