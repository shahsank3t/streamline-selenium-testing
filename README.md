# README #

# **Streamline Selenium Quick Start** #

This document details the steps involved in Streamline Selenium Testing  
1.  Prerequisites  
2. Clone the selenium code  
3.  Update Automation.properties  
4.  Run Test Suite 

## Prerequisites ##
### Create xml , json files and jars ###
 1. For Custom Processor you need to upload streamline "Jar" file . Sample jar file will be available at location $streamline/examples/processors/target/streamline-examples-processors-0.1.0-SNAPSHOT.jar. Also for negative testing use any random jar file
 2. To check Import functionality of Application "Json" file is required. For sample json export any application.For testing json with configured components and nonconfigured components require.Also for negative testing create blank json and invalid json files
 3. To add Model under Model Registry "Xml" file is required.Create valid ,invalid and blank xml files


## Install TestNG in Eclipse ##
Install TestNG using any one of the following method
### 1st Method: Install TestNG In Eclipse Directly From The Eclipse Marketplace. ###
1. Launch the Eclipse IDE and from Help menu, click “Eclipse Marketplace”.
2. In the “Find” input field, write “TestNG” and press enter to search. The search will land you the desired TestNG plugin listed in the result section. 3. There you’ll see an “Install” button with every result that appears in the search. Press it to continue installing the TestNG for Eclipse.
### 2nd Method: Installing TestNG Plugin In Eclipse Using The “Install New Software” ###
1. Launch the Eclipse IDE and from Help menu, click “Install New Software”.
2. You will see a dialog window, click “Add” button.
3. Type name as you wish, lets take “TestNG” and type “http://beust.com/eclipse/” as location. Click OK.
4. You come back to the previous window but this time you must see TestNG option in the available software list. Just Click TestNG and press “Next” button.
5. Click “I accept the terms of the license agreement” then click Finish.
6. You may or may not encounter a Security warning, if in case you do just click OK.
7. Click Next again on the succeeding dialog box until it prompts you to Restart the Eclipse.

## Clone Selenium Code ##
git clone [git@github.com:shahsank3t/streamline-selenium-testing.git]

## Modify automation.properties file ##
Open automation.properties file through eclipse and update belwo properties
### Driver and other properties ###
host.url [Enter streamline host and port number]

driver.chrome.path [Enter chrome driver path]
### Service Pool properties ###
spool.valid.url.first & spool.valid.url.second [Enter two different valid ambari URLs in proper format]

ambari.valid.first.cluster.name [Enter ambari cluster name of first url]

spool.invalid.url [Enter invalid cluster name url]

spool.valid.username [Enter valid username]

spool.valid.password [Enter valid password]

spool.invalid.username [Enter valid username]

spool.invalid.password [Enter invalid password]
### Custom Processor properties ###
custom.processor.valid.jar.first.path [Enter valid first jarfile path]

custom.processor.valid.jar.second.path [Enter valid second jarfile path]

custom.processor.valid.classname [Enter valid classname of the first jarfile]

custom.processor.invalid.jar.path [Enter invalid jarfile path]

custom.processor.invalid.classname [Enter invalid or random classname path]
### My Application properties ###
myapplication.valid.configured.json.path [Enter valid json file path having configured components]

myapplication.invalid.json.path [Enter invalid json file path]

myapplication.blank.json.path [Enter blank json file path]
### Model Registry properties ###
model.valid.xml.path [Enter valid xml file path]

model.invalid.xml.path [Enter invalid xml file path]

model.blank.xml.path [Enter blank xml file path]

## Run Test Suite ##
1. In cloned project find testng.xml file through eclipse.
2. Right click on testng.xml file, you will see "Run as" option.
3. Find "TestNG Suite" option and click on it

**Note: To run individual Test class ,right click on desired Test class and search for "Run as" option.Then find "TestNG Test" option and click on it**
