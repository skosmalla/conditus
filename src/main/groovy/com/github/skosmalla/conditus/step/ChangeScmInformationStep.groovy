package com.github.skosmalla.conditus.step

import com.github.skosmalla.conditus.ConditusStep
import com.github.skosmalla.conditus.PomParser
import com.github.skosmalla.conditus.domain.ScmInformation
import com.github.skosmalla.conditus.ScmUrlUtil

import java.nio.file.Path

/**
 * @author skosmalla
 * @since 11.06.14
 */
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
        newScmInfo.connection = ScmUrlUtil.replace(pomParser.scmInformation.connection, replacer)
        newScmInfo.developerConnection = ScmUrlUtil.replace(pomParser.scmInformation.developerConnection, replacer)
        newScmInfo.url = ScmUrlUtil.replace(pomParser.scmInformation.url, replacer)

        pomParser.scmInformation = newScmInfo
    }
}
