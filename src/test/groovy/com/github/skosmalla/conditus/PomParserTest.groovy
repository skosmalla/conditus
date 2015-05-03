package com.github.skosmalla.conditus

import com.github.skosmalla.conditus.domain.ScmInformation
import org.junit.Test

import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

class PomParserTest {


    @Test
    void getScmInformation() {
        PomParser pomParser = new PomParser(Paths.get('./src/test/resources/pom-scm-test.xml'))

        assert pomParser.getScmInformation() != null
        assert pomParser.getScmInformation().developerConnection == 'scm:svn:https://somerepository.com/svn_repo/trunk'
        assert pomParser.getScmInformation().connection == 'scm:svn:http://somerepository.com/svn_repo/trunk'
        assert pomParser.getScmInformation().url == 'http://somerepository.com/view.cvs'
    }

    @Test
    void setScmInformation() {
        ScmInformation scmInformation = new ScmInformation()
        scmInformation.connection = 'scm:svn:http://somerepository.com/svn_repo/tags/2.1'
        scmInformation.developerConnection = 'scm:svn:https://somerepository.com/svn_repo/tags/2.1'

        Files.copy(Paths.get('./src/test/resources/pom-scm-test.xml'), Paths.get('./target/pom-scm-test.xml'), StandardCopyOption.REPLACE_EXISTING)

        PomParser pomParser = new PomParser(Paths.get('./target/pom-scm-test.xml'))
        pomParser.setScmInformation(scmInformation)

        def project =  new XmlSlurper().parseText(Paths.get('./target/pom-scm-test.xml').toFile().text)

        assert project.scm.connection == scmInformation.connection
        assert project.scm.developerConnection == scmInformation.developerConnection
    }

    @Test
    void getArtifactVersion(){
        PomParser pomParser = new PomParser(Paths.get('./src/test/resources/pom-scm-test.xml'))

        assert pomParser.getArtifactVersion() == '1.0.0-SNAPSHOT'
    }

    @Test
    void getParentVersion(){
        PomParser pomParser = new PomParser(Paths.get('./src/test/resources/pom-parent-version.xml'))

        assert pomParser.getParentVersion() == '2.0.0-SNAPSHOT'
    }

    @Test
    void getParentVersion_noParentExist(){
        PomParser pomParser = new PomParser(Paths.get('./src/test/resources/pom-scm-test.xml'))

        assert pomParser.getParentVersion() == ""
    }
}
