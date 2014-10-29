package com.github.skosmalla.conditus

import org.junit.Test

/**
 * @author skosmalla
 * @since 09.06.14
 */
class OsSpecificsTest {

    @Test
    void test() {
        OsSpecifics osSpecifics = OsSpecifics.getOsName()

        assert osSpecifics == OsSpecifics.UNIX
    }
}
