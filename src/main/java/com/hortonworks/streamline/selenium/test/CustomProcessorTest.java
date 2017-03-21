package com.hortonworks.streamline.selenium.test;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.hortonworks.streamline.selenium.page.BasePage;
import com.hortonworks.streamline.selenium.page.CustomProcessorPage;
import com.hortonworks.streamline.selenium.page.MyapplicationsPage;
import com.hortonworks.streamline.selenium.utils.PropertiesUtil;
import com.hortonworks.streamline.selenium.utils.UserProperty;

import java.util.UUID;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;


public class CustomProcessorTest extends BaseTest{

        CustomProcessorPage cp=new CustomProcessorPage();
        MyapplicationsPage myapp = new MyapplicationsPage();
        BasePage base = new BasePage();
        PropertiesUtil propertyUtils = PropertiesUtil.getInstance();
        public static final Logger LOG=Logger.getLogger(CustomProcessorTest.class);
        String arr[]=UUID.randomUUID().toString().split("-");
        WebDriverWait wait = new WebDriverWait(driver,4000);
        Actions actions = new Actions(driver);

//	Check  Custom Processor Page Header
        @Test (priority=1)
        public void cpHeader()
        {
                try
                {
                        cp.configuration.click();
                        cp.customProcessor.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.cpHeader));
                        String header=cp.cpHeader.getText();
                        Assert.assertEquals("Configuration/Custom Processor", header);
                        Thread.sleep(3000);
                        LOG.info("Custom Processor header displayed");
                 }
                catch (Exception e)
                 {
                        LOG.error("1.Custom Processor header not displayed");
                        Assert.fail();
                }
        }
// Navigate from CustomProcessor to Model Registry page
         @Test (priority=2)
         public void modelRegistryNavigate()
         {
                 try
                 {
                        cp.modelRegistry.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.modelHeader));
                        String ma=cp.modelHeader.getText();
                        Assert.assertEquals("Model Registry", ma);
                        Thread.sleep(3000);
                        LOG.info("Successfully navigated to Model Registry page");
                  }
                 catch (Exception e)
                  {
                         LOG.error("2.Navigation failed");
                         Assert.fail();
                 }
         }
// Check message displayed when no data available under custom processor page
         @Test (priority=3)
         public void noDataFoundMsg()
         {
                 try
                 {
                        cp.configuration.click();
                        cp.customProcessor.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.noDataFound));
                        String nodata=cp.noDataFound.getText();
                        Assert.assertEquals("NO DATA FOUND", nodata);
                        Thread.sleep(3000);
                        LOG.info("Shows No Data Found Message when no cp available");
                  }
                 catch (Exception e)
                  {
                         LOG.error("3.Message display failed");
                         Assert.fail();
                 }
         }
// Check what all configs available under custom processor
        @Test (priority=4)
        public void checkProcessorConfigs()
        {
                try
                {
                        cp.addition();
                        if ((cp.streamEngineLabel.getText().contains("STREAMING ENGINE"))&&(cp.namelabel.getText().contains("NAME"))&&(cp.descLabel.getText().contains("DESCRIPTION"))&&(cp.classLabel.getText().contains("CLASSNAME"))&&(cp.uploadJarLabel.getText().contains("UPLOAD JAR"))&&(cp.configFieldLabel.getText().contains("CONFIG FIELDS"))&&(cp.inputSchemaLable.getText().contains("INPUT SCHEMA"))&&(cp.outputSchemaLable.getText().contains("OUTPUT SCHEMA")))
                                {
                                        cp.cancelBtn.click();
                                        LOG.info("Custom processor shows configs as Streaming Engine,Name,Description,Classname,Upload Jar,Config Fields,Input Schema and Output schema");
                                }
                 }
                catch (Exception e)
                 {
                        LOG.error("4.Custom processor form shows no config");
                }
        }
// Check whether streaming engine field is disabled or editable by user
        @Test (priority=5)
        public void streamEngineDisabled()
        {
                try
                {
                        cp.addition();
                        boolean disabledurl= cp.streamLineEngine.isEnabled();
                    Assert.assertFalse(disabledurl);
                    Thread.sleep(2000);
                    cp.cancelBtn.click();
                    LOG.info("User cannot update streaming engine field as it is disabled");
                }
                catch (Exception e)
                {
                        LOG.error("5.streaming engine field editable");
                        Assert.fail();
                }
        }
