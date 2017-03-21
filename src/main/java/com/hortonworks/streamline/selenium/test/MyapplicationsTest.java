package com.hortonworks.streamline.selenium.test;

import java.util.UUID;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.log4testng.Logger;

import com.hortonworks.streamline.selenium.page.BasePage;
import com.hortonworks.streamline.selenium.page.MyapplicationsPage;
import com.hortonworks.streamline.selenium.test.BaseTest;
import com.hortonworks.streamline.selenium.test.MyapplicationsTest;
import com.hortonworks.streamline.selenium.utils.PropertiesUtil;
import com.hortonworks.streamline.selenium.utils.UserProperty;

import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class MyapplicationsTest extends BaseTest{

        MyapplicationsPage myapp = new MyapplicationsPage();
        BasePage base = new BasePage();
        PropertiesUtil propertyUtils = PropertiesUtil.getInstance();
        WebDriverWait wait = new WebDriverWait(driver,4000);
        String arr[]=UUID.randomUUID().toString().split("-");
        Actions actions = new Actions(driver);
        final static Logger LOG = Logger.getLogger(MyapplicationsTest.class);

//	Check My Applications header
        @Test (priority=1)
        public void myappheader()
        {
                try
                {
                        Thread.sleep(3000);
                        myapp.myApplications.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.appHeader));
                        String header=myapp.appHeader.getText();
                        Assert.assertEquals("My Applications", header);
                        LOG.info("My Applications header displayed");
                        Thread.sleep(2000);
                }
                catch(Exception e)
                {
                        LOG.error("1.My Applications header not missing");
                        Assert.fail();
                }
        }
// Navigate from my application page to model registry page
        @Test (priority=2)
         public void myappToRegistryNavigate()
        {
                try
                {
                        myapp.modelRegistry.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.modelHeader));
                        String mr=myapp.modelHeader.getText();
                        Assert.assertEquals("Model Registry", mr);
                        Thread.sleep(2000);
                        LOG.info("Successfully navigated to Model Registry page");
                }
                catch (Exception e)
                {
                        LOG.error("2.Navigation to model registry failed");
                        Assert.fail();
                }
        }
// Navigate from Model Registry page to My Applications page
        @Test (priority=3)
         public void registryToMyAppNavigate()
         {
                try
                {
                        myapp.myApplications.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.appHeader));
                        String ma=myapp.appHeader.getText();
                        Assert.assertEquals("My Applications", ma);
                        Thread.sleep(2000);
                        LOG.info("Successfully navigated to My Applications page");
                }
                catch (Exception e)
                {
                        LOG.error("3.Navigation to My Applications failed");
                        Assert.fail();
                }
         }
// Check configs available while adding new application
        @Test(priority=4)
        public void checkAddApplicationConfigs()
        {
                try
                {
                        myapp.addApplication();
                        if ((myapp.nameLabel.getText().contains("NAME"))&&(myapp.selectEnvLabel.getText().contains("ENVIRONMENT")))
                        {
                                Thread.sleep(1000);
                                myapp.crossSign.click();
                                LOG.info("Add new application shows configs as Name and Environment");
                        }
                }
                catch (Exception e)
                {
                        LOG.error("4.Add new application form shows no config");
                }
        }
//Check configs available while importing application
        @Test(priority=5)
        public void checkImportConfigs()
        {
                try
                {
                        myapp.importApplication();
                        if ((myapp.selectJsonLabel.getText().contains("SELECT JSON FILE"))&&(myapp.topologyNameLabel.getText().contains("TOPOLOGY NAME"))&&(myapp.environmentLabel.getText().contains("ENVIRONMENT")))
                        {
                                Thread.sleep(1000);
                                myapp.crossSign.click();
                                LOG.info("Import application shows configs as SELECT JSON FILE, TOPOLOGY NAME and ENVIRONMENT");
                        }
                }
                catch (Exception e)
                {
                        LOG.error("5.Import application form shows no config");
                }
        }
