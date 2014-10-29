package com.github.skosmalla.conditus

import com.github.skosmalla.conditus.domain.ProjectInformation
import groovy.json.JsonSlurper

/**
 * @author skosmalla
 * @since 03.07.14
 */
class ProjectInformationParser {

    List<ProjectInformation> parse(String jsonUrl) {
        def bufferedReader = createBufferedReader(jsonUrl)

        def jsonReader = new JsonSlurper()
        def result = jsonReader.parse(bufferedReader)

        def projectInformation = new LinkedList();
        if (result instanceof Map) {
            projectInformation.add(parseOneReleaseArtifact(result))
        } else {
            result.each { item ->
                projectInformation.add(parseOneReleaseArtifact(item))
            }
        }
        return projectInformation
    }

    private BufferedReader createBufferedReader(String jsonUrl) {
        def fileReader = new FileReader(jsonUrl)
        def bufferedReader = new BufferedReader(fileReader)
        bufferedReader
    }

    private ProjectInformation parseOneReleaseArtifact(def item) {
        def projectInformation = new ProjectInformation()
        projectInformation.scmUrl = item.scmUrl
        projectInformation.checkoutDir = item.checkoutDir
        projectInformation.releaseVersion = item.releaseVersion
        projectInformation.nextDevVersion = item.nextDevVersion
        projectInformation
    }
}
