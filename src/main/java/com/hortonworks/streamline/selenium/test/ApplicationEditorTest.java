package com.hortonworks.streamline.selenium.test;

import java.util.UUID;

import org.apache.http.util.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.hortonworks.streamline.selenium.page.ApplicationEditorPage;
import com.hortonworks.streamline.selenium.page.BasePage;
import com.hortonworks.streamline.selenium.page.MyapplicationsPage;
import com.hortonworks.streamline.selenium.utils.PropertiesUtil;

public class ApplicationEditorTest extends BaseTest{
        MyapplicationsPage myapp = new MyapplicationsPage();
        ApplicationEditorPage topo = new ApplicationEditorPage();
        BasePage base = new BasePage();
        PropertiesUtil propertyUtils = PropertiesUtil.getInstance();
        WebDriverWait wait = new WebDriverWait(driver,4000);
        String arr[]=UUID.randomUUID().toString().split("-");
        Actions actions = new Actions(driver);
        final static Logger LOG = Logger.getLogger(ApplicationEditorTest.class);

//	Check My Application header
        @Test (priority=1)
        public void draganddrop()
        {
                try
                {
                        myapp.myApplications.click();
                        base.fluentwait();
                        wait.until(ExpectedConditions.visibilityOf(myapp.operations));
                        myapp.operations.click();
                        base.fluentwait();
                        myapp.editIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(topo.kafkaSource));
                        Thread.sleep(2000);
                        System.out.println("1");
//			topo.kafkaSource.g
//			WebElement s= driver.findElement(By.xpath("//svg[@class='topology-graph']"));
//			System.out.println("2");
//			actions.clickAndHold(topo.kafkaSource).build().perform();
//			System.out.println("3");
//			actions.moveToElement(s).build().perform();
//			System.out.println("4");
//			actions.release(s).build().perform();
//			actions.dragAndDropBy(topo.kafkaSource, -51, -70).perform();
                        new Actions(driver).dragAndDropBy(topo.kafkaSource, 199, 279).build().perform();
                        System.out.println("5");
//			new Actions(driver).clickAndHold(topo.kafkaSource).moveByOffset(100,100).release().perform();
//			System.out.println("5");
                }
                catch (Exception e)
                {

                }


//		199.10140991210938, 297.28692626953125
        }














}
