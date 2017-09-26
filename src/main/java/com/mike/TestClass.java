package com.mike;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestClass {
	    private static Logger log = LoggerFactory.getLogger(TestClass.class);  
	    public void log(){
	       log.trace("Trace info");
	       log.debug("Debug info");
	       log.info("Info info");
	       log.warn("Warn info");
	       log.error("Error info");
	    }
	    public static void main(String[] args) {
	       TestClass test = new TestClass();
	       test.log();
	    }
}
