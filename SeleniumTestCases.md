# Streamline Selenium Test Cases


This document contains the list of test cases covered in selenium for each module of StreamLine and Schema Registry

## Initial Test Class
This class covers basic test cases for Service pool,Environments and My Applications pages like verification of intro message and links.Also it will check and delete data from all pages if present

* Delete all available applications if present
* Delete all available environments if present
* Delete all available clusters if present
* Check msg on My Applications page when no data available 
* On My Applications page click on service pool link redirect to service pool page or not
* Check message on Service Pool page when no data available 
* On My Applications page click on environments link redirect to environments page or not
* Check msg on Environments page when no data available
* On My Applications page click on applications link redirects to my application page
* On Environments page click on service pool link redirect to service pool page or not
* On Environments page click on environments link redirect to environments page or not
* On Environments page click on applications link redirects to my application page
* Check message on service pool page when no data available
* Add new cluster under service pool and check notification message after successful addition
* Check msg on Environments page when cluster available under service pool page 
* Add environment under environments page
* Check My Application message when cluster and environment added
* Add application/topology under my applications
* Check click on arrow opens the sidebar or not
* Delete all custom processors if present

## Service Pool Test Class
This class covers test cases for Service Pool module for valid/invalid/blank ambari url,valid/invalid/blank credentails while adding cluster,Refresh cluster action and delete cluster action

* Check ServicePool Header
* Navigate from Service Pool to other page 
* Click on add button without providing ambari url and check validation message
* Add invalid ambari url and check validation message
* Add ambari url with invalid cluster name and check validation message
* Check what all configs shown on 'Credentials' form which opens while adding cluster
* Enter blank username while adding cluster
* Enter blank password while adding cluster
* Enter invalid username while adding cluster
* Enter invalid password while adding cluster
* Add new cluster under service pool and check notification message after successful addition
* Duplicate ambari url addition and check error notification message
* Check whether user can update ambari url while refreshing the cluster
* Click on Ok without filling any config value while refreshing
* Refresh cluster successful
* Click on cancel button while Refreshing cluster
* Refresh cluster with blank username
* Refresh cluster with blank password
* Enter wrong username while Refreshing cluster
* Enter wrong password while Refreshing cluster
* Select delete option and check whether it opens confirmation window or not
* Click on No Button while Deleting the cluster cluster
* Delete cluster successfully
* Try to delete cluster which is used under environment
* Count total no of clusters available under service pool page

## Environments Test Class
This class covers test cases for Environments module for valid/invalid/blank environment while adding environment,Edit environment action and delete environment action

* Check Environment page header
* Navigate from environments page to other page
* Check what all configs shown on 'New Environment' form which opens while adding environment
* Click on OK button without providing any config value
* Try to add environment by giving only Name
* Try to add environment by giving only Description
* Try to add environment without selecting services
* Try to add environment by selecting only services
* Add environment under environments page with few services
* Add environment under environments page by selecting only storm service
* Add environment under environments page by selecting services except storm
* Add environment under environments page by selecting all services
* Add already used environment name while adding new environment and check error message
* Edit environment by updating its name
* Edit environment by updating its description
* Edit environment by updating select services
* Edit environment without changing anything
* Select delete option and check whether it opens confirmation window or not
* Click on No option while Deleting the  environment 
* Click on Yes option while Deleting the  environment
* Try to delete environment which is used under application


## Custom Processor Test Class
This class covers Custom Processor test cases for listing page and create custom processor page for valid/invalid/blank configs while creating custom processor.From processor listing page cases check for edit action,delete action and search functionality 

