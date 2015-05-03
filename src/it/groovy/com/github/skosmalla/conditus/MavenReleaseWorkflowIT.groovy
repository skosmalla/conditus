package com.github.skosmalla.conditus

import com.github.skosmalla.conditus.exception.ConditusValidationException
import org.junit.Test

import java.nio.file.Files
import java.nio.file.Paths


class MavenReleaseWorkflowIT {


    @Test
    void releaseVariant(){
        Conditus.main("-jsonFile", "./src/it/resources/maven/release.json")

        assert Files.exists(Paths.get("./target/maven-project"))
        def httpConnection = (HttpURLConnection) new URL("http://localhost:8000/svn/conditus/maven-sample-project/tags/1.0.0").openConnection()
        assert httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK
        httpConnection = (HttpURLConnection) new URL("http://localhost:8001/nexus/content/repositories/releases/com/github/skosmalla/conditus/maven-sample-project/1.0.0/maven-sample-project-1.0.0.pom").openConnection()
        assert httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK
    }

    @Test (expected = ConditusValidationException.class)
    void parentDependencyHasSnapshotVersion(){
        Conditus.main("-jsonFile", "./src/it/resources/maven/release-parent-snapshot.json")


    }
}
