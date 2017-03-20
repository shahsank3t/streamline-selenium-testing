package com.fsi.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.fsi.page.BasePage;
import com.fsi.page.EnvironmentsPage;
import com.fsi.page.MyapplicationsPage;

import java.util.List;
import java.util.UUID;

public class EnvironmentsTest extends BaseTest{
	
	EnvironmentsPage env = new EnvironmentsPage ();
	MyapplicationsPage myapp = new MyapplicationsPage();
	BasePage base = new BasePage();
	public static final Logger LOG=Logger.getLogger(EnvironmentsTest.class);
	WebDriverWait wait = new WebDriverWait(driver,4000);
	String arr[]=UUID.randomUUID().toString().split("-");
	
//	Check Environment page header
	@Test (priority=1)
	public void envHeader()
	{
		try
		{
			base.fluentwait();
			env.configuration.click();
			env.environments.click();
			wait.until(ExpectedConditions.visibilityOf(env.envHeader)); 
			String header=env.envHeader.getText();
			Assert.assertEquals(header, "Configuration/Environments");
			Thread.sleep(2000);
			LOG.info("Click on environments link navigate to environments page");
		}
		catch(Exception e)
		{
			LOG.error("1.Environments header not displayed");
			Assert.fail();
	    }
	}	
//Navigate from environments page to other page
	@Test (priority=2)
	 public void modelRegistryNavigate()
	{
		try 
		{
			base.fluentwait();
			env.modelRegistry.click();
			wait.until(ExpectedConditions.visibilityOf(env.modelHeader));
			String mr=env.modelHeader.getText();
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
// Check what all configs shown on 'New Environment' form which opens while adding environment
	@Test (priority=3)
	public void checkEnvironmentConfigs()
	{
		try
		{
			env.addtion();
			if ((env.nameLabel.getText().contains("NAME"))&&(env.descLabel.getText().contains("DESCRIPTION"))&&(env.selectServicesLabel.getText().contains("SELECT SERVICES")))
			{
				Thread.sleep(2000);
				env.envCancelBtn.click();
				Thread.sleep(1000);
				LOG.info("Add environment shows configs as Name,Description and Select Services section");
			}
		}
		catch(Exception e)
		{
			LOG.error("3.Add environment form shows no config");
		}
	}
//	Click on OK without providing any config value
	@Test (priority=4)
	public void addEnvWithoutData()
	{
		try
		{
			env.addtion();
			env.envOkBtn.click();
			boolean isdisplay = env.stormSelectMsg.isDisplayed();
			Assert.assertTrue(isdisplay);
			Thread.sleep(2000);
			env.envCancelBtn.click();
			Thread.sleep(1000);
			LOG.info("Environment add fail");
		}
		catch(Exception e)
		{
			LOG.error("4.Environment added successfully without config values",e);
			Assert.fail();
		}
	}
//	Try to add environment by giving only Name
	@Test (priority=5)
	public void addEnvWithBlankDescription()
	{
		try
		{
			env.addtion();
			env.name.sendKeys("demo"+arr[0]);
			env.envOkBtn.click();
			boolean isdisplay = env.stormSelectMsg.isDisplayed();
			Assert.assertTrue(isdisplay);
			Thread.sleep(2000);
			env.envCancelBtn.click();
			Thread.sleep(1000);
			LOG.info("Environment add fail");
		}
		catch(Exception e)
		{
			LOG.error("5.Environment added successfully with blank description");
			Assert.fail();
		}
	}
//	Try to add environment by giving only Description
	@Test (priority=6)
	public void addEnvWithBlankName()
	{
		try
		{
			env.addtion();
			env.description.sendKeys("testing");
			env.envOkBtn.click();
			boolean isdisplay = env.stormSelectMsg.isDisplayed();
			Assert.assertTrue(isdisplay);
			Thread.sleep(2000);
			env.envCancelBtn.click();
			Thread.sleep(1000);
			LOG.info("Environment add fail");
		}
		catch(Exception e)
		{
			LOG.error("6.Environment added successfully with blank username");
			Assert.fail();
		}
	}
//	Try to add environment without selecting services
	@Test (priority=7)
	public void addwithoutServices()
	{
		try
		{
			env.addtion();
			env.name.sendKeys("demo"+ arr[0]);
			env.description.sendKeys("testing");
			env.envOkBtn.click();
			boolean isdisplay = env.stormSelectMsg.isDisplayed();
			Assert.assertTrue(isdisplay);
			Thread.sleep(2000);
			env.envCancelBtn.click();
			Thread.sleep(1000);
			LOG.info("7.Environment add fail");
		}
		catch(Exception e)
		{
			LOG.error("7.Environment added successfully without selecting services");
			Assert.fail();
		}
	}
//	Try to add environment by selecting only services
	@Test (priority=8)
	public void addwithOnlyServices()
	{
		try
		{
			env.addtion();
			driver.findElement(By.xpath("//img[@data-service='STORM']")).click();
			env.envOkBtn.click();
			boolean isdisplay = env.stormSelectMsg.isDisplayed();
			Assert.assertTrue(isdisplay);
			Thread.sleep(2000);
			env.envCancelBtn.click();
			Thread.sleep(1000);
			LOG.info("Environment add by selecting only services fail");
		}
		catch(Exception e)
		{
			LOG.error("8.Environment added successfully by selecting only services",e);
			Assert.fail();
		}
	}
// Add environment under environments page with few services
	@Test (priority=9)
	public void addEnvValid()
	{
		try
		{
			env.addtion();
			env.name.sendKeys("d1");
			env.description.sendKeys("testing");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//img[@data-service='KAFKA']")).click();
			driver.findElement(By.xpath("//img[@data-service='STORM']")).click();
			base.fluentwait();
			env.envOkBtn.click();
			wait.until(ExpectedConditions.visibilityOf(env.successMsg));
			String envadd=env.successMsg.getText();
			Assert.assertEquals(envadd ,"Environment added successfully");
			Thread.sleep(2000);
			LOG.info("Environment added successfully with valid data");		
		}
		catch (Exception e)
		{
			LOG.error("9.Environment add fail with valid data");
			Assert.fail();
		}
	}
// Add environment under environments page by selecting only storm service
	@Test (priority=10)
	public void addEnvWithOnlyStorm()
	{
		try
		{
			env.addtion();
			env.name.sendKeys("demo1" + arr[0]);
			env.description.sendKeys("testing");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//img[@data-service='STORM']")).click();
			base.fluentwait();
			env.envOkBtn.click();
			wait.until(ExpectedConditions.visibilityOf(env.successMsg));
			String envadd=env.successMsg.getText();
			Assert.assertEquals(envadd ,"Environment added successfully");
			Thread.sleep(2000);
			LOG.info("Environment added successfully by selecting only storm service");		
		}
		catch (Exception e)
		{
			LOG.error("10.Environment add fail by selecting only storm service");
			Assert.fail();
		}
	}
// Add environment under environments page by selecting services except storm
	@Test (priority=11)
	public void addEnvWithoutStorm()
	{
		try
		{
			env.addtion();
			env.name.sendKeys("demo" + arr[0]);
			env.description.sendKeys("testing");
			base.fluentwait();
			List<WebElement> services = driver.findElements(By.xpath("//img[@data-service]"));
			for ( WebElement el : services ) 
			{
			    if ( !el.isSelected() ) {
			        el.click();
			        Thread.sleep(1000);
			    }
			}
			driver.findElement(By.xpath("//img[@data-service='STORM']")).click();
			base.fluentwait();
			env.envOkBtn.click();
			boolean isdisplay = env.stormSelectMsg.isDisplayed();
			Assert.assertTrue(isdisplay);
			Thread.sleep(2000);
			env.envCancelBtn.click();
			Thread.sleep(1000);
			LOG.info("Environment add fail without selecting storm service");	
		}
		catch (Exception e)
		{
			LOG.error("11.Environment added successfully without selecting storm service");
			Assert.fail();
		}
	}
// Add environment under environments page by selecting all services
	@Test (priority=12)
	public void addEnvWithAllServices()
	{
		try
		{
			env.addtion();
			env.name.sendKeys("demo2" + arr[0]);
			env.description.sendKeys("testing");
			base.fluentwait();
			List<WebElement> services = driver.findElements(By.xpath("//img[@data-service]"));
			for ( WebElement el : services ) 
			{
			    if ( !el.isSelected() ) {
			        el.click();
			    }
			}
			base.fluentwait();
			env.envOkBtn.click();
			wait.until(ExpectedConditions.visibilityOf(env.successMsg));
			String envadd=env.successMsg.getText();
			Assert.assertEquals(envadd ,"Environment added successfully");
			Thread.sleep(4000);
			LOG.info("Environment added successfully by selecting all services");		
		}
		catch (Exception e)
		{
			LOG.error("12.Environment add fail");
			Assert.fail();
		}
	}
// Add already used environment name while adding new environment and check error message
	@Test (priority=13)
	public void duplicateEnvName()
	{
		try
		{
			env.addtion();
			env.name.sendKeys("d1");
			env.description.sendKeys("testing");
			base.fluentwait();
			driver.findElement(By.xpath("//img[@data-service='STORM']")).click();
			base.fluentwait();
			env.envOkBtn.click();
			wait.until(ExpectedConditions.visibilityOf(env.errorMsg));
			String envadd=env.errorMsg.getText();
			Assert.assertEquals(envadd ,"An exception with message [Namespace entity already exists with name d1] was thrown while\nRead more");
			Thread.sleep(3000);
			env.cancelMsg.click();
			Thread.sleep(2000);
			LOG.info("Environment add fail by giving already existing environment name");		
		}
		catch (Exception e)
		{
			LOG.error("13.Environment added successfully by giving already existing environment name");
			Assert.fail();
		}
	}
//	Edit environment by updating its name
	@Test (priority=14)
	public void editEnvName()
	{
		try
		{
			driver.navigate().refresh();
			env.update();
			env.editName.clear();
			env.editName.sendKeys("demo"+arr[0]);
			base.fluentwait();
			env.editOkBtn.click();
			wait.until(ExpectedConditions.visibilityOf(env.successMsg));
			String editName=env.successMsg.getText();
			Assert.assertEquals(editName ,"Environment updated successfully");
			Thread.sleep(2000);
			LOG.info("Environment updated successfully by updating environment name");
		}
		catch (Exception e)
		{
			LOG.error("14.Environment edit fail by updating environment name");
			Assert.fail();
		}
	}
//	Edit environment by updating its description
	@Test (priority=15)
	public void editEnvDescription()
	{
		try
		{
			env.update();
			env.editDesc.clear();
			env.editDesc.sendKeys("test"+arr[0]);
			base.fluentwait();
			env.editOkBtn.click();
			wait.until(ExpectedConditions.visibilityOf(env.successMsg));
			String editdesc=env.successMsg.getText();
			Assert.assertEquals(editdesc ,"Environment updated successfully");
			Thread.sleep(2000);
			LOG.info("Environment updated successfully by updating environment description");
		}
		catch (Exception e)
		{
			LOG.error("15.Environment edit fail by updating environment description");
			Assert.fail();
		}
	}
//	Edit environment by updating select services
	@Test (priority=16)
	public void editEnvServices()
	{
		try
		{
			env.update();
			driver.findElement(By.xpath("//img[@data-service='KAFKA']")).click();
			base.fluentwait();
			env.editOkBtn.click();
			wait.until(ExpectedConditions.visibilityOf(env.successMsg));
			String editservice=env.successMsg.getText();
			Assert.assertEquals(editservice ,"Environment updated successfully");
			Thread.sleep(2000);
			LOG.info("Environment updated successfully by updating services");
		}
		catch (Exception e)
		{
			LOG.error("16.Environment edit fail by updating services");
			Assert.fail();
		}
	}
//	Edit environment without changing anything
	@Test (priority=17)
	public void editEnvWithoutChange()
	{
		try
		{
			env.update();
			base.fluentwait();
			env.editOkBtn.click();
			wait.until(ExpectedConditions.visibilityOf(env.successMsg));
			String edit=env.successMsg.getText();
			Assert.assertEquals(edit ,"Environment updated successfully");
			Thread.sleep(2000);
			LOG.info("Environment updated successfully without any change");	
		}
		catch (Exception e)
		{
			LOG.error("17.Environment edit fail without any change");
			Assert.fail();
		}
	}
//	Select delete option and check whether it opens confirmation window or not 
	@Test (priority=18)
	public void deleteEnvMsg()
	{
		try
		{
			env.delete();
			String deletemsg=env.envDeleteMsg.getText();
			Assert.assertEquals(deletemsg ,"Are you sure you want to delete ?");
			Thread.sleep(2000);
			LOG.info("Click on delete option opens Confirmation box");		
		}
		catch (Exception e)
		{
			LOG.error("18.Click on delete option does not open Confirmation box",e);
			Assert.fail();
		}
	}
//	Click on No option while Deleting the  environment 
	@Test (priority=19)
	public void deleteEnvNo()
	{
		try
		{
			driver.navigate().refresh();
			env.delete();
			env.deleteNoBtn.click();
			boolean isdisplay = env.createBtn.isDisplayed();
			Assert.assertTrue(isdisplay);
			Thread.sleep(2000);
			LOG.info("Environment delete operation cancelled by selecting No option");
		}
		catch (Exception e)
		{
			LOG.error("19.Environment deleted successfully by selecting No option",e);
		}
	}
//	Click on Yes option while Deleting the  environment 
	@Test (priority=20)
	public void deleteEnvYes()
	{
		try
		{
			env.delete();
			env.deleteYesBtn.click();
			wait.until(ExpectedConditions.visibilityOf(env.successMsg));
			String delete=env.successMsg.getText();
			Assert.assertEquals(delete ,"Environment deleted successfully");
			Thread.sleep(3000);
			LOG.info("Environment deleted successfully by selecting Yes option");
		}
		catch (Exception e)
		{
			LOG.error("20.Environment delete fail by selecting Yes option",e);
			Assert.fail();
		}
	}
// Try to delete environment which is used under application
	@Test (priority=21)
	public void deleteUsedEnv()
	{
		try
		{
			env.myApplications.click();
			env.configuration.click();
			env.environments.click();
			wait.until(ExpectedConditions.visibilityOf(env.actions));
			driver.findElement(By.xpath("//button[@data-stest='environment-actions']")).click();
			base.fluentwait();
			driver.findElement(By.xpath("//a[@data-stest='delete-environment']")).click();
			wait.until(ExpectedConditions.visibilityOf(env.envDeleteMsg));
			base.fluentwait();
			env.deleteYesBtn.click();
			wait.until(ExpectedConditions.visibilityOf(env.errorMsg));
			String delete=env.errorMsg.getText();
			Assert.assertEquals(delete ,"Namespace refers to some Topology. So it can't be deleted.");
			Thread.sleep(2000);
			env.cancelMsg.click();
			LOG.info("Environment delete operation failed as environment used under topology");
			
		}
		catch (Exception e)
		{
			LOG.error("21.Successfully deleted environment which is used under topology");
			Assert.fail();
		}	
	}
}