//Check add application operation by keeping name field blank
        @Test(priority=6)
        public void addNameBlank()
        {
                try
                {
                        myapp.addApplication();
                        base.fluentwait();
                        myapp.selectEnvironment();
                        myapp.okBtn.click();
                        boolean isdisplay = myapp.applicationForm.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        myapp.crossSign.click(); //close the application form
                        LOG.info("Blank name not allowed");
                }
                catch (Exception e)
                {
                        LOG.error("6.Application added successfully by keeping name field blank");
                        Assert.fail();
                }
        }
//Check add application operation by keeping environment field blank
        @Test(priority=7)
        public void addEnvironmentBlank()
        {
                try
                {
                        myapp.addApplication();
                        base.fluentwait();
                        myapp.name.sendKeys("app"+ arr[0]);
                        myapp.okBtn.click();
                        boolean isdisplay = myapp.applicationForm.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        myapp.crossSign.click(); //close the application form
                        LOG.info("Blank name not allowed");
                }
                catch (Exception e)
                {
                        LOG.error("7.Application added successfully by keeping environment field blank");
                        Assert.fail();
                }
        }
//	Add new topology successfully and verify success notification message
        @Test (priority=8)
        public void addApplicationSuccess()
        {
                try
                {
                        myapp.addApplication();
                        base.fluentwait();
                        myapp.name.sendKeys("app_1"); //If name changed then need to update new name for search, clone and export cases
                        myapp.selectEnvironment();
                        myapp.okBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.successMsg));
                        String success=myapp.successMsg.getText();
                        Thread.sleep(2000);
                        Assert.assertEquals(success, "Topology added successfully");
                        myapp.myApplications.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.confirmBox));
                        myapp.confirmOkBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.appHeader));
                        LOG.info("Topology added successfully");
                }
                catch(Exception e)
                {
                        LOG.error("8.Topology creation failed");
                        Assert.fail();
                }
        }
//Check validation for same name application
        @Test (priority=9)
        public void addWithSameName()
        {
                try
                {
                        myapp.addApplication();
                        base.fluentwait();
                        myapp.name.sendKeys("app_1");
                        base.fluentwait();
                        myapp.selectEnvironment();
                        myapp.okBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.errorMsg));
                        String error=myapp.errorMsg.getText();
                        Thread.sleep(3000);
                        Assert.assertEquals(error, "Application with same name already exists. Please choose a unique Application Name");
                        LOG.info("Topology added operation failed");
                        myapp.cancelError.click();
                        Thread.sleep(1000);
                        myapp.crossSign.click();
                }
                catch (Exception e)
                {
                        LOG.error("9.Topology added with same name application", e);
                        Assert.fail();
                }
        }
//Check validation for special characters in name field of application
        @Test (priority=10)
        public void addSpecialCharacterName()
        {
                try
                {
                        myapp.addApplication();
                        base.fluentwait();
                        myapp.name.sendKeys("app%^&*()");
                        base.fluentwait();
                        myapp.selectEnvironment();
                        myapp.okBtn.click();
                        boolean isdisplay = myapp.applicationForm.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        myapp.crossSign.click(); //close the application form
                        LOG.info("ALL Special Characters are not allowed in application name field");
                }
                catch (Exception e)
                {
                        LOG.error("10.Topology added with Special Characters in name field");
                        Assert.fail();
                }
        }
//Check import application operation without selecting json file
        @Test (priority=11)
        public void importJsonBlank()
        {
                try
                {
                        myapp.importApplication();
                        myapp.topologyName.sendKeys("topo"+ arr[0]);
                        myapp.selectEnvironment();
                        myapp.okBtn.click();
                        boolean isdisplay = myapp.applicationForm.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        myapp.crossSign.click(); //close the application form
                        LOG.info("Blank Select Json not allowed while importing application");
                }
                catch (Exception e)
                {
                        LOG.error("11.Able to import topology without selecting json");
                        Assert.fail();
                }
        }