// Navigate from custom processor form to other page without adding any config value and Check whether it shows Confirm Box popup or not
        @Test (priority=6)
        public void confrimBoxWithoutAddingAnyConfig()
        {
                try
                {
                        cp.addition();
                        cp.modelRegistry.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.modelHeader));
                        String ma=cp.modelHeader.getText();
                        Assert.assertEquals("Model Registry", ma);
                        Thread.sleep(3000);
                        LOG.info("Confirm Box popup doesnot displayed");
                }
                catch (Exception e)
                {
                        LOG.error("6.Confirm Box popup displayed");
                        Assert.fail();
                }
        }
// Check Confirm Box popup message which opens when user try to navigate to other page before saving custom processor configs
        @Test (priority=7)
        public void confrimBoxMsg()
        {
                try
                {
                        cp.addition();
                        cp.name.sendKeys("cp"+ arr[0]);
                        cp.modelRegistry.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.confirmBoxMsg));
                        Thread.sleep(2000);
                        String msg=cp.confirmBoxMsg.getText();
                        Assert.assertEquals("Your Processor Config setting is not saved !Are you sure you want to leave ?", msg);
                        cp.closePopup.click();
                        Thread.sleep(2000);
                        cp.name.clear();
                        cp.cancelBtn.click();
                        LOG.info("Confirm Box popup displayed");

                }
                catch (Exception e)
                {
                        LOG.error("7.Confirm Box popup does not display proper msg");
                        Assert.fail();
                }
        }
// Click on No option of Confirm Box popup which opens when user try to navigate to other page before saving custom processor configs
        @Test (priority=8)
        public void confrimBoxClickNo()
        {
                try
                {
                        cp.addition();
                        cp.name.sendKeys("cp"+ arr[0]);
                        base.fluentwait();
                        cp.modelRegistry.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.confirmBoxMsg));
                        Thread.sleep(2000);
                        cp.confirmBoxCancelBtn.click();
                        boolean isdisplay = cp.streamLineEngine.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        LOG.info("Click on No option");
                        Thread.sleep(2000);
                        cp.name.clear();
                        cp.cancelBtn.click();
                }
                catch (Exception e)
                {
                        LOG.error("8.Confirm Box still open even after clicking on No option");
                        Assert.fail();
                }
        }
// Click on Yes option of Confirm Box popup and navigate to model registry page
        @Test (priority=9)
        public void confrimBoxClickYes()
        {
                try
                {
                        cp.addition();
                        cp.name.sendKeys("cp"+ arr[0]);
                        base.fluentwait();
                        cp.modelRegistry.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.confirmBoxMsg));
                        Thread.sleep(2000);
                        cp.confirmBoxOkBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.modelHeader));
                        String ma=cp.modelHeader.getText();
                        Assert.assertEquals("Model Registry", ma);
                        LOG.info("Successfully navigated to Model Registry page");
                }
                catch (Exception e)
                {
                        LOG.error("9.Navigation failed even after selecting yes option of confirm box");
                        Assert.fail();
                }
        }
// Try to add new custom processor without filling mandatory fields
        @Test (priority=10)
        public void addWithConfigsBlank()
        {
                try
                {
                        cp.addition();
                        cp.saveBtn.click();
                        boolean isdisplay = cp.streamLineEngine.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        cp.cancelBtn.click();
                        LOG.info("Unable to save custom processor without filling mandatory fields");
                }
                catch (Exception e)
                {
                        LOG.error("10.Able to save custom processor without filling mandatory fields",e);
                        Assert.fail();
                }
        }
//	Add Custom Processor successfully and verify success notification message
        @Test (priority=11)
        public void addprocessor()
        {
                try
                {
                        cp.addition();
                        cp.name.sendKeys("cp1"); //if this name changed then need to update new processor name for search and delete cases
                        cp.description.sendKeys("testing");
                        cp.className.sendKeys(propertyUtils.getProperty(UserProperty.CUSTOM_PROCESSOR_VALID_CLASSNAME));
                        Thread.sleep(2000);
                        cp.uploadJar.sendKeys(propertyUtils.getProperty(UserProperty.CUSTOM_PROCESSOR_VALID_JAR));
                        cp.addConfigs();
                        Thread.sleep(2000);
                        cp.addInputSchema();
                        cp.saveBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.successMsg));
                        String add=cp.successMsg.getText();
                        Assert.assertEquals("Processor added successfully", add);
                        LOG.info("Custom Processor added successfully");
                        Thread.sleep(2000);
                }
                catch (Exception e)
                {
                        LOG.error("11.Unable to add new custom processor even after adding valid configs",e);
                        Assert.fail();
                }
        }
