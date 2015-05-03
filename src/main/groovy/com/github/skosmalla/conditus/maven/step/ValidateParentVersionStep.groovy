package com.github.skosmalla.conditus.maven.step

import com.github.skosmalla.conditus.ConditusStep
import com.github.skosmalla.conditus.PomParser
import com.github.skosmalla.conditus.exception.ConditusValidationException

import java.nio.file.Path

class ValidateParentVersionStep implements ConditusStep{

    def pom

    ValidateParentVersionStep(Path pathToPom) {
        this.pom = pathToPom
    }

    @Override
    void execute() {
        PomParser pomParser = new PomParser(pom)

        if(pomParser.parentVersion != null && pomParser.parentVersion.contains("SNAPSHOT")) {
            throw new ConditusValidationException("Parent version is a SNAPSHOT version")
        }

    }
}
