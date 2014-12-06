package com.github.skosmalla.conditus.maven.step

import com.github.skosmalla.conditus.ConditusStep

/**
 * @author skosmalla
 * @since 11.06.14
 */
abstract class PrepareScmStep implements ConditusStep {
    String scmUrl

    PrepareScmStep(String scmUrl) {
        this.scmUrl = scmUrl
    }

}
