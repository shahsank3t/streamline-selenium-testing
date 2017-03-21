package com.hortonworks.streamline.selenium.test;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.hortonworks.streamline.selenium.page.BasePage;
import com.hortonworks.streamline.selenium.page.MyapplicationsPage;
import com.hortonworks.streamline.selenium.page.ServicePoolPage;
import com.hortonworks.streamline.selenium.utils.PropertiesUtil;
import com.hortonworks.streamline.selenium.utils.UserProperty;

public class ServicePoolTest extends BaseTest{

        ServicePoolPage spool=new ServicePoolPage ();
        PropertiesUtil propertyUtils = PropertiesUtil.getInstance();
        MyapplicationsPage myapp = new MyapplicationsPage();
        BasePage base = new BasePage();
        public static final Logger LOG=Logger.getLogger(ServicePoolTest.class);
        WebDriverWait wait = new WebDriverWait(driver,4000);

//	Check ServicePool Header
        @Test (priority=1)
        public void spoolHeader()
        {
                try
                {

                        spool.configuration.click();
                        spool.servicePool.click();
                        wait.until(ExpectedConditions.visibilityOf(spool.spHeader));
                        String sp=spool.spHeader.getText();
                        Assert.assertEquals("Configuration/Service Pool", sp);
//			Thread.sleep(3000);
                        LOG.info("Service Pool header displayed");
                }
                catch (Exception e)
                {
                        LOG.error("1.Service Pool header not displayed");
                        Assert.fail();
                }
        }
//   Navigate from Service Pool to other page
         @Test (priority=2)
         public void myAppNavigate()
        {
                try
                {
                        myapp.myApplications.click();
                        wait.until(ExpectedConditions.visibilityOf(myapp.appHeader));
                        String ma=myapp.appHeader.getText();
                        Assert.assertEquals("My Applications", ma);
//			Thread.sleep(3000);
                        LOG.info("Successfully navigated to my application page");

                }
                catch (Exception e)
                {
                        LOG.error("2.Navigation to my applications page failed");
                        Assert.fail();
                }
        }
//	Click on add button without providing ambari url and check validation message
        @Test (priority=3)
        public void addUrlBlank()
        {
                try
                {
                        spool.addition();
                        spool.addBtn.click();
                        String lable=spool.validationMsg.getText();
                        Assert.assertEquals(lable, "This is not a valid Url");
                        Thread.sleep(2000);
                        spool.url.clear();
                        LOG.info("This is not a valid Url");
                }
                catch (Exception e)
                {
                        LOG.error("3.Able to add with blank ambari Url");
                        Assert.fail();
                }
        }
//	 Add invalid ambari url and check validation message
         @Test (priority=4)
         public void addUrlInvalid()
        {
                try
                {
                        spool.addition();
                        spool.url.sendKeys("abcd1");
                        spool.addBtn.click();
                        String lab1=spool.validationMsg.getText();
                        Assert.assertEquals(lab1, "This is not a valid Url");
                        Thread.sleep(2000);
                        spool.url.clear();
                        LOG.info("This is not a valid Url");
                }
                catch (Exception e)
                {
                        Assert.fail();
                        LOG.error("4.Able to add invalid ambari url successfully");
                }
        }
//	 Add ambari url with invalid cluster name and check validation message
         @Test (priority=5)
         public void addClusterNameInvalid()
        {
                try
                {
                        spool.addition();
                        spool.url.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_INVALID_URL));
//			Thread.sleep(2000);
                        base.fluentwait();
                        spool.addBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(spool.credentialForm));
                        spool.username.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_USERNAME));
                        spool.password.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_PASSWORD));
                        base.fluentwait();
                        spool.okBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(spool.errorMsg));
                        String clu=(spool.errorMsg).getText();
                        Assert.assertEquals(clu ,"Ambari cluster not found");
                        Thread.sleep(3000);
                        spool.cancelBtn.click(); //close the Credentials form
                        spool.cancelMsg.click(); //cancel the error msg
                        LOG.info("Ambari cluster not found");

                }
                catch (Exception e)
                {
                        LOG.error("5.Able to add ambari url with invalid cluster name successfully",e);
                        Assert.fail();
                }
        }
// Check what all configs shown on 'Credentials' form which opens while adding cluster
         @Test (priority=6)
                public void checkclusterConfigs()
                {
                 try
                 {
                         spool.addition();
                         spool.url.clear();
                         spool.url.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_URL));
                         base.fluentwait();
                         spool.addBtn.click();
                         wait.until(ExpectedConditions.visibilityOf(spool.credentialForm));

                         if ((spool.usernameLabel.getText().contains("USERNAME"))&&(spool.passwordLabel.getText().contains("PASSWORD")))
                         {
                                 Thread.sleep(2000);
                                 spool.cancelBtn.click();
                                 LOG.info("Credentials form of service pool shows configs as Username and Password");
                         }
                 }
                 catch (Exception e)
                 {
                         LOG.error("6.Credentials form shows no config");
                 }
                }
