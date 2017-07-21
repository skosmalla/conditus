package com.github.skosmalla.conditus

import com.github.skosmalla.conditus.domain.ScmProvider
import com.github.skosmalla.conditus.exception.UnsupportedScmProviderException

/**
 * @author skosmalla
 * @since 11.06.14
 */
class ScmUrl {

    String url;

    ScmUrl(String url){
        this.url = url;
    }

    def replace(String replacement) {
        String newUrl = url
        if (url.contains('trunk')) {
            newUrl = replaceTrunk(replacement)
        } else if (url.contains('branches')) {
            newUrl = replaceBranch(replacement)
        } else if (url.contains('tags')) {
            newUrl = replaceTag(replacement)
        }
        url = newUrl
    }

    private def replaceTag(String replace) {
        url.replaceFirst("tags.*", replace)
    }

    private def replaceBranch(String replace) {
        url.replaceFirst("branches.*", replace)
    }

    private def replaceTrunk(String replace) {
        url.replaceFirst("trunk", replace)
    }

    ScmProvider getScmProvider() {
        if(url.contains('svn')) {
            return ScmProvider.SVN
        }
        throw new UnsupportedScmProviderException(url)
    }

    String extractRepositoryUrl() {
        def strings = url.split(":", 3)

        strings[2]
    }


    @Override
    String toString() {
        url
    }
}