// Duplicate Custom processor and check error notification message
        @Test (priority=12)
        public void duplicateJar()
        {
                try
                {
                        cp.addition();
                        cp.name.sendKeys("cp"+ arr[0]);
                        cp.description.sendKeys("testing");
                        cp.className.sendKeys(propertyUtils.getProperty(UserProperty.CUSTOM_PROCESSOR_VALID_CLASSNAME));
                        Thread.sleep(2000);
                        cp.uploadJar.sendKeys(propertyUtils.getProperty(UserProperty.CUSTOM_PROCESSOR_VALID_JAR));
                        cp.addConfigs();
                        cp.addInputSchema();
                        cp.saveBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.errorMsg));
                        String add=cp.errorMsg.getText();
                        Assert.assertEquals("The jar file is already exists", add);
                        LOG.info("Unable to add Custom Processor");
                        Thread.sleep(2000);
                        cp.closeError.click();
                        Thread.sleep(1000);
                        cp.cancelBtn.click();
                }
                catch (Exception e)
                {
                        LOG.error("12.Custom Processor added successfully by uploading existing jar file");
                        Assert.fail();
                }
        }
// Check Validation message for same custom processor name
        @Test (priority=13)
        public void duplicateName()
        {
                try
                {
                        cp.addition();
                        cp.name.sendKeys("cp1");
                        Thread.sleep(2000);
                        String namemsg=cp.sameNameMsg.getText();
                        Assert.assertEquals("Processor with this name is already present", namemsg);
                        LOG.info("Unable to add Custom Processor as custom processor with same name already exists");
                        Thread.sleep(1000);
                        cp.cancelBtn.click();
                }
                catch (Exception e)
                {
                        LOG.error("13.Custom Processor added successfully by giving existing processor name");
                        Assert.fail();
                }
        }
// Try to add custom processor by giving invalid classname
        @Test (priority=14)
        public void classnameInvalid()
        {
                try
                {
                        cp.addition();
                        cp.name.sendKeys("cp"+ arr[0]);
                        cp.description.sendKeys("testing");
                        cp.className.sendKeys(propertyUtils.getProperty(UserProperty.CUSTOM_PROCESSOR_INVALID_CLASSNAME));
                        Thread.sleep(2000);
                        cp.uploadJar.sendKeys(propertyUtils.getProperty(UserProperty.CUSTOM_PROCESSOR_VALID_JAR));
                        cp.addConfigs();
                        cp.addInputSchema();
                        cp.saveBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.errorMsg));
                        String add=cp.errorMsg.getText();
                        Assert.assertEquals("Class name doesn't exists in a jar file", add);
                        Thread.sleep(2000);
                        cp.closeError.click();
                        Thread.sleep(1000);
                        cp.cancelBtn.click();
                        LOG.info("Unable to add Custom Processor due to invalid classname");
                }
                catch (Exception e)
                {
                        LOG.error("14.Custom Processor added successfully by giving invalid class name");
                        Assert.fail();
                }
        }
// Try to add custom processor without adding values of config fields and check validation message
        @Test (priority=15)
        public void withoutConfigFields()
        {
                try
                {
                        cp.addition();
                        cp.name.sendKeys("cp"+ arr[0]);
                        cp.description.sendKeys("testing");
                        cp.className.sendKeys(propertyUtils.getProperty(UserProperty.CUSTOM_PROCESSOR_VALID_CLASSNAME));
                        Thread.sleep(2000);
                        cp.uploadJar.sendKeys(propertyUtils.getProperty(UserProperty.CUSTOM_PROCESSOR_VALID_JAR));
                        cp.addInputSchema();
                        cp.saveBtn.click();
                        String configmsg=cp.missingConfigMsg.getText();
                        Assert.assertEquals("Please add Config Fields", configmsg);
                        LOG.info("Unable to add Custom Processor as config fields are blank");
                        Thread.sleep(1000);
                        cp.cancelBtn.click();
                }
                catch (Exception e)
                {
                        LOG.error("15.Custom Processor added successfully without configs");
                        Assert.fail();
                }
        }
