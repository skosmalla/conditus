package com.github.skosmalla.conditus

import com.github.skosmalla.conditus.exception.StepExecutionException

/**
 * @author skosmalla
 * @since 08.06.14.
 */
class CommandLineExecutor {

    static CommandLineExecutor commandLineExecutor;

    public static CommandLineExecutor getInstance(){
        if(commandLineExecutor == null) {
            commandLineExecutor = new CommandLineExecutor()
        }
        commandLineExecutor
    }

    public static void setInstance(CommandLineExecutor commandLineExecutor) {
        CommandLineExecutor.commandLineExecutor = commandLineExecutor
    }

    File workingDirectory

    CommandLineExecutor() {
        this(".")
    }

    CommandLineExecutor(String workingDirectory) {
        this.workingDirectory = new File(workingDirectory)
    }

    String executeCommand(String [] command){
        printCommand(convertArrayToString(command))
        doExecuteCommand(command)
    }


    String executeCommand(String command) {
        printCommand(command)
        doExecuteCommand(command)
    }

    private String convertArrayToString(String[] array) {
        def string = ""
        for(String content : array) {
            string+=content + " "
        }

        string
    }

    private printCommand(String command) {
        println(workingDirectory.getAbsolutePath() + ">" + command)
    }


    private String doExecuteCommand(def command) {
        def sout = new StringBuffer()
        def serr = new StringBuffer()

        def process = command.execute(null, workingDirectory)

        process.consumeProcessOutput(sout, serr)
        process.waitFor()

        if (process.exitValue()) {
            println(sout)
            println(serr)
            throw new StepExecutionException(sout.toString())
        } else {
            def processOutput = sout
            println(processOutput)
            processOutput
        }
    }


}
