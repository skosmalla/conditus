package com.github.skosmalla.conditus

import com.github.skosmalla.conditus.domain.ScmProvider
import com.github.skosmalla.conditus.exception.UnsupportedScmProviderException

/**
 * @author skosmalla
 * @since 11.06.14
 */
class ScmUrlUtil {

    static String replace(String url, String replacement) {
        String newUrl = url
        if (url.contains('trunk')) {
            newUrl = replaceTrunk(url, replacement)
        } else if (url.contains('branches')) {
            newUrl = replaceBranch(url, replacement)
        } else if (url.contains('tags')) {
            newUrl = replaceTag(url, replacement)
        }
        newUrl
    }

    private static def replaceTag(String url, String replace) {
        url.replaceFirst("tags.*", replace)
    }

    private static def replaceBranch(String url, String replace) {
        url.replaceFirst("branches.*", replace)
    }

    private static def replaceTrunk(String url, String replace) {
        url.replaceFirst("trunk", replace)
    }

    static ScmProvider getScmProvider(String scmUrl) {
        if(scmUrl.contains('svn')) {
            return ScmProvider.SVN
        }
        throw new UnsupportedScmProviderException(scmUrl)
    }

    static String getRepositoryUrl(String scmUrl) {
        def strings = scmUrl.split(":", 3)

        strings[2]
    }
}