// Try to add custom processor by keeping input schema filed blank
        @Test (priority=16)
        public void inputSchemaBlank()
        {
                try
                {
                        cp.addition();
                        cp.name.sendKeys("cp"+ arr[0]);
                        cp.description.sendKeys("testing");
                        cp.className.sendKeys(propertyUtils.getProperty(UserProperty.CUSTOM_PROCESSOR_VALID_CLASSNAME));
                        Thread.sleep(2000);
                        cp.uploadJar.sendKeys(propertyUtils.getProperty(UserProperty.CUSTOM_PROCESSOR_VALID_JAR));
                        cp.addConfigs();
                        Thread.sleep(1000);
                        cp.saveBtn.click();
                        boolean isdisplay = cp.inputcrossSign.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        LOG.info("Unable to add Custom Processor as input schema field is blank");
                        Thread.sleep(1000);
                        cp.cancelBtn.click();
                }
                catch (Exception e)
                {
                        LOG.error("16.Custom Processor added successfully with blank input schema");
                        Assert.fail();
                }
        }
// Try to add custom processor by giving invalid json in input schema filed
        @Test (priority=17)
        public void inputSchemaInvalid()
        {
                try
                {
                        cp.addition();
                        cp.name.sendKeys("cp"+ arr[0]);
                        cp.description.sendKeys("testing");
                        cp.className.sendKeys(propertyUtils.getProperty(UserProperty.CUSTOM_PROCESSOR_VALID_CLASSNAME));
                        Thread.sleep(2000);
                        cp.uploadJar.sendKeys(propertyUtils.getProperty(UserProperty.CUSTOM_PROCESSOR_VALID_JAR));
                        Thread.sleep(2000);
                        cp.addConfigs();
                        Thread.sleep(1000);
                        cp.saveBtn.click();
                        boolean isdisplay = cp.inputcrossSign.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        LOG.info("Unable to add Custom Processor as input schema field has invalid json");
                        Thread.sleep(2000);
                        cp.cancelBtn.click();
                }
                catch (Exception e)
                {
                        LOG.error("17.Custom Processor added successfully with invalid input schema");
                        Assert.fail();
                }
        }
//Try to add custom processor by giving invalid json for output schema
        @Test (priority=18)
        public void outputSchemaInvalid()
        {
                try
                {
                        cp.addition();
                        cp.name.sendKeys("cp"+ arr[0]);
                        cp.description.sendKeys("testing");
                        cp.className.sendKeys(propertyUtils.getProperty(UserProperty.CUSTOM_PROCESSOR_VALID_CLASSNAME));
                        Thread.sleep(2000);
                        cp.uploadJar.sendKeys(propertyUtils.getProperty(UserProperty.CUSTOM_PROCESSOR_VALID_JAR));
                        cp.addConfigs();
                        cp.addInputSchema();
                        cp.opStreamPlusSign.click();
                        Thread.sleep(1000);
                        boolean isdisplay = cp.outputJsonCrossSign.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        LOG.info("Unable to add Custom Processor as output schema field has invalid json");
                        Thread.sleep(2000);
                        cp.cancelBtn.click();
                }
                catch (Exception e)
                {
                        LOG.error("18.Custom Processor added successfully with invalid output schema");
                        Assert.fail();
                }
        }
// Update the Output schema name while configuring Custom Processor by giving blank name
        @Test (priority=19)
        public void opSchemaTitleBlank()
        {
                try
                {
                        cp.addition();
                        cp.outputSchemaName.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.outputSchemaPopup));
                        cp.outputSchemaPopup.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                        cp.outputSchemaPopup.sendKeys(Keys.chord(Keys.DELETE));
                        wait.until(ExpectedConditions.visibilityOf(cp.OutputSchemaNameValidation));
                        String name=cp.OutputSchemaNameValidation.getText();
                        Assert.assertEquals("Stream-id cannot be blank", name);
                        LOG.info("Unable to add blank output schema name");
                        cp.schemaNameCancelSign.click();
                        cp.cancelBtn.click();

                }
                catch (Exception e)
                {
                        LOG.error("19.Able to add blank output schema name");
                        Assert.fail();
                }
        }
