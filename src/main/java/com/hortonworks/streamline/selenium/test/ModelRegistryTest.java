package com.hortonworks.streamline.selenium.test;

import java.util.UUID;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.hortonworks.streamline.selenium.page.BasePage;
import com.hortonworks.streamline.selenium.page.ModelRegistryPage;
import com.hortonworks.streamline.selenium.page.MyapplicationsPage;
import com.hortonworks.streamline.selenium.utils.PropertiesUtil;
import com.hortonworks.streamline.selenium.utils.UserProperty;

public class ModelRegistryTest extends BaseTest{

        ModelRegistryPage model = new ModelRegistryPage();
        MyapplicationsPage myapp = new MyapplicationsPage();
        BasePage base = new BasePage();
        PropertiesUtil propertyUtils = PropertiesUtil.getInstance();
        public static final Logger LOG=Logger.getLogger(CustomProcessorTest.class);
        String arr[]=UUID.randomUUID().toString().split("-");
        WebDriverWait wait = new WebDriverWait(driver,4000);
        Actions actions = new Actions(driver);

// Check Model Registry page header
        @Test (priority=1)
        public void modelRegistryHeader()
        {
                try
                {
                        model.myApplications.click();
                        model.modelRegistry.click();
                        wait.until(ExpectedConditions.visibilityOf(model.modelHeader));
                        String header=model.modelHeader.getText();
                        Assert.assertEquals("Model Registry", header);
                        Thread.sleep(3000);
                        LOG.info("Model Registry header displayed");
                }
                catch (Exception e)
                {
                        LOG.error("1.Model Registry header not displayed");
                        Assert.fail();
                }
        }
// Navigate from Model Registry to other page
        @Test (priority=2)
        public void myApplicationsNavigate()
        {
                try
                {
                        model.myApplications.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.appHeader));
                        String ma=myapp.appHeader.getText();
                        Assert.assertEquals("My Applications", ma);
                        Thread.sleep(3000);
                        LOG.info("Successfully navigated to My Applications page");
                }
                catch (Exception e)
                {

                }
        }
// Check message displayed when no data available under model registry page
        @Test (priority=3)
        public void noDataFoundMsg()
        {
                try
                {
                        model.modelRegistry.click();
                        wait.until(ExpectedConditions.visibilityOf(model.noDataFound));
                        String nodata=model.noDataFound.getText();
                        Assert.assertEquals("NO DATA FOUND", nodata);
                        Thread.sleep(3000);
                        LOG.info("Shows No Data Found Message when no cp available");
                }
                catch (Exception e)
                {
                        LOG.error("3.'No Data Found' Message display failed");
                        Assert.fail();
                }
        }
// Check what all configs available under model registry form
        @Test (priority=4)
        public void checkProcessorConfigs()
        {
                try
                {
                        model.addition();
                        if ((model.modelNameLabel.getText().contains("MODEL NAME"))&&(model.uploadPmmlLabel.getText().contains("UPLOAD PMML FILE")))
                                {
                                Thread.sleep(2000);
                                model.cancelBtn.click();
                                        LOG.info("Model Registry form shows configs as Model Name and Upload PMML file");
                                }
                 }
                catch (Exception e)
                 {
                        LOG.error("4.Model Registry form shows no config");
                }
        }
// Try to add model without giving any config value
        @Test (priority=5)
        public void modelConfigsBlank() throws InterruptedException
        {
                try
                {
                        model.addition();
                        model.okBtn.click();
                        boolean isdisplay = model.modelForm.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        LOG.info("Not able to save model by giving blank configs");
                        model.closePopup.click();
                }
                catch (Exception e)
                {
                        LOG.error("5.Able to save model with blank configs");
                        Assert.fail();
                }
        }
//Add model with blank model name
        @Test(priority=6)
        public void modelNameBlank()
        {
                try
                {
                        model.addition();
                        model.uploadPmml.sendKeys(propertyUtils.getProperty(UserProperty.MODEL_VALID_XML));
                        Thread.sleep(2000);
                        model.okBtn.click();
                        boolean isdisplay = model.modelForm.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        LOG.info("Not able to save model by giving blank model name");
                        model.closePopup.click();
                }
                catch (Exception e)
                {
                        LOG.error("6.Able to save model with blank model name");
                        Assert.fail();
                }
        }
//Add model with blank 'upload Pmml file' config
        @Test(priority=7)
        public void modelPmmlBlank()
        {
                try
                {
                        model.addition();
                        model.modelName.sendKeys("m1");
                        Thread.sleep(2000);
                        model.okBtn.click();
                        boolean isdisplay = model.modelForm.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        LOG.info("Not able to save model by giving blank upload PMML file field");
                        model.closePopup.click();
                }
                catch (Exception e)
                {
                        LOG.error("7.Able to save model with blank upload PMML file field");
                        Assert.fail();
                }
        }