//	Enter blank username while adding cluster
        @Test (priority=7)
        public void addUserBlank()
        {
                try
                {
                        Thread.sleep(2000);
                        spool.addition();
                        spool.url.clear();
                        spool.url.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_URL));
                        base.fluentwait();
                        spool.addBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(spool.credentialForm));
                        spool.username.clear();
                        spool.password.clear();
                        spool.password.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_PASSWORD));
                        base.fluentwait();
                        spool.okBtn.click();
                        boolean isdisplay = spool.credentialForm.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        spool.cancelBtn.click(); //close the Credentials form
                        Thread.sleep(1000);
                        LOG.info("Blank username not allowed");
                }
                catch (Exception e)
                {
                        LOG.error("7.Ambari cluster added successfully by keeping username blank");
                        Assert.fail();
                }
        }
//	Enter blank password while adding cluster
        @Test (priority=8)
        public void addPasswordBlank()
        {
                try
                {
                        spool.addition();
                        spool.url.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_URL));
                        base.fluentwait();
                        spool.addBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(spool.credentialForm));
                        spool.username.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_USERNAME));
                        spool.password.clear();
                        base.fluentwait();
                        spool.okBtn.click();
                        boolean isdisplay = spool.credentialForm.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        spool.cancelBtn.click(); //close the Credentials form
                        Thread.sleep(2000);
                        LOG.info("Blank password not allowed");
                }
                catch (Exception e)
                {
                        LOG.error("8.Ambari cluster added successfully by keeping password blank");
                        Assert.fail();
                }
        }
//	Enter invalid username while adding cluster
        @Test (priority=9)
        public void addUserInvalid()
        {
                try
                {
                        spool.addition();
                        spool.url.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_URL));
                        spool.addBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(spool.credentialForm));
                        spool.username.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_INVALID_USERNAME));
                        spool.password.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_PASSWORD));
                        base.fluentwait();
                        spool.okBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(spool.errorMsg));
                        String usr=spool.errorMsg.getText();
                        Assert.assertEquals(usr ,"You have entered wrong username or password");
                        base.fluentwait();
                        spool.cancelBtn.click(); //close the Credentials form
                        Thread.sleep(1000);
                        spool.cancelMsg.click(); //cancel the error msg
                        LOG.info("You have entered wrong username or password");
                        Thread.sleep(1000);
                }
                catch (Exception e)
                {
                        LOG.error("9.Ambari cluster added successfully using invalid username");
                        Assert.fail();
                }
        }
//	Enter invalid password while adding cluster
    @Test (priority=10)
        public void addPasswordInvalid()
        {
                try
                {
                    spool.addition();
                        spool.url.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_URL));
                        spool.addBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(spool.credentialForm));
                        spool.username.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_USERNAME));
                        spool.password.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_INVALID_PASSWORD));
                        base.fluentwait();
                        spool.okBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(spool.errorMsg));
                        String passwd=spool.errorMsg.getText();
                        Assert.assertEquals(passwd ,"You have entered wrong username or password");
                        base.fluentwait();
                        spool.cancelBtn.click(); //close the Credentials form
                        Thread.sleep(1000);
                        spool.cancelMsg.click(); //cancel the error msg
                        LOG.info("You have entered wrong username or password");
                        Thread.sleep(1000);
                }
                catch (Exception e)
                {
                        LOG.error("10.Ambari cluster added successfully using invalid password");
                        Assert.fail();
                }
         }
//	Add new cluster under service pool and check notification message after successful addition
        @Test (priority=11)
        public void addClusterValid()
        {
                try
                {
                        spool.addition();
                        spool.url.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_URL));
                        spool.addBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(spool.credentialForm));
                        spool.username.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_USERNAME));
                        spool.password.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_PASSWORD));
                        base.fluentwait();
                        spool.okBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(spool.successMsg));
                        String addcluster=spool.successMsg.getText();
                        Assert.assertEquals(addcluster ,"Cluster added successfully");
                        Thread.sleep(2000);
                        LOG.info("Cluster added successfully");
                }
                catch (Exception e)
                {
                        LOG.error("11.Cluster addition failed even after adding valid configs");
                        Assert.fail();
                }
        }