//Update the Output schema name while configuring Custom Processor by giving space between name
        @Test (priority=20)
        public void opSchemaTitleWithSpace()
        {
                try
                {
                        cp.addition();
                        cp.outputSchemaName.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.outputSchemaPopup));
                        cp.outputSchemaPopup.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                        cp.outputSchemaPopup.sendKeys(Keys.chord(Keys.DELETE));
                        cp.outputSchemaPopup.sendKeys("hi hello");
                        wait.until(ExpectedConditions.visibilityOf(cp.OutputSchemaNameValidation));
                        String name=cp.OutputSchemaNameValidation.getText();
                        Assert.assertEquals("Stream-id cannot have space in between", name);
                        LOG.info("Stream-id cannot have space in between");
                        cp.cancelBtn.click();

                        }
                        catch (Exception e)
                        {
                                LOG.error("20.Stream-id can have space in between");
                                Assert.fail();
                        }
                }
//Click on sign + of Output Schema and add new output schema with only []
        @Test (priority=21)
        public void addOutputSchemaFail()
        {
                try
                {
                        cp.addition();
                        cp.opStreamPlusSign.click();
                        Thread.sleep(1000);
                        WebElement se= driver.findElement(By.xpath(".//*[@id='tabs-container-pane-2']//div[6]/div[1]//div[5]/div/pre")); //Move to second stream of output schema
                        actions.moveToElement(se);
                        actions.click();
                        actions.sendKeys("[]");
                        actions.build().perform();
                        cp.saveBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.successMsg));
                        String output=cp.successMsg.getText();
                        Assert.assertEquals("Output streams needs to be an array with atleast one field object.", output);
                        LOG.info("Output streams needs to be an array with atleast one field object");
                        Thread.sleep(2000);
                        cp.cancelBtn.click();
                }
                catch (Exception e)
                {
                        LOG.error("21.able to add schema under output schema");
                        Assert.fail();
                }
        }
//Click on sign + of Output Schema and add two output schema and configs as type boolean
        @Test (priority=22)
        public void addOutputSchemaPass()
        {
                try
                {
                        cp.addition();
                        cp.name.sendKeys("cp"+ arr[0]);
                        cp.description.sendKeys("testing");
                        cp.className.sendKeys(propertyUtils.getProperty(UserProperty.CUSTOM_PROCESSOR_VALID_CLASSNAME));
                        Thread.sleep(2000);
                        cp.uploadJar.sendKeys(propertyUtils.getProperty(UserProperty.CUSTOM_PROCESSOR_VALID_JAR_SECOND));
                        cp.addConfigBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.addConfigForm));
                        cp.fieldName.sendKeys("field");
                        cp.uiName.sendKeys("ui");
                        cp.typeDropdown.click();
                        cp.typeBoolean.click();
                        cp.defaultValue.sendKeys("False");
                        cp.tooltip.sendKeys("enter name");
                        cp.addConfigOkBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.inputSchemaLable));
                        cp.addInputSchema();
                        Thread.sleep(1000);
                        cp.opStreamPlusSign.click();
                        Thread.sleep(1000);
                        WebElement se= driver.findElement(By.xpath(".//*[@id='tabs-container-pane-2']//div[6]/div[1]//div[5]/div/pre")); //Move to second stream of output schema
                        actions.moveToElement(se);
                        actions.click();
                        actions.sendKeys("[{\"name\": \"childField1\",\"type\": \"INTEGER\"}]");
                        actions.build().perform();
                        Thread.sleep(1000);
                        cp.saveBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.successMsg));
                        String output=cp.successMsg.getText();
                        Assert.assertEquals("Processor added successfully", output);
                        LOG.info("Able to second output schema and configs with boolean type");
                }
                catch (Exception e)
                {
                        LOG.error("22.Unable to add second output schema and configs with boolean type", e);
                        Assert.fail();
                }
        }