//Add model with valid values and check notification message
        @Test(priority=8)
        public void addModelSuccess()
        {
                try
                {
                        model.addition();
                        model.modelName.sendKeys("m1");
                        model.uploadPmml.sendKeys(propertyUtils.getProperty(UserProperty.MODEL_VALID_XML));
                        Thread.sleep(2000);
                        model.okBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(model.successMsg));
                        String add = model.successMsg.getText();
                        Assert.assertEquals("Model added successfully", add);
                        LOG.info("Model added successfully by giving valid configs");
                }
                catch (Exception e)
                {
                        LOG.error("8.Unable to add model processor even after giving valid configs",e);
                        Assert.fail();
                }
        }
//Duplicate model addition and check error message
        @Test(priority=9)
        public void duplicateModel()
        {
                try
                {
                        model.addition();
                        model.modelName.sendKeys("m1");
                        model.uploadPmml.sendKeys(propertyUtils.getProperty(UserProperty.MODEL_VALID_XML));
                        Thread.sleep(2000);
                        model.okBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(model.errorMsg));
                        String add = model.errorMsg.getText();
                        Assert.assertEquals("Model with the same name is already existing", add);
                        LOG.info("Unable to add model with existing model name");
                        Thread.sleep(2000);
                        model.closeError.click();
                }
                catch (Exception e)
                {
                        LOG.error("9.Able to add same name model",e);
                        Assert.fail();
                }
        }
//Add model registry by giving model name with only special characters
        @Test(priority=10)
        public void modelNameSpecialCharacters()
        {
                try
                {
                        model.addition();
                        model.modelName.sendKeys("%^&*()");
                        model.uploadPmml.sendKeys(propertyUtils.getProperty(UserProperty.MODEL_VALID_XML));
                        Thread.sleep(2000);
                        model.okBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(model.successMsg));
                        String add = model.successMsg.getText();
                        Assert.assertEquals("Model added successfully", add);
                        LOG.info("Model added successfully by giving  model name with special characters");
                }
                catch (Exception e)
                {
                        LOG.error("10.Unable to add model processor even after giving valid configs",e);
                        Assert.fail();
                }
        }
//Try to add model by uploading invalid xml file for upload PMML file config
        @Test(priority=11)
        public void modelPmmlInvalid()
        {
                try
                {
                        model.addition();
                        model.modelName.sendKeys("m1" + arr[0]);
                        model.uploadPmml.sendKeys(propertyUtils.getProperty(UserProperty.MODEL_INVALID_XML));
                        Thread.sleep(2000);
                        model.okBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(model.errorMsg));
                        String add = model.errorMsg.getText();
                        Assert.assertEquals("An exception with message [null] was thrown while processing request.", add);
                        LOG.info("Unable to add model with invalid xml file");
                        Thread.sleep(2000);
                        model.closeError.click();
                }
                catch (Exception e)
                {
                        LOG.error("11.Able to add model with invalid PMML file",e);
                        Assert.fail();
                }
        }
//Try to add model by uploading blank xml file for upload PMML file config
        @Test(priority=12)
    public void modelPmmlBlankFile()
        {
                try
                {
                        model.addition();
                        model.modelName.sendKeys("m1" + arr[0]);
                        model.uploadPmml.sendKeys(propertyUtils.getProperty(UserProperty.MODEL_BLANK_XML));
                        Thread.sleep(2000);
                        model.okBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(model.errorMsg));
                        String add = model.errorMsg.getText();
                        Assert.assertEquals("An exception with message [null] was thrown while processing request.", add);
                        LOG.info("Unable to add model with blank xml file");
                        Thread.sleep(2000);
                        model.closeError.click();
                }
                catch (Exception e)
                {
                        LOG.error("12.Able to add model with blank PMML file",e);
                        Assert.fail();
                }
        }
//Cancel model add operation by clicking on No option while adding model
        @Test(priority=13)
        public void modelAddCancel()
        {
                try
                {
                        model.addition();
                        model.modelName.sendKeys("m1" + arr[0]);
                        model.uploadPmml.sendKeys(propertyUtils.getProperty(UserProperty.MODEL_VALID_XML));
                        Thread.sleep(3000);
                        model.cancelBtn.click();
                        boolean isdisplay = model.createBtn.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        LOG.info("Cancelled the model add operation");
                }
                catch (Exception e)
                {
                        LOG.error("13.Unable to cancel the model add operation",e);
                        Assert.fail();
                }
        }
//Check what all details displayed on listing page of Model Registry page
        @Test(priority=14)
        public void modelListing()
        {
                try
                {
                        model.modelRegistry.click();
                        wait.until(ExpectedConditions.visibilityOf(model.createBtn));
                        Thread.sleep(2000);
                        if (((driver.findElement(By.xpath("//th[@class='reactable-th-modelname']"))).getText().contains("Model Name"))&&((driver.findElement(By.xpath("//th[@class='reactable-th-pmmlfile']"))).getText().contains("PMML File Name"))&&((driver.findElement(By.xpath("//th[@class='reactable-th-action']"))).getText().contains("Actions")))
                        {
                                LOG.info("Model Registry listing page shows details such as Model Name,PMLL File Name and Actions");
                        }
                }
                catch (Exception e)
                {
                        LOG.error("14.Model Registry listing page shows wrong details of model",e);
                        Assert.fail();
                }
        }
