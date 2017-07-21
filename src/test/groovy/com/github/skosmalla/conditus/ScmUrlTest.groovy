package com.github.skosmalla.conditus

import com.github.skosmalla.conditus.domain.ScmProvider
import com.github.skosmalla.conditus.exception.UnsupportedScmProviderException
import org.junit.Test

/**
 * Created by sandra.kosmalla on 18.02.14.
 */
public class ScmUrlTest {


    @Test
    void parseTrunkUrl() {
        ScmUrl scmUrl = new ScmUrl("scm:svn:http://somerepository.com/svn_repo/trunk")
        scmUrl.replace("tags/2.3.1")
        String newUrl = scmUrl.toString();

        assert newUrl == 'scm:svn:http://somerepository.com/svn_repo/tags/2.3.1'
    }

    @Test
    void parseBranchUrl(){
        ScmUrl scmUrl = new ScmUrl("scm:svn:http://somerepository.com/svn_repo/branches/2.3.x-Branch")
        scmUrl.replace("tags/2.3.1")
        String newUrl = scmUrl.toString()

        assert newUrl == 'scm:svn:http://somerepository.com/svn_repo/tags/2.3.1'
    }

    @Test
    void parseTagUrl(){
        ScmUrl scmUrl = new ScmUrl("scm:svn:http://somerepository.com/svn_repo/tags/2.3.1")
        scmUrl.replace("branches/2.3.x-Branch")
        String newUrl = scmUrl.toString()

        assert newUrl == 'scm:svn:http://somerepository.com/svn_repo/branches/2.3.x-Branch'
    }

    @Test
    void parseAnyUrl(){
        ScmUrl scmUrl = new ScmUrl("scm:svn:http://somerepository.com/svn_repo")
        scmUrl.replace("branches/2.3.x-Branch")
        String newUrl = scmUrl.toString()

        assert newUrl == 'scm:svn:http://somerepository.com/svn_repo'

    }

    @Test
    void getSvnProvider (){
        ScmUrl scmUrl = new ScmUrl("scm:svn:http://somerepository.com/svn_repo")
        ScmProvider scmProvider = scmUrl.getScmProvider()

        assert scmProvider == ScmProvider.SVN
    }

    @Test(expected = UnsupportedScmProviderException.class)
    void getUnsupportedScmProvider(){
        ScmUrl scmUrl = new ScmUrl("scm:git:http://somerepository.com/repo")
        scmUrl.getScmProvider()
    }

    @Test
    void getRepositoryUrl(){
        ScmUrl scmUrl = new ScmUrl("scm:svn:http://somerepository.com/svn_repo")
        String repositoryUrl = scmUrl.extractRepositoryUrl()

        assert repositoryUrl == "http://somerepository.com/svn_repo"
    }
}
