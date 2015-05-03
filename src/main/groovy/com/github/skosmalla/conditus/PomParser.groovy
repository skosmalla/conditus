package com.github.skosmalla.conditus

import com.github.skosmalla.conditus.domain.ScmInformation
import groovy.util.slurpersupport.GPathResult
import groovy.xml.StreamingMarkupBuilder
import groovy.xml.XmlUtil

import java.nio.file.Path

/**
 * @author skosmalla
 * @since 11.06.14
 */
class PomParser {

    def project

    Path path


    PomParser (String xmlAsString) {
        project = parse(xmlAsString)
    }

    PomParser(Path path) {
        this.path = path
        project = parse(path.toFile().text)
    }

    private def parse(String xmlAsString) {
        new XmlSlurper().parseText(xmlAsString)
    }

    ScmInformation getScmInformation() {
        ScmInformation scmInformation = new ScmInformation()

        scmInformation.connection = project.scm.connection
        scmInformation.developerConnection = project.scm.developerConnection
        scmInformation.url = project.scm.url

        scmInformation
    }

    String getArtifactVersion(){
        project.version
    }

    void setScmInformation(ScmInformation scmInformation) {
        if (scmInformation == null) {
            return
        }

        if (scmInformation.developerConnection != null) {
            project.scm.developerConnection = scmInformation.developerConnection
        }

        if (scmInformation.connection != null) {
            project.scm.connection = scmInformation.connection
        }

        if (scmInformation.url != null) {
            project.scm.url = scmInformation.url
        }

        def writer = new PrintWriter(path.toFile())
        writer.write(convertToString(project))
        writer.flush()
        writer.close()
    }

    private String convertToString(GPathResult doc) {
        def defaultNamespace = doc.lookupNamespace('')

        if (defaultNamespace) {
            def docWithNamespace = {
                mkp.declareNamespace("": defaultNamespace)
                out << doc
            }
            return XmlUtil.serialize(new StreamingMarkupBuilder().bind(docWithNamespace))
        } else {
            return XmlUtil.serialize(doc as GPathResult)
        }
    }

    String getParentVersion() {
        project.parent.version
    }
}