//Check whether click on search icon opens search text field or not
        @Test (priority=15)
        public void modelClickOnSearchIcon()
        {
                try
                {
                        model.modelRegistry.click();
                        wait.until(ExpectedConditions.visibilityOf(model.createBtn));
                        Thread.sleep(2000);
                        model.searchIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(model.searchBox));
                        boolean isdisplay = model.searchBox.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        LOG.info("Click on search icon opens search text box");
                }
                catch (Exception e)
                {
                        LOG.error("15.Click on search icon does not open search text box",e);
                        Assert.fail();
                }
        }
//Check search operation by giving Full model name
        @Test (priority=16)
        public void modelSearchFullName()
        {
                try
                {
                        Thread.sleep(2000);
                        model.modelRegistry.click();
                        wait.until(ExpectedConditions.visibilityOf(model.createBtn));
                        model.searchIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(model.searchBox));
                        model.searchBox.sendKeys("m1");
                        boolean isdisplay = driver.findElement(By.xpath("//td[contains(.,'m1')]")).isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        model.searchBox.clear();
                        LOG.info("Full name search works properly for model registry page");
                }
                catch (Exception e)
                {
                        LOG.error("16.Full name search for model registry page shows improper results");
                        Assert.fail();
                }
        }
//Check search operation by giving Partial model name
        @Test (priority=17)
        public void modelSearchPartialName()
        {
                try
                {
                        Thread.sleep(2000);
                        model.modelRegistry.click();
                        wait.until(ExpectedConditions.visibilityOf(model.createBtn));
                        model.searchIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(model.searchBox));
                        model.searchBox.sendKeys("m");
                        boolean isdisplay = driver.findElement(By.xpath("//td[contains(.,'m1')]")).isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        model.searchBox.clear();
                        LOG.info("Partial search works properly for model registry page");
                }
                catch (Exception e)
                {
                        LOG.error("17.Partial name search for model registry page shows improper results");
                        Assert.fail();
                }
        }
//Check search operation by giving Non existing model name
        @Test (priority=18)
        public void modelSearchNonExistingName()
        {
                try
                {
                        Thread.sleep(2000);
                        model.modelRegistry.click();
                        wait.until(ExpectedConditions.visibilityOf(model.createBtn));
                        model.searchIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(model.searchBox));
                        model.searchBox.sendKeys("sdfdsfdcv");
                        boolean isdisplay = model.noDataFound.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        model.searchBox.clear();
                        LOG.info("Search with non existing name shows No Data Found message");
                }
                catch (Exception e)
                {
                        LOG.error("18.Search with non existing name shows no message");
                        Assert.fail();
                }
        }
//Check search operation is case sensitive or not
        @Test (priority=19)
        public void modelSearchCaseSensitive()
        {
                try
                {
                        Thread.sleep(2000);
                        model.modelRegistry.click();
                        wait.until(ExpectedConditions.visibilityOf(model.createBtn));
                        model.searchIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(model.searchBox));
                        model.searchBox.sendKeys("M1");
                        boolean isdisplay = driver.findElement(By.xpath("//td[contains(.,'m1')]")).isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        model.searchBox.clear();
                        LOG.info("Model Search is case insensitive");
                }
                catch (Exception e)
                {
                        LOG.error("19.Model Search is case sensitive");
                        Assert.fail();
                }
        }
//Check model registry Delete popup message
        @Test (priority=20)
        public void modelDeleteMessage()
        {
                try
                {
                        model.delete();
                        String msg = model.deleteMsg.getText();
                        Assert.assertEquals("Are you sure you want to delete this model?", msg);
                        Thread.sleep(2000);
                        model.closePopup.click();
                        LOG.info("Click on delete icon shows proper message");
                }
                catch (Exception e)
                {
                        LOG.error("20.Click on delete icon does not show any message");
                        Assert.fail();
                }
        }
//Click on No option while deleting model
        @Test (priority=21)
        public void modelDeleteNo()
        {
                try
                {
                        model.delete();
                        model.deleteNoBtn.click();
                        boolean isdisplay = model.createBtn.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        LOG.info("Click on No option while deleting model cancels the delete operation");
                }
                catch (Exception e)
                {
                        LOG.error("21.Click on No option deleted the model");
                        Assert.fail();
                }
        }
//Delete model successfully by clickng on Yes option and check notification message
        @Test (priority=22)
        public void modelDeleteYes()
        {
                try
                {
                        model.delete();
                        model.deleteYesBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(model.successMsg));
                        String add = model.successMsg.getText();
                        Assert.assertEquals("Model deleted successfully", add);
                        Thread.sleep(2000);
                        LOG.info("Model deleted successfully");
                        driver.navigate().refresh();
                }
                catch (Exception e)
                {
                        LOG.error("22.Model delete operation failed");
                        Assert.fail();
                }
        }
}
