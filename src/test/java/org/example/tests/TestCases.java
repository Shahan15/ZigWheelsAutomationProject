package org.example.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class TestCases {

    public static final Logger logger = LogManager.getLogger(TestCases.class);

    @Test
    public void quicktest () {
        logger.info("test test");
    }


}
