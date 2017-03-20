package com.fsi.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;
import com.fsi.page.EnvironmentsPage;
import com.fsi.page.MyapplicationsPage;
import com.fsi.page.ServicePoolPage;
import com.fsi.utils.PropertiesUtil;

public class FinalTest extends BaseTest{
	
	ServicePoolPage spool=new ServicePoolPage ();
	MyapplicationsPage myapp = new MyapplicationsPage();
	EnvironmentsPage env = new EnvironmentsPage ();
	PropertiesUtil propertyUtils = PropertiesUtil.getInstance();
	WebDriverWait wait = new WebDriverWait(driver,4000);

	public static final Logger LOG=Logger.getLogger(FinalTest.class);
	
//Delete all available applications
	@Test(priority=1)
	public void deleteAllApplications()
	{
		try
		{
			myapp.myApplications.click();
			wait.until(ExpectedConditions.visibilityOf(myapp.create));
			List <WebElement> appcount = driver.findElements(By.xpath("//button[contains(@class, 'dropdown-toggle dropdown-toggle btn btn-link')]"));
			Actions action = new Actions(driver);
			  if (appcount.size() == 0){
				System.out.println("No applications available");} 
			  else {
				for (int i=0; i<appcount.size(); i++) {
					WebElement we = driver.findElement(By.xpath("//button[contains(@class, 'dropdown-toggle dropdown-toggle btn btn-link')]"));
					action.moveToElement(we).moveToElement(driver.findElement(By.xpath("//i[contains(@class, 'fa fa-times-circle')]"))).click().build().perform();
					wait.until(ExpectedConditions.visibilityOf(myapp.deleteMsg));
					myapp.deleteYes.click();
					Thread.sleep(2000);
				}
			  }
			boolean isdisplay = myapp.appHelpMsg.isDisplayed();
			Assert.assertTrue(isdisplay);
			LOG.info("Topology deleted successfully");
		}
		catch (Exception e)
		{
			LOG.error("Topology delete operation failed");
			Assert.fail();
		}
	}
//Delete all available environments
	@Test(priority=2, dependsOnMethods={"deleteAllApplications"})
	public void deleteAllEnvironments()
	{
		try
		{
			env.configuration.click();
			env.environments.click();
			Thread.sleep(2000);
			List <WebElement> envcount = driver.findElements(By.xpath("//button[@id='dropdown']"));
			System.out.println(envcount);
			 if (envcount.size() == 0){
				System.out.println("No environments available");} 
			 else {
				for (int j=0; j<envcount.size(); j++){
					driver.findElement(By.xpath("//button[@data-stest='environment-actions']")).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("//a[@data-stest='delete-environment']")).click();
					wait.until(ExpectedConditions.visibilityOf(env.envDeleteMsg));
					env.deleteYes.click();	
					Thread.sleep(2000);
				   }
		        }
			boolean isdisplay = env.envHelpMsg.isDisplayed();
			Assert.assertTrue(isdisplay);
			LOG.info("Environment deleted successfully");
		}
		catch (Exception e)
		{
			LOG.error("Environment delete operation failed" ,e);
			Assert.fail();
		}
	}
//Delete all available clusters
	@Test(priority=3, dependsOnMethods={"deleteAllEnvironments"})
	public void deleteAllClusters()
	{
		try
		{
			spool.configuration.click();
			spool.servicePool.click();
			wait.until(ExpectedConditions.visibilityOf(spool.addBtn));
			List <WebElement> count2 = driver.findElements(By.xpath("//button[@data-stest='service-pool-actions']"));
			  if (count2.size() == 0){
				System.out.println("No clusters available");} 
			  else {
				for (int k=0; k<count2.size(); k++){
					driver.findElement(By.xpath("//button[@data-stest='service-pool-actions']")).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("//a[@data-stest='delete-service-pool']")).click();
					wait.until(ExpectedConditions.visibilityOf(spool.spoolDeleteMsg));
					spool.deleteYesBtn.click();
					Thread.sleep(2000);
				}
			  }
			boolean isdisplay = spool.spoolHelpMsg.isDisplayed();
			Assert.assertTrue(isdisplay);
			LOG.info("Cluster deleted successfully");
		}
		catch (Exception e)
		{
			LOG.error("Cluster delete operation failed");
			Assert.fail();
		}
	}
}