//	Duplicate ambari url addition and check error notification message
        @Test (priority=12)
        public void duplicateCluster()
        {
                try
                {
                        spool.addition();
                        spool.url.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_URL));
                        base.fluentwait();
                        spool.addBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(spool.credentialForm));
                        spool.username.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_USERNAME));
                        spool.password.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_PASSWORD));
                        base.fluentwait();
                        spool.okBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(spool.errorMsg));
                        String clu1=spool.errorMsg.getText();
                        Assert.assertEquals(clu1 ,"Entity with name ["+ propertyUtils.getProperty(UserProperty.AMBARI_VALID_CLUSTER_NAME) + " [" +propertyUtils.getProperty(UserProperty.SPOOL_VALID_URL)+"]] already exists.");
                        Thread.sleep(2000);
                        spool.cancelMsg.click();
                        LOG.info("Can not add duplicate cluster");
                }
                catch (Exception e)
                {
                        LOG.error("12.Able to add duplicate ambari cluster successfully");
                        Assert.fail();
                }
        }
//	Check whether user can update ambari url while refreshing the cluster
        @Test (priority=13)
        public void refreshUrlDisabled()
        {
           try
                {
                        spool.update();
                        Thread.sleep(2000);
                    boolean disabledurl= spool.refreshUrl.isEnabled();
                    Assert.assertFalse(disabledurl);
                    Thread.sleep(2000);
                    spool.cancelBtn.click();
                    LOG.info("User cannot update ambari url as it is disabled");
                }
                catch (Exception e)
                {
                        LOG.error("13.User can update ambari url");
                        Assert.fail();
                }
        }
//	Click on Ok without filling any config value while refreshing
        @Test (priority=14)
        public void refreshWithoutData()
        {
            try
                {
		Thread.sleep(2000);
		spool.update();
                        spool.okBtn.click();
                        boolean isdisplay = spool.credentialForm.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        spool.cancelBtn.click(); //close the Credentials form
                        LOG.info("Blank username and password not allowed");
                }
                catch (Exception e)
                {
                        LOG.error("14.Refresh cluster Passed with blank credentials");
                        Assert.fail();
                }
        }
//	Refresh cluster successful
        @Test (priority=15)
        public void refreshSuccess()
        {
           try
                {
                    Thread.sleep(2000);
                    spool.update();
                        spool.refreshUser.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_USERNAME));
                        spool.refreshPasswd.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_PASSWORD));
                        base.fluentwait();
                        spool.okBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(spool.successMsg));
                        String ref=spool.successMsg.getText();
                        Assert.assertEquals(ref ,"Process completed successfully");
                        LOG.info("Cluster refreshed successfully");
                        Thread.sleep(2000);
                }
                catch (Exception e)
                {
                        LOG.error("15.Refresh cluster failed even after adding valid configs");
                        Assert.fail();
                }
        }
//	Click on cancel button while Refreshing cluster
        @Test (priority=16)
        public void refreshCancel()
        {
           try
                {
                        spool.update();
                        spool.refreshUser.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_USERNAME));
                        spool.refreshPasswd.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_PASSWORD));
                        base.fluentwait();
                        spool.cancelBtn.click();
                        boolean isdisplay = spool.addBtn.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        LOG.info("Cluster refreshed cancelled");
                }
                catch (Exception e)
                {
                        LOG.error("16.Refresh cluster successful enevn after clicking on cancel butten");
                        Assert.fail();
                }
        }
//	Refresh cluster with blank username
        @Test (priority=17)
        public void refreshBlankUser()
        {
            try
                {
		Thread.sleep(2000);
		spool.update();
                        spool.refreshPasswd.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_PASSWORD));
                        spool.okBtn.click();
                        boolean isdisplay = spool.credentialForm.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        spool.cancelBtn.click(); //close the Credentials form
                        LOG.info("Blank username not allowed");
                        Thread.sleep(2000);
                }
                catch (Exception e)
                {
                        LOG.error("17.Refresh cluster Passed by keeping username blank");
                        Assert.fail();
                }
        }
//	Refresh cluster with blank password
        @Test (priority=18)
        public void refreshBlankPassword()
        {
                try
                {
                        spool.update();
                        spool.refreshUser.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_USERNAME));
                        spool.okBtn.click();
                        boolean isdisplay = spool.credentialForm.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        spool.cancelBtn.click(); //close the Credentials form
                        LOG.info("Blank password not allowed");
                        Thread.sleep(2000);
                }
                catch (Exception e)
                {
                        LOG.error("18.Refresh cluster failed by keeping password blank");
                        Assert.fail();
                }
        }
//  Enter wrong username while Refreshing cluster
        @Test (priority=19)
        public void refreshUserWrong()
        {
                try
                {
                        spool.update();
                        spool.refreshUser.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_INVALID_USERNAME));
                        spool.refreshPasswd.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_PASSWORD));
                        spool.okBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(spool.errorMsg));
                        String usr1=spool.errorMsg.getText();
                        Assert.assertEquals(usr1 ,"You have entered wrong username or password");
                        Thread.sleep(2000);
                        spool.cancelBtn.click(); //close the Credentials form
                        Thread.sleep(1000);
                        spool.cancelMsg.click(); //cancel the error msg
                        LOG.info("You have entered wrong username or password");
                        Thread.sleep(1000);
                }
                catch (Exception e)
                {
                        LOG.error("19.Refresh cluster passed by giving invalid username");
                        Assert.fail();
                }
        }
