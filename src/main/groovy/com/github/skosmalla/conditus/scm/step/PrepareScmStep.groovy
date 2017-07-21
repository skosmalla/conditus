package com.github.skosmalla.conditus.scm.step

import com.github.skosmalla.conditus.ConditusStep
import com.github.skosmalla.conditus.ScmUrl

/**
 * @author skosmalla
 * @since 11.06.14
 */
abstract class PrepareScmStep implements ConditusStep {
    ScmUrl scmUrl

    PrepareScmStep(ScmUrl scmUrl) {
        this.scmUrl = scmUrl
    }

}
