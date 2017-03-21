package com.hortonworks.streamline.selenium.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import com.hortonworks.streamline.selenium.test.ServicePoolTest;

public class ModelRegistryPage extends BasePage{
        WebDriverWait wait = new WebDriverWait(driver,60);
        public static final Logger LOG=Logger.getLogger(ServicePoolTest.class);

//	*********Elements from Model Registry listing page***********
        @FindBy (xpath="//i[contains(@class, 'fa fa-sitemap')]")
        public WebElement myApplications;

        @FindBy (xpath=".//*[@id='app_container']/div/aside/section/ul/li[4]/a")
        public WebElement modelRegistry;

        @FindBy (xpath=".//*[@id='app_container']/div/header/nav/div/div")
        public WebElement modelHeader;

        @FindBy (xpath="//p[contains(@class,'noDataFound-text')]")
        public WebElement noDataFound;

        @FindBy (xpath="//a[contains(@class, 'hb lg success actionDropdown')]")
        public WebElement createBtn;

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

        @FindBy (xpath="//body//div[1]/div/span/input")
        public WebElement searchBox;

        @FindBy (xpath=".//*[@id='toast-container']/div/div")
        public WebElement successMsg;

        @FindBy (xpath=".//*[@id='toast-container']/div/div/div")
        public WebElement errorMsg;

        @FindBy (xpath="//button[@class='toast-close-button']")
        public WebElement closeError;

//	*********Elements from Model Registry Form ***********
        @FindBy (xpath="//h4[(@class='modal-title')]")
        public WebElement modelForm;

        @FindBy (xpath="//body//div[2]/div/div[1]/label")
        public WebElement modelNameLabel;

        @FindBy (xpath="//body//div[1]/div/input")
        public WebElement modelName;

        @FindBy (xpath="//body//div[2]/div/div[2]/label")
        public WebElement uploadPmmlLabel;

        @FindBy (xpath="//body//div[2]/div/input[2]")
        public WebElement uploadPmml;

        @FindBy (xpath="//button[contains(@class,'btn btn-success')]")
        public WebElement okBtn;

        @FindBy (xpath="//body//div[3]/button[1]")
        public WebElement cancelBtn;

        @FindBy (xpath="//button[@class='close']")
        public WebElement closePopup;

//	Initial steps for Addition of model
        public void addition()
        {
                try
                {
                        Thread.sleep(3000);
                        modelRegistry.click();
                        fluentwait();
                        wait.until(ExpectedConditions.visibilityOf(createBtn));
                        createBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(modelForm));
                        Thread.sleep(1000);
                }
                catch (Exception e)
                {
                        LOG.error("Unable to add custom processor",e);
                }
        }
//	Initial steps for deletion of model
        public void delete()
        {
                try
                {
                        Thread.sleep(3000);
                        modelRegistry.click();
                        fluentwait();
                        wait.until(ExpectedConditions.visibilityOf(deleteIcon));
                        deleteIcon.click();
                        wait.until(ExpectedConditions.visibilityOf(deleteMsg));
                        Thread.sleep(1000);
                }
                catch (Exception e)
                {
                        LOG.error("Unable to delete custom processor",e);
                }
        }
}
