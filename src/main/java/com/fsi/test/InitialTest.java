package com.fsi.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;
import com.fsi.page.BasePage;
import com.fsi.page.CustomProcessorPage;
import com.fsi.page.EnvironmentsPage;
import com.fsi.page.ModelRegistryPage;
import com.fsi.page.MyapplicationsPage;
import com.fsi.page.ServicePoolPage;
import com.fsi.utils.PropertiesUtil;
import com.fsi.utils.UserProperty;
import org.testng.Assert;
import java.util.List;
import java.util.UUID;

public class InitialTest extends BaseTest{
	
	ServicePoolPage spool=new ServicePoolPage ();
	MyapplicationsPage myapp = new MyapplicationsPage();
	ModelRegistryPage model = new ModelRegistryPage();
	EnvironmentsPage env = new EnvironmentsPage ();
	CustomProcessorPage cp=new CustomProcessorPage();
	BasePage base = new BasePage();
	PropertiesUtil propertyUtils = PropertiesUtil.getInstance();
	WebDriverWait wait = new WebDriverWait(driver,4000);
	String arr[]=UUID.randomUUID().toString().split("-");
	
	public static final Logger LOG=Logger.getLogger(InitialTest.class);

//Delete all available applications if present
	@Test(priority=1)
	public void deleteAllApplications()
	{
		try
		{
			myapp.myApplications.click();
			myapp.fluentwait();
			wait.until(ExpectedConditions.visibilityOf(myapp.createBtn));
			List <WebElement> appcount = driver.findElements(By.xpath("//button[contains(@class, 'dropdown-toggle dropdown-toggle btn btn-link')]"));
			Actions action = new Actions(driver);
			if (appcount.size() == 0){
				LOG.info("No applications available");}
			else {
			  for (int i=0; i<appcount.size(); i++) {
				WebElement we = driver.findElement(By.xpath("//button[contains(@class, 'dropdown-toggle dropdown-toggle btn btn-link')]"));
				action.moveToElement(we).moveToElement(driver.findElement(By.xpath("//i[contains(@class, 'fa fa-times-circle')]"))).click().build().perform();
				wait.until(ExpectedConditions.visibilityOf(myapp.deleteMsg));
				myapp.deleteYesBtn.click();
				Thread.sleep(3000);
				LOG.info("Topology deleted successfully");
			      }
			    }
			boolean isdisplay = myapp.appHelpMsg.isDisplayed();
			Assert.assertTrue(isdisplay);
		}
		catch (Exception e)
		{
			LOG.error("1.Topology delete operation failed");
			Assert.fail();
		}
	}
//Delete all available environments if present
	@Test(priority=2, dependsOnMethods={"deleteAllApplications"})
	public void deleteAllEnvironments()
	{
		try
		{
			env.configuration.click();
			myapp.fluentwait();
			env.environments.click();
			Thread.sleep(2000);
			List <WebElement> envcount = driver.findElements(By.xpath("//button[@id='dropdown']"));
			if (envcount.size() == 0){
				LOG.info("No environments available");}
		    else {
			  for (int j=0; j<envcount.size(); j++){
				driver.findElement(By.xpath("//button[@data-stest='environment-actions']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[@data-stest='delete-environment']")).click();
				wait.until(ExpectedConditions.visibilityOf(env.envDeleteMsg));
				env.deleteYesBtn.click();	
				Thread.sleep(2000);
				LOG.info("Environment deleted successfully");
					}
			      }
			boolean isdisplay = env.envHelpMsg.isDisplayed();
			Assert.assertTrue(isdisplay);
		}
		catch (Exception e)
		{
			LOG.error("2.Environment delete operation failed" ,e);
			Assert.fail();
		}
	}
//Delete all available clusters if present
	@Test(priority=3, dependsOnMethods={"deleteAllEnvironments"})
	public void deleteAllClusters()
	{
		try
		{
			spool.configuration.click();
			myapp.fluentwait();
			spool.servicePool.click();
			wait.until(ExpectedConditions.visibilityOf(spool.addBtn));
			List <WebElement> count2 = driver.findElements(By.xpath("//button[@data-stest='service-pool-actions']"));
			if (count2.size() == 0){
				LOG.info("No clusters available");}
			else {
			  for (int k=0; k<count2.size(); k++){
				driver.findElement(By.xpath("//button[@data-stest='service-pool-actions']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[@data-stest='delete-service-pool']")).click();
				wait.until(ExpectedConditions.visibilityOf(spool.spoolDeleteMsg));
				spool.deleteYesBtn.click();
				Thread.sleep(2000);
				LOG.info("Cluster deleted successfully");
				   }
				 }
			boolean isdisplay = spool.spoolHelpMsg.isDisplayed();
			Assert.assertTrue(isdisplay);
		}
		catch (Exception e)
		{
			LOG.error("3.Cluster delete operation failed",e);
			Assert.fail();
		}
	}
//	Check msg on My Applications page when no data available 
	@Test (priority=4)
	public void introMsgOnApplications()
	{
		try 
		{
			myapp.myApplications.click();
			wait.until(ExpectedConditions.visibilityOf(myapp.appHelpMsg));
			String intro=myapp.appHelpMsg.getText();
			Assert.assertEquals(intro, "3 Easy Steps to get started...");
			Thread.sleep(2000);
			LOG.info("3 Easy Steps to get started intro displayed");
		}
		catch (Exception e)
		{
			LOG.error("4.Intro msg on my applications page missing");
			Assert.fail();
		}
	}	
// On My Applications page click on service pool link redirect to service pool page or not
	@Test (priority=5)
	public void spoolLinkOnApplications()
	{
		try 
		{
			myapp.myApplications.click();
			wait.until(ExpectedConditions.visibilityOf(myapp.appHelpMsg));
			myapp.spoolLink.click();
			wait.until(ExpectedConditions.visibilityOf(spool.spHeader));
			String sp1=spool.spHeader.getText();
			Assert.assertEquals(sp1, "Configuration/Service Pool");
			Thread.sleep(2000);
			LOG.info("Click on service pool link navigate to service pool page");
		}
		catch (Exception e)
		{
			LOG.error("5.Click on service pool link does not redirect to service pool page");
			Assert.fail();
		}
	}	
//	Check message on Service Pool page when no data available 
	@Test (priority=6)
	public void spoolMsgOnServicePool()
	{
		try 
		{
			myapp.myApplications.click();
			wait.until(ExpectedConditions.visibilityOf(myapp.appHelpMsg));
			myapp.spoolLink.click();
			wait.until(ExpectedConditions.visibilityOf(spool.spoolHelpMsg));
			String intro1=spool.spoolHelpMsg.getText(); 
			Assert.assertEquals(intro1, "2 Easy Steps to get started...");
			Thread.sleep(2000);
			LOG.info("2 Easy Steps to get started...");
		}
		catch (Exception e)
		{
			LOG.error("6.Intro msg on service pool page missing");
			Assert.fail();
		}
	}
//	On My Applications page click on environments link redirect to environments page or not
	@Test (priority=7)
	public void envLinkOnApplications()
	{
		try 
		{
			myapp.myApplications.click();
			wait.until(ExpectedConditions.visibilityOf(myapp.appHelpMsg));
			myapp.envLink.click();
			wait.until(ExpectedConditions.visibilityOf(env.envHeader));
			String en1=env.envHeader.getText();
			Assert.assertEquals(en1, "Configuration/Environments");
			Thread.sleep(2000);
			LOG.info("click on environments link navigate to environments page");
		}
		catch (Exception e)
		{
			LOG.error("7.Click on environments link does not redirect to environments page");
			Assert.fail();
		}
	}
//	Check msg on Environments page when no data available 
	@Test (priority=8)
	public void introMsgOnEnvironments()
	{
		try 
		{
			myapp.myApplications.click();
			wait.until(ExpectedConditions.visibilityOf(myapp.appHelpMsg));
			myapp.envLink.click();
			wait.until(ExpectedConditions.visibilityOf(env.envHelpMsg));
			String intro2=env.envHelpMsg.getText();
			Assert.assertEquals(intro2, "3 Easy Steps to get started...");
			Thread.sleep(2000);
			LOG.info("3 Easy Steps to get started intro displayed");
		}
		catch (Exception e)
		{
			LOG.error("8.Intro msg on environments page missing");
			Assert.fail();
		}
	}
//	On My Applications page click on applications link redirects to my application page
	@Test (priority=9)
	public void appLinkOnEvironements()
	{
		try 
		{
			myapp.myApplications.click();
			wait.until(ExpectedConditions.visibilityOf(myapp.appHelpMsg));
			myapp.appLink.click();
			wait.until(ExpectedConditions.visibilityOf(myapp.appHeader));
			String app1=myapp.appHeader.getText();
			Assert.assertEquals(app1, "My Applications");
			Thread.sleep(2000);
			LOG.info("Click on My Applications link navigate to My Applications page");
		}
		catch (Exception e)
		{
			LOG.error("9.Click on My Applications link does not redirect to My Applications page");
			Assert.fail();
		}
	}
// On Environments page click on service pool link redirect to service pool page or not
	@Test (priority=10)
	public void spoolLinkOnEnvironments()
	{
		try 
		{
			env.configuration.click();
			env.environments.click();
			wait.until(ExpectedConditions.visibilityOf(env.envHelpMsg));
			env.spoolLink.click();
			wait.until(ExpectedConditions.visibilityOf(spool.spHeader));
			String sp2=spool.spHeader.getText(); //get service pool page header
			Assert.assertEquals(sp2, "Configuration/Service Pool");
			Thread.sleep(2000);
			LOG.info("Click on service pool link navigate to service pool page");
		}
		catch (Exception e)
		{
			LOG.error("10.Click on service pool link does not redirect to service pool page");
			Assert.fail();
		}
	}
//	On Environments page click on environments link redirect to environments page or not
	@Test (priority=11)
	public void envLinkOnEnvironments()
	{
		try 
		{
			env.configuration.click();
			env.environments.click();
			wait.until(ExpectedConditions.visibilityOf(env.envHelpMsg));
			env.envLink.click();
			wait.until(ExpectedConditions.visibilityOf(env.envHeader));
			String en2=env.envHeader.getText();
			Assert.assertEquals(en2, "Configuration/Environments");
			Thread.sleep(2000);
			LOG.info("Click on environments link navigate to environments page");
		}
		catch (Exception e)
		{
			LOG.error("11.Click on environments link does not redirect to environments page");
			Assert.fail();
		}
	}	
//	On Environments page click on applications link redirects to my application page
	@Test (priority=12)
	public void appLinkOnEnvironments()
	{
		try 
		{
			env.configuration.click();
			env.environments.click();
			wait.until(ExpectedConditions.visibilityOf(env.envHelpMsg));
			env.appLink.click();
			wait.until(ExpectedConditions.visibilityOf(myapp.appHeader));
			String app2=myapp.appHeader.getText();
			Assert.assertEquals(app2, "My Applications");
			Thread.sleep(2000);
			LOG.info("Click on My Applications link navigate to My Applications page");
		}
		catch (Exception e)
		{
			LOG.error("12.Click on My Applications link does not redirect to My Applications page");
			Assert.fail();
		}
	}
// Check message on service pool page when no data available
	@Test (priority=13)
	public void intromsgOnServicePool()
	{
		try
		{
			spool.configuration.click();
			spool.servicePool.click();
			wait.until(ExpectedConditions.visibilityOf(spool.spoolHelpMsg));
			String intro3=spool.spoolHelpMsg.getText(); 
			Assert.assertEquals(intro3, "2 Easy Steps to get started...");
			Thread.sleep(2000);
			LOG.info("2 Easy Steps to get started...");
		}
		catch (Exception e)
		{
			LOG.error("13.Intro msg on service pool page missing");
			Assert.fail();
		}
	}
//	 Add new cluster under service pool and check notification message after successful addition
	  @Test (priority=14)
	  public void addCluster()
	  {
		try
		{
			spool.addition();
			spool.url.sendKeys(propertyUtils.getProperty(UserProperty.INITIAL_VALID_URL));
			Thread.sleep(2000);
			spool.addBtn.click();
			wait.until(ExpectedConditions.visibilityOf(spool.credentialForm));
			spool.username.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_USERNAME));
			spool.password.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_PASSWORD));
			Thread.sleep(2000);
			spool.okBtn.click();
			wait.until(ExpectedConditions.visibilityOf(spool.successMsg));
			String add=spool.successMsg.getText();
			Assert.assertEquals(add ,"Cluster added successfully");
			Thread.sleep(2000);
			LOG.info("Cluster added successfully");
		}
		catch (Exception e)
		{
			LOG.error("14.Add ambari cluster operation failed",e);
			Assert.fail();
		}
	}
