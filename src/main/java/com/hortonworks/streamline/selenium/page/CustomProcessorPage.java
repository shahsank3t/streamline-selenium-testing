package com.hortonworks.streamline.selenium.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import com.hortonworks.streamline.selenium.test.ServicePoolTest;

public class CustomProcessorPage extends BasePage{
        WebDriverWait wait = new WebDriverWait(driver,60);
        public static final Logger LOG=Logger.getLogger(ServicePoolTest.class);

//	*********Elements from Custom Processor listing page***********
        @FindBy (xpath="//i[contains(@class, 'fa fa-sitemap')]")
        public WebElement myApplications;

        @FindBy (xpath=".//*[@id='app_container']/div/aside/a/i")
        public WebElement sideBar;

        @FindBy (xpath=".//*[@id='app_container']/div/aside/section/ul/li[4]/a")
        public WebElement modelRegistry;

        @FindBy (xpath=".//*[@id='app_container']/div/header/nav/div/div")
        public WebElement modelHeader;

        @FindBy (xpath=".//*[@id='app_container']/div/aside/section/ul/li[5]/a")
        public WebElement configuration;

        @FindBy (xpath=".//*[@id='app_container']/div/aside/section/ul/li[5]/ul/li[1]/a")
        public WebElement customProcessor;

        @FindBy (xpath=".//*[@id='app_container']/div/header/nav/div/div/span")
        public WebElement cpHeader;

        @FindBy (className="noDataFound-text")
        public WebElement noDataFound;

        @FindBy (xpath="//i[contains(@class, 'fa fa-plus')]")
        public WebElement createBtn;

        @FindBy (xpath="//button[@class='btn-warning']")
        public WebElement editIcon;

        @FindBy (xpath="//button[@class='btn-danger']")
        public WebElement deleteIcon;

        @FindBy (xpath="//h4[@class='modal-title']")
        public WebElement deleteMsg;

        @FindBy (xpath="//button[contains(@class, 'btn btn-danger')]")
        public WebElement deleteNoBtn;

        @FindBy (xpath="//button[contains(@class, 'btn btn-success')]")
        public WebElement deleteYesBtn;

        @FindBy (xpath="//button[contains(@class, 'searchBtn btn btn-default')]")
        public WebElement searchIcon;

        @FindBy (xpath="//input[contains(@class, 'inputAnimateIn inputAnimateOut form-control')]")
        public WebElement searchBox;

//	*********Elements from 'Custom Processor' form***********
        @FindBy (xpath="//input[@name='streamingEngine']")
        public WebElement streamLineEngine;

        @FindBy (xpath=".//*[@id='app_container']//form/div[1]/label")
        public WebElement streamEngineLabel;

        @FindBy (xpath=".//*[@id='app_container']//form/div[2]/label")
        public WebElement namelabel;

        @FindBy (xpath="//input[@name='name']")
        public WebElement name;

        @FindBy (xpath=".//*[@id='app_container']//form/div[2]/p")
        public WebElement sameNameMsg;

        @FindBy (xpath=".//*[@id='app_container']//form/div[3]/label")
        public WebElement descLabel;

        @FindBy (xpath="//input[@name='description']")
        public WebElement description;

        @FindBy (xpath="//input[@name='customProcessorImpl']")
        public WebElement className;

        @FindBy (xpath=".//*[@id='app_container']//form/div[4]/label")
        public WebElement classLabel;

        @FindBy (xpath=".//*[@id='app_container']//form/div[5]/label")
        public WebElement uploadJarLabel;

        @FindBy (className="hidden-file-input")
        public WebElement uploadJar;

        @FindBy (xpath=".//*[@id='app_container']//form/div[6]/label")
        public WebElement configFieldLabel;

        @FindBy (xpath="//button[contains(@class, 'btn btn-sm btn-primary')]")
        public WebElement addConfigBtn;

        @FindBy (xpath="//body//form/div[6]/div[2]/p")
        public WebElement missingConfigMsg;

        @FindBy (xpath=".//*[@id='app_container']//form/div[8]/label")
        public WebElement inputSchemaLable;

        @FindBy (xpath="//body//div[8]//div[6]/div[1]/div/div/div/div[5]/div")
        public WebElement inputSchemaBox;

        @FindBy (xpath="//div[contains(@class, 'CodeMirror-gutter CodeMirror-lint-markers')]")
        public WebElement inputcrossSign;

        @FindBy (xpath=".//*[@id='app_container']//form/div[9]/label")
        public WebElement outputSchemaLable;

        @FindBy (xpath="//pre[contains(@class='CodeMirror-lines')]")
        public WebElement outputSchemaBox;

        @FindBy (xpath="//div[@class='CodeMirror-lint-marker-error']")
        public WebElement outputJsonCrossSign;

        @FindBy (xpath=".//*[@id='streamName']/a")
        public WebElement outputSchemaName;

        @FindBy (xpath="//input[@data-index='0']")
        public WebElement outputSchemaPopup;

        @FindBy (xpath="//button[contains(@class, 'btn-link btn-xs btn btn-success')]")
        public WebElement schemaNameYesSign;

