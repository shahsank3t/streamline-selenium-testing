package com.hortonworks.streamline.selenium.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import com.hortonworks.streamline.selenium.test.ApplicationEditorTest;

public class ApplicationEditorPage extends BasePage{

        WebDriverWait wait = new WebDriverWait(driver,60);
        Actions actions = new Actions(driver);
        final static Logger LOG = Logger.getLogger(ApplicationEditorTest.class);

//*********************Application editor View mode*******************
        @FindBy (xpath="//button[@id='version-dropdown']")
        public WebElement versionDropdown;

        @FindBy (xpath="//a[contains(@class,'btn btn-default')]")
        public WebElement stormBtn;

        @FindBy (xpath="//a[contains(@class,'btn btn-success')]")
        public WebElement editBtn;

        @FindBy (xpath="//div[contains(@class, 'view-tiles clearfix')]")
        public WebElement viewPageTiles;

//*********************Application editor Edit mode*******************
	@FindBy (xpath=".//*[@id='applicationName']/a")
        public WebElement topologyName;

	@FindBy (xpath="//i[contains(@class, 'fa fa-gear')]")
        public WebElement configIcon;

	@FindBy (xpath="//i[contains(@class, 'fa fa-search-plus')]")
        public WebElement zoomInIcon;

	@FindBy (xpath="//i[contains(@class, 'fa fa-search-minus')]")
        public WebElement zoomOutIcon;

        @FindBy (xpath="//a[contains(@class, 'hb lg success pull-right')]")
        public WebElement runIcon;

        @FindBy (xpath="//body//div[3]/div/p[2]")
        public WebElement status;

        @FindBy (xpath="//body//div[1]/h4/div/a")
        public WebElement configForm;

        @FindBy (xpath="//button[@class='close']")
        public WebElement crossSign;

//	*****************Component Pannel****************************
        @FindBy (xpath="//img[@src='styles/img/icon-eventhubs.png']")
        public WebElement eventHubs;

        @FindBy (xpath="//img[@src='styles/img/icon-hdfs.png']")
        public WebElement hdfsSource;

//	@FindBy (xpath="//img[@src='styles/img/icon-kafka.png']")
//	public WebElement kafkaSource;

        @FindBy (xpath="//html/body/div[2]/div/section/div/div/div/div[2]/div/div/div[2]/div/div[1]/div/ul[1]/li[3]")
        public WebElement kafkaSource;

        @FindBy (xpath="//img[@src='styles/img/icon-window.png']")
        public WebElement aggregateProcessor;

        @FindBy (xpath="//img[@src='styles/img/icon-branch.png']")
        public WebElement branchProcessor;

        @FindBy (xpath="//img[@src='styles/img/icon-join.png']")
        public WebElement joinProcessor;

        @FindBy (xpath="//img[@src='styles/img/icon-pmml.png']")
        public WebElement pmmlProcessor;

        @FindBy (xpath="//img[@src='styles/img/icon-processor.png']")
        public WebElement projectionProcessor;

        @FindBy (xpath="//img[@src='styles/img/icon-rule.png']")
        public WebElement ruleProcessor;

        @FindBy (xpath="//img[@src='styles/img/icon-cassandra.png']")
        public WebElement cassandraSink;

        @FindBy (xpath="//img[@src='styles/img/icon-druid.png']")
        public WebElement druidSink;

        @FindBy (xpath="//img[@src='styles/img/icon-hive.png']")
        public WebElement hiveSink;

        @FindBy (xpath="//img[@src='styles/img/icon-hbase.png']")
        public WebElement hbaseSink;

        @FindBy (xpath="//img[@src='styles/img/icon-hdfs.png']")
        public WebElement hdfsSink;

        @FindBy (xpath="//img[@src='styles/img/icon-jdbc.png']")
        public WebElement jdbcSink;

        @FindBy (xpath="//img[@src='styles/img/icon-kafka.png']")
        public WebElement kafkaSink;

        @FindBy (xpath="//img[@src='styles/img/icon-notification.png']")
        public WebElement notificationSink;

        @FindBy (xpath="//img[@src='styles/img/icon-opentsdb.png']")
        public WebElement opentsdbSink;

        @FindBy (xpath="//img[@src='styles/img/icon-solr.png']")
        public WebElement solrSink;

}