//Check import application operation by keeping topology name field blank
        @Test (priority=12)
        public void importNameBlank()
        {
                try
                {
                        myapp.importApplication();
                        myapp.selectJson.sendKeys(propertyUtils.getProperty(UserProperty.MYAPP_VALID_CONFIGURED_JSON));
                        base.fluentwait();
                        myapp.selectEnvironment();
                        myapp.okBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.successMsg));
                        String success=myapp.successMsg.getText();
                        Thread.sleep(3000);
                        Assert.assertEquals(success, "Topology imported successfully");
                        myapp.myApplications.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.confirmBox));
                        Thread.sleep(3000);
                        myapp.confirmOkBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.appHeader));
                        LOG.info("Topology imported successfully without giving topology name");
                }
                catch (Exception e)
                {
                        LOG.error("12.Unable to import topology without giving topology name");
                        Assert.fail();
                }
        }
//Check import application operation by keeping environment field blank
        @Test (priority=13)
        public void importEnvironmentBlank()
        {
                try
                {
                        myapp.importApplication();
                        myapp.selectJson.sendKeys(propertyUtils.getProperty(UserProperty.MYAPP_VALID_CONFIGURED_JSON));
                        base.fluentwait();
                        myapp.topologyName.sendKeys("topo"+ arr[0]);
                        myapp.okBtn.click();
                        boolean isdisplay = myapp.applicationForm.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        myapp.crossSign.click(); //close the application form
                        LOG.info("Blank environment not allowed while importing application");
                }
                catch (Exception e)
                {
                        LOG.error("13.Able to import topology without giving environment name");
                        Assert.fail();
                }
        }
//Import application successfully and verify success notification message
        @Test (priority=14)
        public void importApplicationSuccess()
        {
                try
                {
                        myapp.importApplication();
                        myapp.selectJson.sendKeys(propertyUtils.getProperty(UserProperty.MYAPP_VALID_CONFIGURED_JSON));
                        base.fluentwait();
                        myapp.topologyName.sendKeys("topo-1"); //If name changed then need to update new name for search, clone and export cases
                        myapp.selectEnvironment();
                        myapp.okBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.successMsg));
                        String success=myapp.successMsg.getText();
                        Assert.assertEquals(success, "Topology imported successfully");
                        myapp.myApplications.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.confirmBox));
                        Thread.sleep(2000);
                        myapp.confirmOkBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.appHeader));
                        LOG.info("Topology imported successfully");
                }
                catch (Exception e)
                {
                        LOG.error("14.Unable to import topology even after giving all valid configs");
                        Assert.fail();
                }
        }
//Check import application by giving same name application
        @Test (priority=15)
        public void importWithSameName()
        {
                try
                {
                        myapp.importApplication();
                        myapp.selectJson.sendKeys(propertyUtils.getProperty(UserProperty.MYAPP_VALID_CONFIGURED_JSON));
                        base.fluentwait();
                        myapp.topologyName.sendKeys("topo-1");
                        Thread.sleep(2000);
                        myapp.selectEnvironment();
                        myapp.okBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.errorMsg));
                        String error=myapp.errorMsg.getText();
                        Thread.sleep(3000);
                        Assert.assertEquals(error, "Application with same name already exists. Please choose a unique Application Name");
                        LOG.info("Topology import operation failed as application with same name already exist");
                        myapp.cancelError.click();
                        Thread.sleep(1000);
                        myapp.crossSign.click();
                }
                catch (Exception e)
                {
                        LOG.error("15.Able to import topology by giving already existing application name");
                        Assert.fail();
                }
        }
//Check Refresh action of application
        @Test (priority=16)
        public void refreshApplication()
        {
                try
                {
                        myapp.myApplications();
                        wait.until(ExpectedConditions.visibilityOf(myapp.refreshIcon));
                        myapp.refreshIcon.click();
                        Thread.sleep(2000);
                        boolean isdisplay = myapp.operations.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        LOG.info("Application refreshed successfully");
                }
                catch (Exception e)
                {
                        LOG.error("16.Unable to refresh application",e);
                        Assert.fail();
                }
        }
//Click on edit option of application and check
        @Test (priority=17)
        public void editApplication()
        {
                try
                {
                        myapp.myApplications();
                        wait.until(ExpectedConditions.visibilityOf(myapp.editIcon));
                        myapp.editIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.editPageGear));
                        boolean isdisplay = myapp.editPageGear.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        myapp.myApplications.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.confirmBox));
                        Thread.sleep(2000);
                        myapp.confirmOkBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.appHeader));
                        LOG.info("Click on edit icon redirect to application editor edit mode");
                }
                catch (Exception e)
                {
                        LOG.error("17.Click on edit icon does not work",e);
                        Assert.fail();
                }
        }
