package com.fsi.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;
import com.fsi.test.ServicePoolTest;

public class ServicePoolPage extends BasePage {
	
	WebDriverWait wait = new WebDriverWait(driver,60);
	public static final Logger LOG=Logger.getLogger(ServicePoolTest.class);

	@FindBy (xpath="//i[contains(@class, 'fa fa-sitemap')]")
	public WebElement myApplications;
	
	@FindBy (xpath="//a[contains(@class, 'sidebar-toggle')]")
	public WebElement sideBar;
	
	@FindBy (xpath="//i[contains(@class, 'fa fa-wrench')]")
	public WebElement configuration;
	
	@FindBy (xpath=".//*[@id='app_container']/div/aside/section/ul/li[5]/ul/li[3]/a")
	public WebElement servicePool;

	@FindBy (xpath=".//*[@id='app_container']/div/header/nav/div/div/span")
	public WebElement spHeader;
					
	@FindBy (xpath="//h4[@class='intro-section-title']")
	public WebElement spoolHelpMsg;
					
	@FindBy (xpath=".//*[@id='app_container']/div/section/div/div[1]/div/div/input")
	public WebElement url;
	
	@FindBy (xpath="//button[contains(@class, 'btn btn-success')]")
	public WebElement addBtn;
	
	@FindBy (xpath="//body//div[2]/form/div[1]/input")
	public WebElement username;
	
	@FindBy (xpath="//body//div[2]/form/div[1]/label")
	public WebElement usernameLabel;
	
	@FindBy (xpath="//body//div[2]/form/div[2]/input")
	public WebElement password;
	
	@FindBy (xpath="//body//div[2]/form/div[2]/label")
	public WebElement passwordLabel;

	@FindBy (xpath="//body//div[3]/button[2]")
	public WebElement okBtn;

	@FindBy (xpath="//body//div[3]/button[1]")
	public WebElement cancelBtn;
					
	@FindBy (xpath="//body//div[1]/div/lable")
	public WebElement validationMsg;
					
	@FindBy (xpath=".//*[@id='toast-container']/div/div/strong")
	public WebElement successMsg;
	
	@FindBy (xpath=".//*[@id='toast-container']/div/div/div")
	public WebElement errorMsg;
	
	@FindBy (xpath=".//*[@id='toast-container']/div/button")
	public WebElement cancelMsg;
	
	@FindBy (xpath="//button[@data-stest='service-pool-actions']")
	public WebElement actions;
					
	@FindBy (xpath="//h4[@class='modal-title']")
	public WebElement credentialForm;
	
	@FindBy (xpath="//body//div[2]/form/div[1]/input")
	public WebElement refreshUrl;
	
	@FindBy (xpath="//body//div[2]/form/div[2]/input")
	public WebElement refreshUser;
					
	@FindBy (xpath="//body//div[2]/form/div[3]/input")
	public WebElement refreshPasswd;
				
	@FindBy (xpath="//h4[@class='modal-title']")
	public WebElement spoolDeleteMsg;
	
	@FindBy (xpath="//body//div[2]/button[2]")
	public WebElement deleteYesBtn;
		
	@FindBy (xpath="//body//div[2]/button[1]")
	public WebElement deleteNoBtn;
	
//	Initial steps for Addition of cluster
	public void addition()
	{
		try
		{
			Thread.sleep(2000);
			myApplications.click();
			fluentwait();
			configuration.click();
			servicePool.click();
			wait.until(ExpectedConditions.visibilityOf(addBtn));
			fluentwait();
		}
		catch (Exception e)
		{
			LOG.error("Unable to add cluster");
		}
	}
//	Initial steps to Update of cluster
	public void update()
	{
		try
		{
			Thread.sleep(2000);
			myApplications.click();
			fluentwait();
			configuration.click();
			servicePool.click();
			wait.until(ExpectedConditions.visibilityOf(actions));
			driver.findElement(By.xpath("//button[@data-stest='service-pool-actions']")).click();
			fluentwait();
			driver.findElement(By.xpath("//a[@data-stest='edit-service-pool']")).click();
			wait.until(ExpectedConditions.visibilityOf(credentialForm));
		}
		catch (Exception e)
		{
			LOG.error("Unable to edit cluster");
		}
	}
//	Initial steps to Delete of cluster
	public void deleteCluster()
	{
		try
		{
			Thread.sleep(2000);
			myApplications.click();
			fluentwait();
			configuration.click();
			servicePool.click();			
			wait.until(ExpectedConditions.visibilityOf(actions));
			int result=driver.findElements(By.xpath("//button[@data-stest='service-pool-actions']")).size();
			driver.findElement(By.xpath("//body//div[2]/div["+ result +"]/div/div[1]/div/div/button")).click();
			fluentwait();
			driver.findElement(By.xpath("//body//div[2]/div["+ result +"]/div/div[1]/div/div/ul/li[2]/a")).click();
			wait.until(ExpectedConditions.visibilityOf(spoolDeleteMsg));
			fluentwait();
		}
		catch (Exception e)
		{
			LOG.error("Unable to delete cluster");
		}
	}
}