//Click on cross sign near Output Schema which appear after adding new output stream and check its functionality
        @Test (priority=23)
        public void deleteSecondOutputSchema()
        {
                try
                {
                        cp.addition();
                        cp.addInputSchema();
                        Thread.sleep(1000);
                        cp.opStreamPlusSign.click();
                        Thread.sleep(2000);
                        cp.opStreamCancel.click();
                        Thread.sleep(1000);
                        boolean isdisplay = cp.streamLineEngine.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        LOG.info("Added and deleted second output schema");
                        cp.cancelBtn.click();
                }
                catch (Exception e)
                {
                        LOG.error("23.Unable to add and delete second output schema under output schema");
                        Assert.fail();
                }
        }
//Click on cancel button while adding the custom processor
@Test (priority=24)
        public void cancelAddOperation()
        {
                try
                {
                        cp.addition();
                        cp.name.sendKeys("cp"+ arr[0]);
                        cp.description.sendKeys("testing");
                        cp.className.sendKeys(propertyUtils.getProperty(UserProperty.CUSTOM_PROCESSOR_VALID_CLASSNAME));
                        Thread.sleep(2000);
                        cp.uploadJar.sendKeys(propertyUtils.getProperty(UserProperty.CUSTOM_PROCESSOR_VALID_JAR_SECOND));
                        cp.addConfigs();
                        cp.addInputSchema();
                        cp.cancelBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.createBtn));
                        boolean isdisplay = cp.createBtn.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        LOG.info("Custom Processor add operation cancelled");
                }
                catch (Exception e)
                {
                        LOG.error("24.Able to add custom processor after cancelling the operation");
                        Assert.fail();
                }
        }
//Validation on empty fields under Add Config Fields
        @Test (priority=25)
        public void addConfigsBlank()
        {
                try
                {
                        cp.addition();
                        cp.addConfigBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.addConfigForm));
                        Thread.sleep(2000);
                        cp.addConfigOkBtn.click();
                        boolean isdisplay = cp.addConfigForm.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        LOG.info("Unable to add configs with blank data");
                        cp.addConfigcancelBtn.click();
                        Thread.sleep(1000);
                        cp.cancelBtn.click();
                }
                catch (Exception e)
                {
                        LOG.error("25.Able to add configs with blank config values");
                        Assert.fail();
                }
        }
//Add configs with type as number under Add Config Fields
        @Test (priority=26)
        public void addConfigTypeNumber()
        {
                try
                {
                        cp.addition();
                        cp.addConfigBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.addConfigForm));
                        cp.fieldName.sendKeys("field");
                        cp.uiName.sendKeys("ui");
                        cp.typeDropdown.click();
                        cp.typeNumber.click();
                        cp.defaultValue.sendKeys("10");
                        cp.tooltip.sendKeys("enter tooltip");
                        cp.addConfigOkBtn.click();
                        boolean isdisplay = driver.findElement(By.xpath("//td[contains(.,'number')]")).isDisplayed();
                        Assert.assertTrue(isdisplay);
                        LOG.info("Configs with type number added");
                        Thread.sleep(2000);
                        cp.cancelBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.createBtn));
                }
                catch (Exception e)
                {
                        LOG.error("26.Unable to add configs with type number");
                        Assert.fail();
                }
        }
//Uncheck Is User Input checkbox under Add Config Fields
        @Test (priority=27)
        public void isUserInputFalse()
        {
                try
                {
                        cp.addition();
                        cp.addConfigBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.addConfigForm));
                        cp.fieldName.sendKeys("field");
                        cp.uiName.sendKeys("ui");
                        cp.typeDropdown.click();
                        cp.typeNumber.click();
                        cp.defaultValue.sendKeys("10");
                        cp.tooltip.sendKeys("enter tooltip");
                        cp.isUserInputCheckBox.click();
                        Thread.sleep(2000);
                        cp.addConfigOkBtn.click();
                        boolean isdisplay = driver.findElement(By.xpath("//td[6][contains(.,'false')]")).isDisplayed();
                        Assert.assertTrue(isdisplay);
                        LOG.info("Configs added by unchecking isuserInput checkbox");
                        Thread.sleep(2000);
                        cp.cancelBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.createBtn));
                }
                catch (Exception e)
                {
                        LOG.error("27.Unable to add configs by unchecking isuserInput checkbox");
                        Assert.fail();
                }
        }
