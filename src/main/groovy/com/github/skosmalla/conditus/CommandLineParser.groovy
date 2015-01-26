package com.github.skosmalla.conditus

/**
 * @author skosmalla
 * @since 03.07.14
 */
class CommandLineParser {

    def cli

    CommandLineParser() {
        cli = new CliBuilder(usage : "groovy Conditus -jsonFile <file>")
        cli.jsonFile(args:1, argName:'file', required:true, 'use given file for release build')
        cli.validateJsonFile(args:0, required: false, 'validate given JSON file for syntax errors')
    }

    OptionAccessor parse(def args) {
        return cli.parse(args)
    }
}
