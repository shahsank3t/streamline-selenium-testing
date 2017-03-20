package com.fsi.utils;

import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

import com.fsi.utils.PropertiesUtil;
import com.fsi.utils.UserProperty;

public class DriverManager {

public static WebDriver driver ;
private static String OS = null;

	public static WebDriver getDriver() {
		PropertiesUtil propertyUtils = PropertiesUtil.getInstance();
		
//		 read property and create driver at application level;
//		if(driver == null) {
//			
			if (isUbuntu()) 
			{
				// read property and create driver at application level for linux;

//				For chrome
				if (driver == null) {
					URL url = Thread.currentThread().getContextClassLoader().getResource(propertyUtils.getProperty(UserProperty.UBUNTU_CHROME_DRIVER_PATH, "driver/chromedriver"));
					System.setProperty("webdriver.chrome.driver", url.getPath());
					driver = new ChromeDriver();
					driver.manage().window().maximize();
					driver.get(propertyUtils.getProperty(UserProperty.APP_HOST_URL, "http://172.22.125.42:8088"));
//				For Firefox
//				if (driver ==null) {	
//					URL url = Thread.currentThread().getContextClassLoader().getResource(propertyUtils.getProperty(UserProperty.UBUNTU_GECKODRIVER_PATH, "/home/gargeyee/selenium/workspace/streams/src/main/java/driver/geckodriver"));
//					System.setProperty("webdriver.gecko.driver",url.getPath());
//					WebDriver driver = new FirefoxDriver();
//					WebDriver driver = new FirefoxDriver();
//					driver.manage().window().maximize();
//					driver.get(propertyUtils.getProperty(UserProperty.APP_HOST_URL, "http://172.22.125.42:8088"));
				}	
				
			}else
				{
				// read property and create driver at application level for Mac;
				if (driver == null) {
					URL macurl = Thread.currentThread().getContextClassLoader().getResource(propertyUtils.getProperty(UserProperty.MAC_CHROME_DRIVER_PATH, "driver/MacChromeDriver/chromedriver"));
					System.setProperty("webdriver.chrome.driver", macurl.getPath());
					ChromeOptions opts = new ChromeOptions();
					opts.addArguments("--no-sandbox");
					driver = new ChromeDriver(opts);
					driver.manage().window().maximize();
					driver.get(propertyUtils.getProperty(UserProperty.APP_HOST_URL, "http://172.22.125.42:8088"));
				}
			}
			return driver;
	}
		
			public static String getOsName() 
			{
				if (OS == null) {
					OS = System.getProperty("os.name");
				}
				System.out.println(OS);
				return OS;
			}

			public static boolean isUbuntu()
			{
				return getOsName().startsWith("Linux");
			}

			public static boolean isMac()
			{
				return getOsName().startsWith("Mac");
			}
			
	}