//	Enter wrong password while Refreshing cluster
        @Test (priority=20)
        public void refreshPasswordWrong()
        {
                try
                {
                    spool.update();
                    spool.refreshUser.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_VALID_USERNAME));
                        spool.refreshPasswd.sendKeys(propertyUtils.getProperty(UserProperty.SPOOL_INVALID_PASSWORD));
                        spool.okBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(spool.errorMsg));
                        String passw1=spool.errorMsg.getText();
                        Assert.assertEquals(passw1 ,"You have entered wrong username or password");
                        Thread.sleep(3000);
                        spool.cancelBtn.click(); //close the Credentials form
                        Thread.sleep(1000);
                        spool.cancelMsg.click(); //cancel the error msg
                        LOG.info("You have entered wrong username or password");
                        Thread.sleep(1000);
                }
                catch (Exception e)
                {
                        LOG.error("20.Refresh cluster passed by giving invalid password");
                        Assert.fail();
                }
        }
//	Select delete option and check whether it opens confirmation window or not
        @Test (priority=21)
        public void deleteClusterMsg()
        {
                try
                {
                        spool.deleteCluster();
                        String deletemsg=spool.spoolDeleteMsg.getText();
                        Assert.assertEquals(deletemsg ,"Are you sure you want to delete ?");
                        Thread.sleep(2000);
                        spool.deleteNoBtn.click();
                        Thread.sleep(1000);
                        LOG.info("Click on delete option opens Confirmation box");
                }
                catch (Exception e)
                {
                        LOG.error("21.Delete cluster popup message not displayed");
                        Assert.fail();
                }
        }
//	Click on No while Deleting the cluster cluster
        @Test (priority=22)
        public void deleteClusterNo()
        {
                try
                {
                        spool.deleteCluster();
                        spool.deleteNoBtn.click();
                        boolean isdisplay = spool.addBtn.isDisplayed();
                        Assert.assertTrue(isdisplay);
                        Thread.sleep(2000);
                        LOG.info("Click on no option while deleting cluster cancel the operation");
                }
                catch (Exception e)
                {
                        LOG.error("22.Click on no butten while deleting cluster failed");
                }
        }
//	Delete cluster successfully
        @Test (priority=23)
    public void deleteClusterYes()
        {
           try
                {
                        spool.deleteCluster();
                        spool.deleteYesBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(spool.successMsg));
                        String del=spool.successMsg.getText();
                        Assert.assertEquals(del ,"cluster deleted successfully");
                        Thread.sleep(2000);
                        LOG.info("cluster deleted successfully");
                }
                catch (Exception e)
                {
                        LOG.error("23.Delete cluster failed even after selecting yes option");
                        Assert.fail();
                }
        }
//	Try to delete cluster which is used under environment
        @Test (priority=24)
    public void deleteUsedCluster()
    {
                try
                {
                        spool.configuration.click();
                        spool.servicePool.click();
                        wait.until(ExpectedConditions.visibilityOf(spool.actions));
                        driver.findElement(By.xpath("//button[@data-stest='service-pool-actions']")).click();
                        base.fluentwait();
                        driver.findElement(By.xpath("//a[@data-stest='delete-service-pool']")).click();
                        wait.until(ExpectedConditions.visibilityOf(spool.spoolDeleteMsg));
                        base.fluentwait();
                        spool.deleteYesBtn.click();
                        wait.until(ExpectedConditions.visibilityOf(spool.errorMsg));
                        String del=spool.errorMsg.getText();
                        Assert.assertEquals(del ,"This cluster is shared with some environment. So it can't be deleted.");
                        Thread.sleep(2000);
                        spool.cancelMsg.click();
                        Thread.sleep(2000);
                        LOG.info("Cluster delete action failed");

                }
                catch (Exception e)
                {
                        LOG.error("24.Cluster used under environment deleted successfully");
                        Assert.fail();
                }
    }
//	Count total no of clusters available under service pool page
        @Test (priority=25)
        public void numberOfClusters() throws InterruptedException
        {
                        spool.configuration.click();
                        spool.servicePool.click();
                        Thread.sleep(2000);
                        List <WebElement> count = driver.findElements(By.className("col-md-4"));
                        int clusterCount = count.size();
                        System.out.println("Total number of cluster(s) available: " + clusterCount);
                        LOG.info("Able to get count of total no of available clusters");
        }
}
