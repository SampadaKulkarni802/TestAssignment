/* Framework description */
             
1.The framework is divided into 2 folders : main/java and tests/java
2.main/java contains the following folders : 
  app
  config
  IntegrationTests
  logger
  log4j.properties
  TestData
  UITestFramework
  testng.xml

3. tests/java contains the followingh folders :
   testngTests

4. Following is a brief description of the classes in each package/folder:
  	app :    The app that is to be tested/automated is uploaded.
  	config : Contains the config.properties file. It stores the app paths(android.ios),username/password for logging 		in.
	IntegrationTests : Contains Screens folder with locators of that screen.
			   Contains coreLogic folder with methods for that particular screen and also the generic methods 			   	   like wait,findElement,etc. 	
	logger : Contains the details of where and in what format the logs are to be displayed.Defines the logging level.
	log4j.properties : Contains the details of Logger, Appender and Layout properties.
  	TestData : Contains the .xlsx file that is used for data driven testing.
  	testng.xml : Contains the data how the tests will be executed.
	UITestFramework : Contains classes to create session, wait conditions,tap,long press,swipe,scroll,alerts,etc.
                          Each screen class extends this class so as to access its methods.
  	
	testngTests : Contains the tests that are automated using all of the above and run on the app.   
   
 