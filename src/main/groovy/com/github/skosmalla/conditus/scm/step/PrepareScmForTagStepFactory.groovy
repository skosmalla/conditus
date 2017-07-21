package com.github.skosmalla.conditus.scm.step

import com.github.skosmalla.conditus.ScmUrl
import com.github.skosmalla.conditus.domain.ScmProvider


class PrepareScmForTagStepFactory {

    static PrepareScmStep getInstance(String scmUrlAsString) {
        ScmUrl scmUrl = new ScmUrl(scmUrlAsString)
        def scmProvider = scmUrl.getScmProvider()

        if (scmProvider == ScmProvider.SVN) {
            return new PrepareSvnForTagStep(scmUrl)
        }

    }
}
