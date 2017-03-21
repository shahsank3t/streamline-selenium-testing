package com.hortonworks.streamline.selenium.page;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import com.hortonworks.streamline.selenium.test.MyapplicationsTest;

public class MyapplicationsPage extends BasePage{

        WebDriverWait wait = new WebDriverWait(driver,60);
        public static final Logger LOG=Logger.getLogger(MyapplicationsTest.class);
        Actions actions = new Actions(driver);

        @FindBy (xpath="//i[contains(@class, 'fa fa-sitemap')]")
        public WebElement myApplications;

        @FindBy (xpath=".//*[@id='app_container']/div/header/nav/div/div")
        public WebElement appHeader;

        @FindBy (xpath="//a[contains(@class, 'sidebar-toggle')]")
        public WebElement sideBar;

        @FindBy (xpath="//h4[@class='intro-section-title']")
        public WebElement appHelpMsg;

        @FindBy (xpath="//p[@class='noDataFound-text']")
        public WebElement noDataFound;

        @FindBy (linkText="Service Pool")
        public WebElement spoolLink;

        @FindBy (linkText="Environments")
        public WebElement envLink;

        @FindBy (linkText="Application")
        public WebElement appLink;

        @FindBy (xpath="//i[contains(@class, 'fa fa-cube')]")
        public WebElement modelRegistry;

        @FindBy (xpath=".//*[@id='app_container']/div/header/nav/div/div")
        public WebElement modelHeader;

        @FindBy (xpath="//i[contains(@class, 'fa fa-wrench')]")
        public WebElement configuration;

        @FindBy (xpath="//div[contains(@class, 'view-tiles clearfix')]")
        public WebElement viewPageTiles;

        @FindBy (xpath="//i[contains(@class, 'fa fa-gear')]")
        public WebElement editPageGear;

//	*****************Add /Import Application Elements******************
        @FindBy (xpath=".//*[@id='actionDropdown']")
        public WebElement createBtn;

        @FindBy (xpath=".//*[@id='add-environment']/div/ul/li[1]/a")
        public WebElement newApplication;

        @FindBy (className="modal-title")
        public WebElement applicationForm;

        @FindBy (xpath="//input[@name='topologyName']")
        public WebElement name;

        @FindBy (xpath="//body//div[2]/div/div[1]/label")
        public WebElement nameLabel;

        @FindBy (xpath="//body//div[2]/div/div[2]/label")
        public WebElement selectEnvLabel;

        @FindBy (xpath="//span[contains(@class, 'Select-arrow')]")
        public WebElement selectEnvironment;

        @FindBy (xpath=".//*[@id='add-environment']/div/ul/li[2]/a")
        public WebElement importApplication;

        @FindBy (xpath="//input[@name='files']")
        public WebElement selectJson;

        @FindBy (xpath="//body//div[2]/div/div[1]/label")
        public WebElement selectJsonLabel;

        @FindBy (xpath="//input[@name='name']")
        public WebElement topologyName;

        @FindBy (xpath="//body//div[2]/div/div[2]/label")
        public WebElement topologyNameLabel;

        @FindBy (xpath="//body//div[3]/div/div/div/span[1]/div[1]")
        public WebElement importEnvironment;

        @FindBy (xpath="//body//div[2]/div/div[3]/label")
        public WebElement environmentLabel;

        @FindBy (xpath="//button[contains(@class, 'btn btn-success')]")
        public WebElement okBtn;

        @FindBy (xpath="//body//div[3]/button[1]")
        public WebElement cancelBtn;

        @FindBy (xpath="//button[@class='close']")
        public WebElement crossSign;

        @FindBy (xpath="//body//div[3]/button[1]")
        public WebElement addCancelBtn;

        @FindBy (xpath=".//*[@id='toast-container']/div/div/strong")
        public WebElement successMsg;

        @FindBy (xpath=".//*[@id='toast-container']/div/div/div")
        public WebElement errorMsg;

        @FindBy (xpath=".//*[@id='toast-container']/div/button")
        public WebElement cancelError;

        @FindBy (xpath="//h4[@class='modal-title']")
        public WebElement confirmBox;

        @FindBy (xpath="//body//div[3]/button[2]")
        public WebElement confirmOkBtn;

        @FindBy (xpath="//body//div[3]/button[1]")
        public WebElement confirmCancelBtn;

//	*****************Action Elements*********************************
        @FindBy (xpath="//button[contains(@class, 'dropdown-toggle dropdown-toggle btn btn-link')]")
        public WebElement operations;

        @FindBy (xpath="//body//div[2]/div/div[1]/div[1]/a/h4")
        public WebElement appName;

        @FindBy (xpath="//a[@title='Refresh']")
        public WebElement refreshIcon;