//	Check msg on Environments page when cluster available under service pool page 
	@Test (priority=15)
	public void verifyMsgOnEnvironments()
	{
		try 
		{
			myapp.myApplications.click();
			wait.until(ExpectedConditions.visibilityOf(myapp.appHelpMsg));
			myapp.envLink.click();
			wait.until(ExpectedConditions.visibilityOf(env.envHelpMsg));
			String intro4=env.envHelpMsg.getText();
			Assert.assertEquals(intro4, "2 Easy Steps to get started...");
			Thread.sleep(2000);
			LOG.info("2 Easy Steps to get started intro displayed");
		}
		catch (Exception e)
		{
			LOG.error("15.Unable to verify msg on environments page when clusters available");
			Assert.fail();
		}
	}
// Add environment under environments page
	@Test (priority=16, dependsOnMethods={"addCluster"})
	public void addEnvironment()
	{
		try
		{
			env.addtion();
			env.name.sendKeys("environment1");
			env.description.sendKeys("testing");
			Thread.sleep(1000);
			List<WebElement> services = driver.findElements(By.xpath("//img[@data-service]"));
			for ( WebElement el : services ) 
			{
			    if ( !el.isSelected() ) {
			        el.click();
			        Thread.sleep(1000);
			    }
			}
			env.envOkBtn.click();
			wait.until(ExpectedConditions.visibilityOf(env.successMsg));
			String envadd=env.successMsg.getText();
			Assert.assertEquals(envadd ,"Environment added successfully");
			Thread.sleep(2000);
			LOG.info("Environment added successfully");	
		}
		catch (Exception e)
		{
			LOG.error("16.Environment add fail");
			Assert.fail();
		}
	}
