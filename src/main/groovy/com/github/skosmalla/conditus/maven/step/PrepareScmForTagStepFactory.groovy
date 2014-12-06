package com.github.skosmalla.conditus.maven.step

import com.github.skosmalla.conditus.domain.ScmProvider
import com.github.skosmalla.conditus.ScmUrlUtil

/**
 * @author skosmalla
 * @since 11.06.14
 */
class PrepareScmForTagStepFactory {

    static PrepareScmStep getInstance(String scmUrl) {
        def scmProvider = ScmUrlUtil.getScmProvider(scmUrl)

        if (scmProvider == ScmProvider.SVN) {
            return new PrepareSvnForTagStep(scmUrl)
        }

    }
}
