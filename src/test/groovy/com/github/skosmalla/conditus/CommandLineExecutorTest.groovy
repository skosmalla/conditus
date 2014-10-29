package com.github.skosmalla.conditus

import com.github.skosmalla.conditus.exception.StepExecutionException
import org.junit.Test

/**
 * @author skosmalla
 * @since 09.06.14
 */
class CommandLineExecutorTest {

    OsSpecifics os = OsSpecifics.osName


    @Test
    void testRunningProcessCommandSuccessful() {
        def stepProcessor = new CommandLineExecutor("target")
        def processOutput
        if (os == OsSpecifics.WIN) {
            processOutput = stepProcessor.executeCommand("cmd /c dir")

        }

        if (os == OsSpecifics.UNIX) {
            stepProcessor.executeCommand("sh -c ls")
        }
        println(processOutput)
    }

    @Test
    void testRunningProcessCommandSuccessfulInCurrentDirectory() {
        def stepProcessor = new CommandLineExecutor()
        def processOutput
        if (os == OsSpecifics.WIN) {
            processOutput = stepProcessor.executeCommand("cmd /c dir")

        }

        if (os == OsSpecifics.UNIX) {
            stepProcessor.executeCommand("sh -c ls")
        }
        println(processOutput)
    }

    @Test(expected = StepExecutionException.class)
    void testRunningProcessCommandFaulty() {
        def stepProcessor = new CommandLineExecutor("target")
        if (os == OsSpecifics.WIN) {
            stepProcessor.executeCommand("cmd /c ll")
        }
        if (os == OsSpecifics.UNIX) {
            stepProcessor.executeCommand("sh -c ipconfig")
        }
    }
}
