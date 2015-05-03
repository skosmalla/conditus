package com.github.skosmalla.conditus;

import org.junit.Test;


class ConditusTest {

        @Test
        void testValidateJsonFileFlag(){
            Conditus.main('-jsonFile', './src/test/resources/projectInfo.json', '-validateJsonFile')
        }

    //TODO negative test
}