//Uncheck Is User Input checkbox under Add Config Fields
        @Test (priority=28)
        public void isOptionalTrue()
        {
                try
                {
                        cp.addition();
                        cp.addConfigBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.addConfigForm));
                        cp.fieldName.sendKeys("dsfsdf");
                        cp.uiName.sendKeys("udsfi");
                        cp.isOptionalCheckBox.click();
                        cp.typeDropdown.click();
                        cp.typeNumber.click();
                        cp.defaultValue.sendKeys("5460");
                        cp.tooltip.sendKeys("enter tooltip");
                        Thread.sleep(2000);
                        cp.addConfigOkBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.inputSchemaLable));
                        boolean isdisplay = driver.findElement(By.xpath("//td[3][contains(.,'true')]")).isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        LOG.info("Configs added by checking isOptional checkbox");
                        cp.cancelBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.createBtn));
                }
                catch (Exception e)
                {
                        LOG.error("28.Unable to add configs by checking isOptional checkbox");
                        Assert.fail();
                }
        }
//Edit the data of Add Config Fields
        @Test (priority=29)
        public void editConfigName()
        {
                try
                {
                        cp.addition();
                        cp.addConfigs();
                        cp.editAddConfigs.click();
                        cp.fieldName.clear();
                        cp.fieldName.sendKeys("fieldname edited");
                        Thread.sleep(1000);
                        cp.addConfigOkBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.inputSchemaLable));
                        boolean isdisplay = driver.findElement(By.xpath("//td[contains(.,'fieldname edited')]")).isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        LOG.info("Configs edited successfully");
                        cp.cancelBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.createBtn));
                }
                catch (Exception e)
                {
                        LOG.error("29.Unable to edit configs under add configs section");
                        Assert.fail();
                }
        }
//Delete configs of Add Config Fields
        @Test (priority=30)
        public void deleteConfigs()
        {
                try
                {
                        cp.addition();
                        cp.addConfigs();
                        Thread.sleep(2000);
                        cp.deleteAddConfigs.click();
                        Thread.sleep(1000);
                        boolean isdisplay = driver.findElement(By.xpath("//td[contains(.,'No records found.')]")).isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        LOG.info("Configs deleted successfully");
                        cp.cancelBtn.click();
                }
                catch (Exception e)
                {
                        LOG.error("30.Unable to delete configs under add configs section");
                        Assert.fail();
                }
        }
//(Update CP)Check whether user can update value for 'Name' field of custom processor
        @Test (priority=31)
        public void updateProcessorName()
        {
                try
                {
                        Thread.sleep(2000);
                        cp.configuration.click();
                        cp.customProcessor.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.editIcon));
                        cp.editIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.name));
                        boolean disabledurl= cp.name.isEnabled();
                    Assert.assertFalse(disabledurl);
                    Thread.sleep(2000);
                    cp.cancelBtn.click();
                    LOG.info("User cannot update processor name once added");
                }
                catch (Exception e)
                {
                        LOG.error("31.Able to edit custom processor name");
                        Assert.fail();
                }
        }
//Check what all details displayed on listing page of Custom Processor page under Configuration
        @Test (priority=32)
        public void processorListing()
        {
                try
                {
                        driver.navigate().refresh();
                        cp.configuration.click();
                        cp.customProcessor.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.editIcon));
                        if (((driver.findElement(By.xpath("//th[@class='reactable-th-name']"))).getText().contains("Name"))&&((driver.findElement(By.xpath("//th[@class='reactable-th-description']"))).getText().contains("Description"))&&((driver.findElement(By.xpath("//th[@class='reactable-th-jarfilename']"))).getText().contains("Jar File Name"))&&((driver.findElement(By.xpath("//th[@class='reactable-th-action']"))).getText().contains("Actions")))
                                {
                                        LOG.info("Custom processor listing page shows details such as Name,Description,Jar File Name and Actions");
                                }
                }
                catch (Exception e)
                {
                        LOG.error("32.Custom processor listing page shows wrong details of processor");
                        Assert.fail();
                }
        }
//Check whether click on search icon opens search text field or not
        @Test (priority=33)
        public void cpClickOnSearchIcon()
        {
                try
                {
                        Thread.sleep(2000);
                        cp.configuration.click();
                        cp.customProcessor.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.createBtn));
                        cp.searchIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.searchBox));
                        boolean isdisplay = cp.searchBox.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        LOG.info("Click on search icon opens search text box");
                }
                catch (Exception e)
                {
                        LOG.error("33.Click on search icon does not open search text box");
                        Assert.fail();
                }
        }
