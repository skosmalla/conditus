package com.github.skosmalla.conditus.maven.step

import com.github.skosmalla.conditus.ConditusStep
import com.github.skosmalla.conditus.PomParser
import com.github.skosmalla.conditus.ScmUrl
import com.github.skosmalla.conditus.domain.ScmInformation

import java.nio.file.Path


class ChangeScmInformationStep implements ConditusStep{


    Path pathToPom

    String replacer

    ChangeScmInformationStep(Path pathToPom, String replacer) {
        this.replacer = replacer
        this.pathToPom = pathToPom
    }

    @Override
    void execute() {
        def newScmInfo = new ScmInformation()

        PomParser pomParser = new PomParser(pathToPom)

        ScmUrl connectionScmUrl = new ScmUrl(pomParser.scmInformation.connection)
        connectionScmUrl.replace(replacer)
        newScmInfo.connection = connectionScmUrl.toString()

        ScmUrl developerConnectionScmUrl = new ScmUrl(pomParser.scmInformation.developerConnection)
        developerConnectionScmUrl.replace(replacer)
        newScmInfo.developerConnection = developerConnectionScmUrl.toString()

        ScmUrl scmInformationScmUrl = new ScmUrl(pomParser.scmInformation.url)
        scmInformationScmUrl.replace(replacer)
        newScmInfo.url = scmInformationScmUrl.toString()

        pomParser.scmInformation = newScmInfo
    }
}
