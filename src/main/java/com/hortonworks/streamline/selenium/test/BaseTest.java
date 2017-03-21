package com.hortonworks.streamline.selenium.test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import org.openqa.selenium.WebDriver;

import com.hortonworks.streamline.selenium.utils.DriverManager;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class BaseTest {
        static Logger LOG = Logger.getLogger(BaseTest.class);
        public WebDriver driver;
        public BaseTest()
        {
                try {
                        driver = DriverManager.getDriver();
                }
                catch(Exception e){
                        e.printStackTrace();
                }
        }

        public static void main(String[] args) {
                LOG.debug("This is a debug message");
                myMethod();
                LOG.info("This is an info message");
        }

        private static void myMethod() {
                try {
                        throw new Exception("My Exception");
                } catch (Exception e) {
                        LOG.error("This is an exception", e);
                }
        }

//	{
//        //DOMConfigurator is used to configure logger from xml configuration file
//        DOMConfigurator.configure("src/main/resources/log4j.xml");
//
//        //Log in console in and log file
//        LOG.debug("Log4j appender configuration is successful !!");
//        LOG.info("This is an info message");
//    }
         // get a logger instance
//		public static Logger logger = Logger.getLogger(BaseTest.class);
//
//		public void testLoggerDebug() {
//			logger.debug("Hello.. im in Debug method");
//		}
//
//		public void testLoggerInfo() {
//			logger.info("Hello.. im in Info method");
//		}
//
//		public void testLoggerError() {
//			logger.error("Hello.. im in Error method");
//		}
//
//
//		public static void main(String[] args) {
//			BaseTest example = new BaseTest();
//			example.testLoggerDebug();
//			example.testLoggerInfo();
//			example.testLoggerError();
//		}

        //Create a date object
        Date d1 = new Date(0);
        SimpleDateFormat s =  new SimpleDateFormat ("yyyyMMddhhmmss");

}