//Click on application name and check its navigation to view mode
        @Test (priority=18)
        public void clickOnApplicationName()
        {
                try
                {
                        Thread.sleep(2000);
                        myapp.myApplications.click();
                        base.fluentwait();
                        wait.until(ExpectedConditions.visibilityOf(myapp.operations));
                        myapp.appName.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.viewPageTiles));
                        boolean isdisplay = myapp.viewPageTiles.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        myapp.myApplications.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.appHeader));
                        LOG.info("Click on application name redirect to application editor view mode");
                }
                catch (Exception e)
                {
                        LOG.error("18.Click on application name does not work");
                        Assert.fail();
                }
        }
// Navigate from 'Application Editor' (view mode) page to 'My Applications' Page and check whether it shows comfirm popup window
        @Test (priority=19)
        public void navigateViewModeToMyapp()
        {
                try
                {
                        myapp.myApplications.click();
                        base.fluentwait();
                        wait.until(ExpectedConditions.visibilityOf(myapp.operations));
                        myapp.appName.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.viewPageTiles));
                        Thread.sleep(2000);
                        myapp.myApplications.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.appHeader));
                        boolean isdisplay = myapp.appHeader.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        LOG.info("Navigating from application editor view mode to my applications does not open comfirm popup window");
                }
                catch (Exception e)
                {
                        LOG.error("19.Navigating from application editor view mode to my applications failed");
                        Assert.fail();
                }
        }
// Navigate from 'Application Editor' (edit mode) page to 'My Applications' Page
        @Test (priority=20)
        public void navigateeditModeToMyapp()
        {
                try
                {
                        myapp.myApplications();
                        wait.until(ExpectedConditions.visibilityOf(myapp.editIcon));
                        myapp.editIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.editPageGear));
                        boolean isdisplay = myapp.editPageGear.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        myapp.myApplications.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.confirmBox));
                        Thread.sleep(2000);
                        myapp.confirmOkBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.appHeader));
                        LOG.info("Navigating from application editor edit mode to my applications opens comfirm popup window");
                }
                catch (Exception e)
                {
                        LOG.error("20.Navigating from application editor edit mode to my applications failed");
                        Assert.fail();
                }
        }
//Check clone action of application without selecting environment
        @Test (priority=21)
        public void cloneEnvironmentBlank()
        {
                try
                {
                        myapp.myApplications();
                        myapp.cloneIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.applicationForm));
                        myapp.cloneOkbtn.click();
                        boolean isdisplay = myapp.applicationForm.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        LOG.info("Unable to clone application without selecting environment");
                        myapp.crossSign.click();
                }
                catch (Exception e)
                {
                        LOG.error("21.Able to clone application without selecting environment",e);
                        Assert.fail();
                }
        }
//Check clone action of application and check success message
        @Test (priority=22)
        public void cloneApplicationSuccess()
        {
                try
                {
                        myapp.myApplications();
                        myapp.cloneIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.applicationForm));
                        myapp.selectEnvironment();
                        myapp.cloneOkbtn.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.successMsg));
                        String success=myapp.successMsg.getText();
                        Assert.assertEquals(success, "Topology cloned successfully");
                        Thread.sleep(2000);
                        myapp.myApplications.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.confirmBox));
                        myapp.confirmOkBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.appHeader));
                        LOG.info("Topology cloned successfully");
                }
                catch (Exception e)
                {
                        LOG.error("22.Clone operation failed",e);
                        Assert.fail();
                }
        }
//	Cancel clone operation by clicking on No button while cloning
        @Test (priority=23)
        public void cloneApplicationCancel()
        {
                try
                {
                        myapp.myApplications();
                        myapp.cloneIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.applicationForm));
                        myapp.selectEnvironment();
                        myapp.cloneCancelBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.createBtn));
                        boolean isdisplay = myapp.createBtn.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        LOG.info("Topology cloned successfully");
                }
                catch (Exception e)
                {
                        LOG.error("23.Clone operation failed",e);
                        Assert.fail();
                }
        }
