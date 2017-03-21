package com.hortonworks.streamline.selenium.page;

import java.util.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hortonworks.streamline.selenium.utils.DriverManager;

import java.util.concurrent.TimeUnit;

public class BasePage {

        public WebDriver driver;

        public BasePage() {
                driver = DriverManager.getDriver();
                PageFactory.initElements(driver, this);
        }

        /*Wait Methods*/
        public void waitTime(WebElement elementName)
        {
                WebDriverWait wait = new WebDriverWait(driver, 30);
                wait.until(ExpectedConditions.visibilityOf(elementName));
        }

        public void waitForElementToBeClickable(WebElement clickableElement)
        {
                WebDriverWait wait = new WebDriverWait(driver,30);
                wait.until(ExpectedConditions.elementToBeClickable(clickableElement));
        }

        public void fluentwait()
        {
                FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
                wait.pollingEvery(250, TimeUnit.MILLISECONDS);
                wait.withTimeout(4,TimeUnit.MINUTES);
                wait.ignoring(NoSuchElementException.class);
        }

}