* Check  Custom Processor Page Header
* Navigate from CustomProcessor to Model Registry page
* Check message displayed when no data available under custom processor page
* Check what all configs available under custom processor
* Check whether streaming engine field is disabled or editable by user
* Navigate from custom processor form to other page without adding any config value and Check whether it shows Confirm Box popup or not
* Check Confirm Box popup message which opens when user try to navigate to other page before saving custom processor configs
* Click on No option of Confirm Box popup which opens when user try to navigate to other page before saving custom processor configs
* Click on Yes option of Confirm Box popup and navigate to model registry page
* Try to add new custom processor without filling mandatory fields
* Add Custom Processor successfully and verify success notification message
* Duplicate Custom processor and check error notification message
* Check Validation message for same custom processor name
* Try to add custom processor by giving invalid classname
* Try to add custom processor without adding values of config fields and check validation message
* Try to add custom processor by keeping input schema filed blank
* Try to add custom processor by giving invalid json in input schema filed
* Try to add custom processor by giving invalid json for output schema	
* Update the Output schema name while configuring Custom Processor by giving blank name
* Update the Output schema name while configuring Custom Processor by giving space between name
* Click on sign + of Output Schema and add new output schema with only []
* Click on sign + of Output Schema and add two output schema and configs as type boolean
* Click on cross sign near Output Schema which appear after adding new output stream and check its functionality
* Click on cancel button while adding the custom processor
* Validation on empty fields under Add Config Fields
* Add configs with type as number under Add Config Fields
* Uncheck Is User Input checkbox under Add Config Fields
* Uncheck Is User Input checkbox under Add Config Fields
* Edit the data of Add Config Fields
* Delete configs of Add Config Fields
* (Update CP)Check whether user can update value for 'Name' field of custom processor 
* Check what all details displayed on listing page of Custom Processor page under Configuration 
* Check whether click on search icon opens search text field or not
* Check Full Name search for Custom Processor
* Check partial Name search for Custom Processor
* Check Name search for Custom Processor by giving non existing Custom Processor name
* Check search of the Custom Processor is case sensitive or not
* Check custom processor Delete popup message
* Click on No option while deleting custom processor
* Delete custom processor by selecting yes option and validation message

## My Applications Test Class
This class covers test cases for My Applications module for Add new application with valid/invalid/blank configs,Import application with valid/invalid/blank configs.Edit,delete,refresh,clone and export application actions.Also this class covers search and sort functionnality of applications

* Check My Applications header
* Navigate from my application page to model registry page
* Navigate from Model Registry page to My Applications page
* Check configs available while adding new application
* Check configs available while importing application
* Check add application operation by keeping name field blank
* Check add application operation by keeping environment field blank
* Add new topology successfully and verify success notification message
* Check validation for same name application
* Check validation for special characters in name field of application
* Check import application operation without selecting json file
* Check import application operation by keeping topology name field blank
* Check import application operation by keeping environment field blank
* Import application successfully and verify success notification message
* Check import application by giving same name application
* Check Refresh action of application
* Click on edit option of application and check
* Click on application name and check its navigation to view mode
* Navigate from 'Application Editor' (view mode) page to 'My Applications' Page and check whether it shows comfirm popup window
* Navigate from 'Application Editor' (edit mode) page to 'My Applications' Page
* Check clone action of application without selecting environment
* Check clone action of application and check success message
* Cancel clone operation by clicking on No button while cloning
* Clone imported application
* Check pop up message displayed while exporting the topology
* Cancel the export action by clicking on No button while exporting
* Check export action of application
* Export imported topology
* Export cloned topology
* Click on search icon opens the search text box
* Search topology by giving Full name
* Search topology by giving Partial name
* Search topology by giving Non existing name
* Check search is case sensitive or not
* Check sort by Last Updated
* Check sort by Name
* Check sort by Status
* Check message shown on popup which opens when click on delete option
* Cancel the delete operation by clicking on No button while deleting the application
* Delete Application and check validation message

## Model Registry Test Class
This class covers the test cases for listing page and add model form of Model Registry module .Cases like Add new model with valid/invalid/blank configs,delete and search operations for listing page

* Check Model Registry page header
* Navigate from Model Registry to other page
* Check message displayed when no data available under model registry page
* Check what all configs available under model registry form
* Try to add model without giving any config value
* Add model with blank model name
* Add model with blank 'upload Pmml file' config
* Add model with valid values and check notification message
* Duplicate model addition and check error message
* Add model registry by giving model name with only special characters
* Try to add model by uploading invalid xml file for upload PMML file config
* Try to add model by uploading blank xml file for upload PMML file config
* Cancel model add operation by clicking on No option while adding model
* Check what all details displayed on listing page of Model Registry page
* Check whether click on search icon opens search text field or not
* Check search operation by giving Full model name
* Check search operation by giving Partial model name
* Check search operation by giving Non existing model name
* Check search operation is case sensitive or not
* Check model registry Delete popup message
* Click on No option while deleting model
* Delete model successfully by clickng on Yes option and check notification message
 