        @FindBy (xpath="//button[contains(@class, 'btn-link btn-xs btn btn-danger')]")
        public WebElement schemaNameCancelSign;

        @FindBy (xpath="//div[@class='editable-error']")
        public WebElement OutputSchemaNameValidation;

        @FindBy (xpath="//i[contains(@class, 'fa fa-plus')]")
        public WebElement opStreamPlusSign;

        @FindBy (xpath="//i[contains(@class, 'fa fa-times-circle')]")
        public WebElement opStreamCancel;

        @FindBy (xpath="//button[contains(@class, 'btn btn-success')]")
        public WebElement saveBtn;

        @FindBy (xpath=".//*[@id='app_container']//form/div[10]/div/button[1]")
        public WebElement cancelBtn;

        @FindBy (xpath="//body//div[2]/div/div/div[2]/p")
        public WebElement confirmBoxMsg;

        @FindBy (xpath="//body//div[2]/div/div/div[3]/button[1]")
        public WebElement confirmBoxCancelBtn;

        @FindBy (xpath="//body//div[2]/div/div/div[3]/button[2]")
        public WebElement confirmBoxOkBtn;

        @FindBy (xpath=".//*[@id='toast-container']/div/div/strong")
        public WebElement successMsg;

        @FindBy (xpath=".//*[@id='toast-container']/div/div/div")
        public WebElement errorMsg;

        @FindBy (xpath=".//*[@id='toast-container']/div/button")
        public WebElement closeError;

        @FindBy (xpath="//button[@class='close']")
        public WebElement closePopup;

//	*********Elements from 'Add Config Field' form***********
        @FindBy (className="modal-title")
        public WebElement addConfigForm;

        @FindBy (xpath="//input[@name='fieldName']")
        public WebElement fieldName;

        @FindBy (xpath="//input[@name='uiName']")
        public WebElement uiName;

        @FindBy (xpath="//input[@name='isOptional']")
        public WebElement isOptionalCheckBox;

        @FindBy (xpath="//div[contains(@class, 'Select-placeholder')]")
        public WebElement typeDropdown;

        @FindBy (xpath="//div[contains(@id,'option-0')]")
        public WebElement typeString;

        @FindBy (xpath="//div[contains(@id,'option-1')]")
        public WebElement typeNumber;

        @FindBy (xpath="//div[contains(@id,'option-2')]")
        public WebElement typeBoolean;

        @FindBy (xpath="//input[@name='defaultValue']")
        public WebElement defaultValue;

        @FindBy (xpath="//input[@name='isUserInput']")
        public WebElement isUserInputCheckBox;

        @FindBy (xpath="//input[@name='tooltip']")
        public WebElement tooltip;

        @FindBy (xpath="//body//div[3]/button[2]")
        public WebElement addConfigOkBtn;

        @FindBy (xpath="//body//div[3]/button[1]")
        public WebElement addConfigcancelBtn;

        @FindBy (xpath="//button[@class='btn-warning']")
        public WebElement editAddConfigs;

        @FindBy (xpath="//button[@class='btn-danger']")
        public WebElement deleteAddConfigs;


//	Initial steps for Addition of custom processor
        public void addition()
        {
                try
                {
                        Thread.sleep(3000);
                        myApplications.click();
                        fluentwait();
                        configuration.click();
                        customProcessor.click();
                        wait.until(ExpectedConditions.visibilityOf(createBtn));
                        createBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(streamLineEngine));
                        Thread.sleep(2000);
                }
                catch (Exception e)
                {
                        LOG.error("Unable to add custom processor",e);
                }
        }
// Steps to add text to input schema box
        public void addInputSchema()
        {
                try
                {
                        Thread.sleep(2000);
                        Actions actions = new Actions(driver);
                        actions.moveToElement(inputSchemaBox);
                        actions.click();
                        actions.sendKeys("[]");
                        actions.build().perform();
                        Thread.sleep(2000);
                }
                catch (Exception e)
                {
                        LOG.error("Unable to add text to input schema field");
                }
        }
//Steps to add config fields
        public void addConfigs()
        {
                try
                {
                        Thread.sleep(3000);
                        addConfigBtn.click();
                        fluentwait();
                        wait.until(ExpectedConditions.visibilityOf(addConfigForm));
                        fieldName.sendKeys("fieldname");
                        uiName.sendKeys("uiname");
                        typeDropdown.click();
                        fluentwait();
                        typeString.click();
                        defaultValue.sendKeys("yoyo");
                        tooltip.sendKeys("enter name");
                        addConfigOkBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(inputSchemaLable));
                }
                catch (Exception e)
                {
                        LOG.error("Unable to add configs under add config section");
                }
        }
//Initial Steps to delete custom processor
        public void delete()
        {
                try
                {
                        Thread.sleep(3000);
                        myApplications.click();
                        fluentwait();
                        configuration.click();
                        customProcessor.click();
                        wait.until(ExpectedConditions.visibilityOf(createBtn));
                        deleteIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(deleteMsg));
                        Thread.sleep(2000);
                }
                catch (Exception e)
                {
                        LOG.error("Unable to delete custom processor");
                }
        }
}
