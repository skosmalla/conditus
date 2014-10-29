package com.github.skosmalla.conditus

import org.junit.Test

/**
 * @author skosmalla
 * @since 03.07.14
 */
class CommandLineParserTest {

    @Test
    void parseCliWithRequiredArgs() {
        def parser = new CommandLineParser()
        def args = ['-jsonFile', './src/test/resources/projectInfo.json']
        def options = parser.parse(args)

        assert options
        assert options.jsonFile == './src/test/resources/projectInfo.json'
    }
}

