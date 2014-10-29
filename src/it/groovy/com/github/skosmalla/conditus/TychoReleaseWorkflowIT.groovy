package com.github.skosmalla.conditus

import org.junit.Test

import java.nio.file.Files
import java.nio.file.Paths

/**
 * @author skosmalla
 * @since 03.07.14
 */
class TychoReleaseWorkflowIT {

    @Test
    void testTychoReleaseWorkflow() {
        Conditus.main("-jsonFile", "./src/it/resources/tycho/release.json")

        assert Files.exists(Paths.get("./target/tycho-project"))
        def httpConnection = (HttpURLConnection) new URL("http://localhost:8000/svn/conditus/tycho-sample-project/tags/1.0.0").openConnection()
        assert httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK
        httpConnection = (HttpURLConnection) new URL("http://localhost:8001/nexus/content/repositories/releases/com/github/skosmalla/tycho/rcp/example/tycho-rcp-example/1.0.0/tycho-rcp-example-1.0.0.pom").openConnection()
        assert httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK
    }


}