        @FindBy (xpath="//a[@title='Edit']")
        public WebElement editIcon;

        @FindBy (xpath="//a[@title='Clone']")
        public WebElement cloneIcon;

        @FindBy (xpath="//body//div[3]/button[2]")
        public WebElement cloneOkbtn;

        @FindBy (xpath="//body//div[3]/button[1]")
        public WebElement cloneCancelBtn;

        @FindBy (xpath="//a[@title='Export']")
        public WebElement exportIcon;

        @FindBy (xpath="//h4[@class='modal-title']")
        public WebElement exportMsg;

        @FindBy (xpath="//body//div[2]/button[2]")
        public WebElement exportYesbtn;

        @FindBy (xpath="//body//div[2]/button[1]")
        public WebElement exportNoBtn;

        @FindBy (xpath="//i[contains(@class, 'fa fa-times-circle')]")
        public WebElement deleteApplication;

        @FindBy (xpath="//h4[@class='modal-title']")
        public WebElement deleteMsg;

        @FindBy (xpath="//body//div[2]/button[2]")
        public WebElement deleteYesBtn;

        @FindBy (xpath="//body//div[2]/button[1]")
        public WebElement deleteNoBtn;

        @FindBy (xpath="//button[@class='close']")
        public WebElement closePopup;

//	*********** Search and Sort Elements*************************
        @FindBy(xpath="//button[@id='sortDropdown']")
        public WebElement sortDropdown;

        @FindBy(xpath=".//*[@id='app_container']/div/section/div[1]/div[2]/div/div[2]/div/ul/li[1]/a")
        public WebElement sortbyName;

        @FindBy(xpath=".//*[@id='app_container']/div/section/div[1]/div[2]/div/div[2]/div/ul/li[2]/a")
        public WebElement sortbyLastUpdate;

        @FindBy(xpath=".//*[@id='app_container']/div/section/div[1]/div[2]/div/div[2]/div/ul/li[3]/a")
        public WebElement sortbyStatus;

        @FindBy (xpath="//button[contains(@class, 'searchBtn btn btn-default')]")
        public WebElement searchIcon;

        @FindBy (xpath="//input[contains(@class, 'inputAnimateIn inputAnimateOut form-control')]")
        public WebElement searchBox;

// Steps to go to myapplications
        public void myApplications()
        {
                try
                {
                        Thread.sleep(2000);
                        myApplications.click();
                        fluentwait();
                        wait.until(ExpectedConditions.visibilityOf(operations));
                        operations.click();
                        fluentwait();
                }
                catch (Exception e)
                {
                        LOG.error("Unable to click on My Applications");
                }
        }
//	Initial steps to add application
        public void addApplication()
        {
                try
                {
                        Thread.sleep(3000);
                        myApplications.click();
                        fluentwait();
                        wait.until(ExpectedConditions.visibilityOf(createBtn));
                        createBtn.click();
                        fluentwait();
                        newApplication.click();
                        wait.until(ExpectedConditions.visibilityOf(applicationForm));
                }
                catch (Exception e)
                {
                        LOG.error("Unable to add environment");
                }
        }
//	Initial steps to import application
        public void importApplication()
        {
                try
                {
                        Thread.sleep(3000);
                        myApplications.click();
                        fluentwait();
                        wait.until(ExpectedConditions.visibilityOf(createBtn));
                        createBtn.click();
                        fluentwait();
                        importApplication.click();
                        wait.until(ExpectedConditions.visibilityOf(applicationForm));
                }
                catch (Exception e)
                {
                        LOG.error("Unable to add environment", e);
                }
        }
//	Selecting 1st option from dropdown of select environment
        public void selectEnvironment()
        {
                try
                {
                        Thread.sleep(2000);
                        selectEnvironment.click();
                        fluentwait();
                        WebElement env=driver.findElement(By.xpath("//div[contains(@id,'option-0')]"));
                        actions.moveToElement(env).click().perform();
                        fluentwait();
                }
                catch (Exception e)
                {
                        LOG.error("Unable to select environment from drop down list", e);
                }
        }
// Initial steps to delete application
        public void deleteApplication()
        {
                try
                {
                        WebElement we = driver.findElement(By.xpath("//button[contains(@class, 'dropdown-toggle dropdown-toggle btn btn-link')]"));
                        actions.moveToElement(we).moveToElement(driver.findElement(By.xpath("//i[contains(@class, 'fa fa-times-circle')]"))).click().build().perform();
                        wait.until(ExpectedConditions.visibilityOf(deleteMsg));
                        Thread.sleep(2000);
                }
                catch (Exception e)
                {
                        LOG.error("Unable to delete environment", e);
                }
        }
}