// Clone imported application
        @Test (priority=24)
        public void cloneImportedApplication()
        {
                try
                {
                        Thread.sleep(2000);
                        myapp.myApplications.click();
                        base.fluentwait();
                        wait.until(ExpectedConditions.visibilityOf(myapp.createBtn));
                        myapp.searchIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.searchBox));
                        myapp.searchBox.sendKeys("topo");
                        wait.until(ExpectedConditions.visibilityOf(myapp.operations));
                        myapp.operations.click();
                        base.fluentwait();
                        myapp.cloneIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.applicationForm));
                        myapp.selectEnvironment();
                        myapp.cloneOkbtn.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.successMsg));
                        String success=myapp.successMsg.getText();
                        Assert.assertEquals(success, "Topology cloned successfully");
                        Thread.sleep(2000);
                        myapp.myApplications.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.confirmBox));
                        myapp.confirmOkBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.appHeader));
                        LOG.info("Imported topology cloned successfully");
                }
                catch (Exception e)
                {
                        LOG.error("24.Clone operation of imported topology failed",e);
                        Assert.fail();
                }
        }
//	Clone topology having components but not configured
//	Clone configured and deployed topology

//	Check pop up message displayed while exporting the topology
        @Test (priority=25)
        public void exportMessage()
        {
                try
                {
                        myapp.myApplications();
                        myapp.exportIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.applicationForm));
                        String msg=myapp.exportMsg.getText();
                        Thread.sleep(2000);
                        Assert.assertEquals(msg, "Are you sure you want to export the topology ?");
                        LOG.info("Export message displayed when click on export Icon");
                        myapp.closePopup.click();
                }
                catch (Exception e)
                {
                        LOG.error("25.Export message display failed",e);
                        Assert.fail();
                }
        }
//	Cancel the export action by clicking on No button while exporting
        @Test (priority=26)
        public void exportCancel()
        {
                try
                {
                        myapp.myApplications();
                        myapp.exportIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.applicationForm));
                        base.fluentwait();
                        Thread.sleep(2000);
                        myapp.exportNoBtn.click();
                        boolean isdisplay = myapp.createBtn.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        LOG.info("Topology export operation cancelled by clicking on No button");
                }
                catch (Exception e)
                {
                        LOG.error("26.Click on No button does not cancel the topology export operation",e);
                        Assert.fail();
                }
        }
//  Check export action of application
        @Test (priority=27)
        public void exportApplicationSuccess()
        {
                try
                {
                        myapp.myApplications();
                        myapp.exportIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.applicationForm));
                        myapp.exportYesbtn.click();
                        boolean isdisplay = myapp.createBtn.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        LOG.info("Topology exported successfully");
                }
                catch (Exception e)
                {
                        LOG.error("27.Export operation failed",e);
                        Assert.fail();
                }
        }
//	Export imported topology
        @Test (priority=28)
        public void exportImportedApplication()
        {
                try
                {
                        Thread.sleep(2000);
                        myapp.myApplications.click();
                        base.fluentwait();
                        wait.until(ExpectedConditions.visibilityOf(myapp.createBtn));
                        myapp.searchIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.searchBox));
                        myapp.searchBox.sendKeys("topo");
                        wait.until(ExpectedConditions.visibilityOf(myapp.operations));
                        myapp.operations.click();
                        base.fluentwait();
                        myapp.exportIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.applicationForm));
                        base.fluentwait();
                        myapp.exportYesbtn.click();
                        boolean isdisplay = myapp.createBtn.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        LOG.info("Export operation for imported application successful");
                        myapp.searchBox.clear();
                }
                catch (Exception e)
                {
                        LOG.error("28.Export operation for imported application failed",e);
                        Assert.fail();
                }
        }
