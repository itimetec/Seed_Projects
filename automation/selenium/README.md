# Selenium-Java-API-UI-Framework
Selenium API and UI Automation Test Cases

# Prerequisites

>Java (version-8)

# Test Execution Details

>Build tool chosen for this framework is Maven, so to run the script we need to use the Maven commands.

To run the test: `mvn test`

To clean the target directory: `mvn clean`

>The execution command needs to be customised based on the environment we choose to execute the Test, There are some mandatory parameters need to be included.

##### Mandatory parameters
- Profile

##### Optional parameters
- browser
- userName
- password
- Test group 

### Mandatory Parameters

**1. Profile:**
>Based on your environment select proper profile among test/regression/dev/sbox or other profiles.

To set profile: `-Dspring.profiles.active=regression`


**Optional Parameters**

**1. Browser**
>Browser selection can be done with the help of this parameter. By default, Chrome is chosen for the execution

To set browser: `selenium.browserName=firefox`

**2. userName, password**
>If you want to set credentials while running the code you can use below properties

To set userName and password: `-userName=abc -password=xyz`

>for more details about the environment and its variable like baseUrl, userName, password etc.. view environment files under src/test/resources/properties/

**3. Test Group**
>If you want to run test case according to specific group use below test group.

To set group: `-groups REGRESSION`


------------

# Additional Information


>**Steps to launch chrome in debugmode:**
> 
- [Platform: Windows] Open Command Prompt
- Navigate to the installed path of chrome, default location will be `C:\Program Files (x86)\Google\Chrome\Application`
- Run in chrome in debugmode `chrome.exe -remote-debugging-port=9014 --user-data-dir="."`

> Note: Make sure you use **port number 9014** to run the chrome