//	Check My Application message when cluster and environment added
	@Test (priority=17)
	public void msgOnApplication()
	{
		try 
		{
			myapp.myApplications.click();
			wait.until(ExpectedConditions.visibilityOf(myapp.appHelpMsg));
			String intro5=myapp.appHelpMsg.getText();
			Assert.assertEquals(intro5, "2 Easy Steps to get started...");
			Thread.sleep(2000);
			LOG.info("2 Easy Steps to get started intro displayed");
		}
		catch (Exception e)
		{
			LOG.error("17.Msg display fail");
			Assert.fail();
		}
	}	
// Add application/topology under my applications
	@Test (priority=18, dependsOnMethods={"addEnvironment"})
	public void addApplication()
	{
		try
		{
			Thread.sleep(2000);
			myapp.myApplications.click();
			wait.until(ExpectedConditions.visibilityOf(myapp.createBtn));
			myapp.createBtn.click();
			myapp.newApplication.click();
			wait.until(ExpectedConditions.visibilityOf(myapp.applicationForm));
			myapp.name.sendKeys("application1");
			myapp.selectEnvironment();
			myapp.okBtn.click();
			wait.until(ExpectedConditions.visibilityOf(myapp.successMsg));
			String success=myapp.successMsg.getText();		
			Thread.sleep(3000);
			Assert.assertEquals(success, "Topology added successfully");
			myapp.myApplications.click();
			wait.until(ExpectedConditions.visibilityOf(myapp.confirmBox));
			myapp.confirmOkBtn.click();
			wait.until(ExpectedConditions.visibilityOf(myapp.appHeader));
			LOG.info("Topology added successfully");
		}
		catch (Exception e)
		{
			LOG.error("18.Topology creation failed");
			Assert.fail();
		}
	}