//	Export cloned topology
        @Test (priority=29)
        public void exportClonedApplication()
        {
                try
                {
                        Thread.sleep(2000);
                        myapp.myApplications.click();
                        base.fluentwait();
                        wait.until(ExpectedConditions.visibilityOf(myapp.createBtn));
                        myapp.searchIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.searchBox));
                        myapp.searchBox.sendKeys("clone");
                        wait.until(ExpectedConditions.visibilityOf(myapp.operations));
                        myapp.operations.click();
                        base.fluentwait();
                        myapp.exportIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.applicationForm));
                        base.fluentwait();
                        myapp.exportYesbtn.click();
                        boolean isdisplay = myapp.createBtn.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        LOG.info("Export operation for cloned application successful");
                        myapp.searchBox.clear();
                }
                catch (Exception e)
                {
                        LOG.error("29.Export operation for cloned application failed",e);
                        Assert.fail();
                }
        }
//	Export topology having non configured components

//	Click on search icon opens the search text box
        @Test (priority=30)
        public void myappClickOnSearchIcon()
        {
                try
                {
                        Thread.sleep(2000);
                        myapp.myApplications.click();
                        base.fluentwait();
                        wait.until(ExpectedConditions.visibilityOf(myapp.createBtn));
                        myapp.searchIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.searchBox));
                        boolean isdisplay = myapp.searchBox.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        LOG.info("Click on search icon opens search text box");
                }
                catch (Exception e)
                {
                        LOG.error("30.Click on search icon does not open search text box");
                        Assert.fail();
                }
        }
//	Search topology by giving Full name
        @Test (priority=31)
        public void myappSearchFullName()
        {
                try
                {
                        Thread.sleep(2000);
                        myapp.myApplications.click();
                        base.fluentwait();
                        wait.until(ExpectedConditions.visibilityOf(myapp.createBtn));
                        myapp.searchIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.searchBox));
                        myapp.searchBox.sendKeys("application1");
                        boolean isdisplay = driver.findElement(By.xpath("//h4[contains(.,'application1')]")).isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        myapp.searchBox.clear();
                        LOG.info("Search works properly when Full name application given in search field");
                }
                catch (Exception e)
                {
                        LOG.error("31.Search by giving full name not working");
                        Assert.fail();
                }
        }
//	Search topology by giving Partial name
        @Test (priority=32)
        public void myappSearchPartialName()
        {
                try
                {
                        Thread.sleep(2000);
                        myapp.myApplications.click();
                        base.fluentwait();
                        wait.until(ExpectedConditions.visibilityOf(myapp.createBtn));
                        myapp.searchIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.searchBox));
                        myapp.searchBox.sendKeys("app");
                        boolean isdisplay = driver.findElement(By.xpath("//h4[contains(.,'app')]")).isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        myapp.searchBox.clear();
                        LOG.info("Search works properly when Partial name application given in search field");
                }
                catch (Exception e)
                {
                        LOG.error("32.Search by giving partial name not working");
                        Assert.fail();
                }
        }
//	Search topology by giving Non existing name
        @Test (priority=33)
        public void myappSearchNonExistingName()
        {
                try
                {
                        Thread.sleep(2000);
                        myapp.myApplications.click();
                        base.fluentwait();
                        wait.until(ExpectedConditions.visibilityOf(myapp.createBtn));
                        myapp.searchIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.searchBox));
                        myapp.searchBox.sendKeys("sdfdsfs");
                        boolean isdisplay = myapp.noDataFound.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        myapp.searchBox.clear();
                        LOG.info("Search by giving non existing application name shows No data Found message");
                }
                catch (Exception e)
                {
                        LOG.error("33.Search by giving non existing application name not showing No data Found message");
                        Assert.fail();
                }
        }
//	Check search is case sensitive or not
        @Test (priority=34)
        public void myappSearchCaseSensitive()
        {
                try
                {
                        Thread.sleep(2000);
                        myapp.myApplications.click();
                        base.fluentwait();
                        wait.until(ExpectedConditions.visibilityOf(myapp.createBtn));
                        myapp.searchIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.searchBox));
                        myapp.searchBox.sendKeys("APPLICATION1");
                        boolean isdisplay = driver.findElement(By.xpath("//h4[contains(.,'application1')]")).isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        myapp.searchBox.clear();
                        LOG.info("Search application is case insensitive");
                }
                catch (Exception e)
                {
                        LOG.error("34.Search application is case sensitive");
                        Assert.fail();
                }
        }
