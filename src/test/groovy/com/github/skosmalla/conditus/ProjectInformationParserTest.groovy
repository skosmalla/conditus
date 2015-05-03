package com.github.skosmalla.conditus

import org.junit.Test

/**
 * @author skosmalla
 * @since 03.07.14
 */
class ProjectInformationParserTest {

    def projectInfoParser = new ProjectInformationParser()

    @Test
    void testParseJsonWithOneProjectItem(){
        def projectItems = projectInfoParser.parse("src/test/resources/projectInfo.json")

        assert projectItems.size() == 1
        assert projectItems[0].scmUrl == "scm:svn:http://localhost:8080/svn/conditus/tycho/trunk"
    }

    @Test
    void testParseJsonWithManyProjectItems(){
        def projectItems = projectInfoParser.parse("src/test/resources/projectsInfo.json")


        assert projectItems.size() == 2
        assert projectItems[0].scmUrl == "scm:svn:http://localhost:8080/svn/conditus/tycho/trunk"
    }
}