//Check Full Name search for Custom Processor
        @Test (priority=34)
        public void cpSearchFullName()
        {
                try
                {
                        Thread.sleep(2000);
                        cp.configuration.click();
                        cp.customProcessor.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.createBtn));
                        cp.searchIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.searchBox));
                        cp.searchBox.sendKeys("cp1");
                        boolean isdisplay = driver.findElement(By.xpath("//td[contains(.,'cp1')]")).isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        LOG.info("Search by full name shows proper result");
                        cp.searchBox.clear();
                }
                catch (Exception e)
                {
                        LOG.error("34.Search by full name not working");
                        Assert.fail();
                }
        }
//Check partial Name search for Custom Processor
        @Test (priority=35)
        public void cpSearchPartialName()
        {
                try
                {
                        Thread.sleep(2000);
                        cp.configuration.click();
                        cp.customProcessor.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.createBtn));
                        cp.searchIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.searchBox));
                        cp.searchBox.sendKeys("1");
                        boolean isdisplay = driver.findElement(By.xpath("//td[contains(.,'cp1')]")).isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        LOG.info("Search by partial name shows proper result");
                        cp.searchBox.clear();
                }
                catch (Exception e)
                {
                        LOG.error("35.Search by partial name not working");
                        Assert.fail();
                }
        }
//Check Name search for Custom Processor by giving non existing Custom Processor name
        @Test (priority=36)
        public void cpSearchNonExistingName()
        {
                try
                {
                        Thread.sleep(2000);
                        cp.configuration.click();
                        cp.customProcessor.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.createBtn));
                        cp.searchIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.searchBox));
                        cp.searchBox.sendKeys("xyzzz");
                        boolean isdisplay = cp.noDataFound.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        LOG.info("Search by non existing name shows No Data Found in result");
                        cp.searchBox.clear();
                }
                catch (Exception e)
                {
                        LOG.error("36.Search by non existing name not working");
                        Assert.fail();
                }
        }
//Check search of the Custom Processor is case sensitive or not
        @Test (priority=37)
        public void cpSearchCaseSensitive()
        {
                try
                {
                        Thread.sleep(2000);
                        cp.configuration.click();
                        cp.customProcessor.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.createBtn));
                        cp.searchIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.searchBox));
                        cp.searchBox.sendKeys("CP1");
                        boolean isdisplay = driver.findElement(By.xpath("//td[contains(.,'cp1')]")).isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        LOG.info("Search is case insensitive");
                        cp.searchBox.clear();
                }
                catch (Exception e)
                {
                        LOG.error("37.Search is case sensitive");
                        Assert.fail();
                }
        }
//Check custom processor Delete popup message
        @Test (priority=38)
        public void processorDeleteMsg()
        {
                try
                {
                        cp.delete();
                        String delete=cp.deleteMsg.getText();
                        Assert.assertEquals("Are you sure you want to delete this processor?", delete);
                        LOG.info("Click on delete icon shows proper message");
                        cp.closePopup.click();
                }
                catch (Exception e)
                {
                        LOG.error("38.Click on delete icon does not show any message");
                        Assert.fail();
                }
        }
//Click on No option while deleting custom processor
        @Test (priority=39)
        public void processorDeleteNo()
        {
                try
                {
                        cp.delete();
                        cp.deleteNoBtn.click();
                        boolean isdisplay = driver.findElement(By.xpath("//td[contains(.,'cp1')]")).isDisplayed();
                        Assert.assertTrue(isdisplay);
                        LOG.info("Click on No option while deleting processor cancels the delete operation");
                }
                catch (Exception e)
                {
                        LOG.error("39.Click on No option deleted the processor");
                        Assert.fail();
                }
        }
//Delete custom processor by selecting yes option and validation message
        @Test (priority=40)
        public void processorDeleteYes()
        {
                try
                {
                        cp.delete();
                        cp.deleteYesBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(cp.successMsg));
                        String delete=cp.successMsg.getText();
                        Assert.assertEquals("Processor deleted successfully", delete);
                        LOG.info("Custom Processor deleted successfully");
                        Thread.sleep(2000);
                }
                catch (Exception e)
                {
                        LOG.error("40.Not able to delete Custom Processor");
                        Assert.fail();
                }
        }
}
