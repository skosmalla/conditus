package com.github.skosmalla.conditus.maven.step

import com.github.skosmalla.conditus.ScmUrlUtil
import com.github.skosmalla.conditus.domain.ScmProvider


class PrepareScmForTagStepFactory {

    static PrepareScmStep getInstance(String scmUrl) {
        def scmProvider = ScmUrlUtil.getScmProvider(scmUrl)

        if (scmProvider == ScmProvider.SVN) {
            return new PrepareSvnForTagStep(scmUrl)
        }

    }
}