// Check click on arrow opens the sidebar or not
	@Test (priority=19)
	public void sideBarArrow()
	{
		try
		{
			myapp.myApplications.click();
			Thread.sleep(2000);
			env.sideBar.click();
			Thread.sleep(1000);
			String streamLine=driver.findElement(By.xpath("//span[@class='logo-lg']")).getText();
			Assert.assertEquals(streamLine, "STREAMLINE");
			LOG.info("Click on arrow opens the sidebar");
			Thread.sleep(2000);
			env.sideBar.click();
			String sl=driver.findElement(By.xpath("//span[@class='logo-mini']")).getText();
			Assert.assertEquals(sl, "SL");
			Thread.sleep(3000);
		}
		catch (Exception e)
		{
			LOG.error("19.Click on arrow not working");
			Assert.fail();
		}
	}
// Delete all custom processors if present
	@Test (priority=20)
	public void deleteAllProcessors()
	{
		try
		{
			cp.configuration.click();
			cp.customProcessor.click();
			wait.until(ExpectedConditions.visibilityOf(cp.createBtn));
			List <WebElement> cpcount = driver.findElements(By.xpath("//button[@class='btn-danger']"));
			if (cpcount.size() == 0){
				LOG.info("No custom processors available");}
		    else {
			  for (int l=0; l<cpcount.size(); l++){
				driver.findElement(By.xpath("//button[@class='btn-danger']")).click();
				wait.until(ExpectedConditions.visibilityOf(cp.deleteMsg));
				cp.deleteYesBtn.click();	
				Thread.sleep(3000);
				LOG.info("Custom Processors deleted successfully");
					}
			      }
			boolean isdisplay = cp.noDataFound.isDisplayed();
			Assert.assertTrue(isdisplay);
		}
		catch (Exception e)
		{
			LOG.error("20.Custom Processor delete operation failed");
			Assert.fail();
		}
	}
// Delete all model registry if present
	@Test (priority=21)
	public void deleteAllModels()
	{
		try
		{
			model.modelRegistry.click();
			wait.until(ExpectedConditions.visibilityOf(model.deleteIcon));
			List <WebElement> modelcount = driver.findElements(By.xpath("//button[@class='btn-danger']"));
			if (modelcount.size() == 0){
				LOG.info("No model registry available");}
			else {
				for (int m=0; m<modelcount.size(); m++){
				  driver.findElement(By.xpath("//button[@class='btn-danger']")).click();
				  wait.until(ExpectedConditions.visibilityOf(model.deleteMsg));
				  model.deleteYesBtn.click();	
				  Thread.sleep(3000);
				  LOG.info("Model Processors deleted successfully");
					}
				  }
			boolean isdisplay = model.noDataFound.isDisplayed();
			Assert.assertTrue(isdisplay);
		}
		catch (Exception e)
		{
			LOG.error("21.Model Processors delete operation failed");
			Assert.fail();
		}
	}
}		