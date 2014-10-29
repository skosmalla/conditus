package com.github.skosmalla.conditus

import com.github.skosmalla.conditus.domain.ScmProvider
import com.github.skosmalla.conditus.exception.UnsupportedScmProviderException
import org.junit.Test

/**
 * Created by sandra.kosmalla on 18.02.14.
 */
public class ScmUrlUtilTest {


    @Test
    void parseTrunkUrl() {
        String newUrl = ScmUrlUtil.replace("scm:svn:http://somerepository.com/svn_repo/trunk", "tags/2.3.1")

        assert newUrl == 'scm:svn:http://somerepository.com/svn_repo/tags/2.3.1'
    }

    @Test
    void parseBranchUrl(){
        String newUrl = ScmUrlUtil.replace("scm:svn:http://somerepository.com/svn_repo/branches/2.3.x-Branch", "tags/2.3.1")

        assert newUrl == 'scm:svn:http://somerepository.com/svn_repo/tags/2.3.1'
    }

    @Test
    void parseTagUrl(){
        String newUrl = ScmUrlUtil.replace("scm:svn:http://somerepository.com/svn_repo/tags/2.3.1", "branches/2.3.x-Branch")

        assert newUrl == 'scm:svn:http://somerepository.com/svn_repo/branches/2.3.x-Branch'
    }

    @Test
    void parseAnyUrl(){
        String newUrl = ScmUrlUtil.replace("scm:svn:http://somerepository.com/svn_repo", "branches/2.3.x-Branch")

        assert newUrl == 'scm:svn:http://somerepository.com/svn_repo'

    }

    @Test
    void getSvnProvider (){
        ScmProvider scmProvider = ScmUrlUtil.getScmProvider("scm:svn:http://somerepository.com/svn_repo")

        assert scmProvider == ScmProvider.SVN
    }

    @Test(expected = UnsupportedScmProviderException.class)
    void getUnsupportedScmProvider(){
        ScmUrlUtil.getScmProvider("scm:git:http://somerepository.com/repo")
    }

    @Test
    void getRepositoryUrl(){
        String repositoryUrl = ScmUrlUtil.getRepositoryUrl("scm:svn:http://somerepository.com/svn_repo")

        assert repositoryUrl == "http://somerepository.com/svn_repo"
    }
}