//	Check sort by Last Updated
        @Test (priority=35)
        public void sortByLastUpdated()
        {
                try
                {
                        driver.navigate().refresh();
                        Thread.sleep(2000);
                        myapp.myApplications.click();
                        base.fluentwait();
                        wait.until(ExpectedConditions.visibilityOf(myapp.createBtn));
                        myapp.sortDropdown.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.sortbyLastUpdate));
                        myapp.sortbyLastUpdate.click();
                        Thread.sleep(3000);
                        boolean isdisplay = driver.findElement(By.xpath("//h4[contains(.,'topo')]")).isDisplayed();
                        Assert.assertTrue(isdisplay);
                        LOG.info("Sort By LastUpdated works properly");
                        Thread.sleep(2000);
                }
                catch (Exception e)
                {
                        LOG.error("35.Sort By LastUpdated not working properly",e);
                        Assert.fail();
                }
        }
//	Check sort by Name
        @Test (priority=36)
        public void sortByName()
        {
                try
                {
                        Thread.sleep(2000);
                        myapp.myApplications.click();
                        base.fluentwait();
                        wait.until(ExpectedConditions.visibilityOf(myapp.createBtn));
                        myapp.sortDropdown.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.sortbyName));
                        myapp.sortbyName.click();
                        Thread.sleep(2000);
                        boolean isdisplay = driver.findElement(By.xpath("//h4[contains(.,'app')]")).isDisplayed();
                        Assert.assertTrue(isdisplay);
                        LOG.info("Sort By Name works properly");
                        Thread.sleep(2000);
                }
                catch (Exception e)
                {
                        LOG.error("36.Sort By Name not working properly");
                        Assert.fail();
                }
        }
//	Check sort by Status
        @Test (priority=37)
        public void sortByStatus()
        {
                try
                {
                        Thread.sleep(2000);
                        myapp.myApplications.click();
                        base.fluentwait();
                        wait.until(ExpectedConditions.visibilityOf(myapp.createBtn));
                        myapp.sortDropdown.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.sortbyStatus));
                        myapp.sortbyStatus.click();
                        Thread.sleep(2000);
                        boolean isdisplay = driver.findElement(By.xpath("//h4[contains(.,'app')]")).isDisplayed();
                        Assert.assertTrue(isdisplay);
                        LOG.info("Sort By Status works properly");
                        Thread.sleep(2000);
                }
                catch (Exception e)
                {
                        LOG.error("37.Sort By Status not working properly");
                        Assert.fail();
                }
        }
//	Check message shown on popup which opens when click on delete option
        @Test (priority=38)
        public void deleteMessage()
        {
                try
                {
                        myapp.myApplications();
                        myapp.deleteApplication();
                        String msg=myapp.deleteMsg.getText();
                        Assert.assertEquals(msg, "Are you sure you want to delete ?");
                        LOG.info("Delete message displayed when click on delete Icon");
                        Thread.sleep(1000);
                        myapp.closePopup.click();
                }
                catch (Exception e)
                {
                        LOG.error("38.Delete message does not displayed when click on delete Icon");
                        Assert.fail();
                }
        }
//	Cancel the delete operation by clicking on No button while deleting the application
        @Test (priority=39)
        public void deleteApplicationNo()
        {
                try
                {
                        myapp.myApplications();
                        myapp.deleteApplication();
                        myapp.deleteNoBtn.click();
                        boolean isdisplay = myapp.createBtn.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        LOG.info("Cancelled the delete operation by clicking on No button");
                }
                catch (Exception e)
                {
                        LOG.error("39.Delete operation cancel failed");
                        Assert.fail();
                }
        }
//	Delete Application and check validation message
        @Test (priority=40)
        public void deleteApplicationSuccess()
        {
                try
                {
                        driver.navigate().refresh();
                        myapp.myApplications();
                        myapp.deleteApplication();
                        myapp.deleteYesBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.successMsg));
                        String success=myapp.successMsg.getText();
                        Assert.assertEquals(success, "Topology deleted successfully");
                        LOG.info("Topology deleted successfully");
                        Thread.sleep(2000);
                }
                catch(Exception e)
                {
                        Assert.fail();
                        LOG.error("40. Topology deletion failed");
                }
        }
}
