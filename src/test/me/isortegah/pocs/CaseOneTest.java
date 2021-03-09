package me.isortegah.pocs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class CaseOneTest {

    private static final Logger logger = LogManager.getLogger(CaseOneTest.class);

    @Test
    public void stepOne(){
        String message = "Hello there!";
        logger.trace("Hello there trace!");
        logger.debug("Hello there debug!");
        logger.info("Hello there info!");
        logger.warn("Hello there warn!");
        logger.error("Hello there error!");
        logger.fatal("Hello there fatal!");
    }
}
