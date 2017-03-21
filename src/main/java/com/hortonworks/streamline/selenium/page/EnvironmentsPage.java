package com.hortonworks.streamline.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import com.hortonworks.streamline.selenium.test.EnvironmentsTest;

public class EnvironmentsPage extends BasePage{
        WebDriverWait wait = new WebDriverWait(driver,60);
        public static final Logger LOG=Logger.getLogger(EnvironmentsTest.class);

        @FindBy (xpath="//a[contains(@class, 'sidebar-toggle')]")
        public WebElement sideBar;

        @FindBy (xpath="//i[contains(@class, 'fa fa-sitemap')]")
        public WebElement myApplications;

        @FindBy (xpath="//i[contains(@class, 'fa fa-wrench')]")
        public WebElement configuration;

        @FindBy (xpath=".//*[@id='app_container']/div/aside/section/ul/li[5]/ul/li[4]/a")
        public WebElement environments;

        @FindBy (xpath=".//*[@id='app_container']/div/header/nav/div/div/span")
        public WebElement envHeader;

        @FindBy (xpath="//h4[@class='intro-section-title']")
        public WebElement envHelpMsg;

        @FindBy (xpath="//i[contains(@class, 'fa fa-cube')]")
        public WebElement modelRegistry;

        @FindBy (xpath=".//*[@id='app_container']/div/header/nav/div/div")
        public WebElement modelHeader;

        @FindBy (linkText="Service Pool")
        public WebElement spoolLink;

        @FindBy (linkText="Environments")
        public WebElement envLink;

        @FindBy (linkText="Application")
        public WebElement appLink;

        @FindBy (xpath="//i[contains(@class, 'fa fa-plus')]")
        public WebElement createBtn;

        @FindBy (xpath="//body//div[1]/label")
        public WebElement nameLabel;

        @FindBy (xpath="//input[@name='environmentName']")
        public WebElement name;

        @FindBy (xpath="//body//div[2]/label")
        public WebElement descLabel;

        @FindBy (xpath="//input[@name='description']")
        public WebElement description;

        @FindBy (xpath="//body//div[2]/div/div/h4")
        public WebElement selectServicesLabel;

        @FindBy (xpath="//body//div[2]/div/div/small")
        public WebElement stormSelectMsg;

        @FindBy (xpath="//button[contains(@class, 'btn btn-success')]")
        public WebElement envOkBtn;

        @FindBy (xpath="//button[contains(@class, 'btn btn-default')]")
        public WebElement envCancelBtn;

        @FindBy (xpath=".//*[@id='toast-container']/div/div/strong")
        public WebElement successMsg;

        @FindBy (xpath=".//*[@id='toast-container']/div/div/div")
        public WebElement errorMsg;

        @FindBy (xpath=".//*[@id='toast-container']/div/button")
        public WebElement cancelMsg;

        @FindBy (xpath="//button[@data-stest='environment-actions']")
        public WebElement actions;

        @FindBy (xpath="//input[@name='environmentName']")
        public WebElement editName;

        @FindBy (xpath="//input[@name='description']")
        public WebElement editDesc;

        @FindBy (xpath="//body//div[3]/button[1]")
        public WebElement editCancelBtn;

        @FindBy (xpath="//body//div[3]/button[2]")
        public WebElement editOkBtn;

        @FindBy (className="modal-title")
        public WebElement envDeleteMsg;

        @FindBy (xpath="//body//div[2]/button[2]")
        public WebElement deleteYesBtn;

        @FindBy (xpath="//body//div[2]/button[1]")
        public WebElement deleteNoBtn;


//Initial steps for Addition of environment
        public void addtion()
        {
                try
                {
                        fluentwait();
                        myApplications.click();
                        configuration.click();
                        environments.click();
                        wait.until(ExpectedConditions.visibilityOf(createBtn));
                        createBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(stormSelectMsg));
                        fluentwait();
                }
                catch (Exception e)
                {
                        LOG.error("Unable to add environment");
                }
        }
//Initial steps to Update existing environment
        public void update()
        {
                try
                {
                        fluentwait();
                        myApplications.click();
                        configuration.click();
                        environments.click();
                        wait.until(ExpectedConditions.visibilityOf(actions));
                        driver.findElement(By.xpath("//button[@data-stest='environment-actions']")).click();
                        fluentwait();
                        driver.findElement(By.xpath("//a[@data-stest='edit-environment']")).click();
                        fluentwait();
                        wait.until(ExpectedConditions.visibilityOf(stormSelectMsg));
                        Thread.sleep(2000);
                }
                catch (Exception e)
                {
                        LOG.error("Unable to edit environment",e);
                }
        }
//Initial steps to Delete existing environment
        public void delete()
        {
                try
                {
                        Thread.sleep(2000);
                        myApplications.click();
                        configuration.click();
                        environments.click();
                        wait.until(ExpectedConditions.visibilityOf(actions));
                        int result=driver.findElements(By.xpath("//button[@data-stest='environment-actions']")).size();
                        driver.findElement(By.xpath("//body//div[2]/div["+ result +"]/div/div[1]/div/div/button")).click();
                        fluentwait();
                        driver.findElement(By.xpath("//body//div[2]/div["+ result +"]/div/div[1]/div/div/ul/li[2]/a")).click();
                        wait.until(ExpectedConditions.visibilityOf(envDeleteMsg));
                        Thread.sleep(2000);
                }
                catch (Exception e)
                {
                        LOG.error("Unable to delete environment",e);
                }
        }

}
