# Mobile Automation
~~~~
This solution is developed with below technologies to automate Web and Mobile Applications
1.Selenium 
2.Java Programing 
3.Testng.xml for test framework 
4.Maven build tool
5.Extent reporting for html report generation

Project structure:
    1. Application Test cases are under,
        -->path:src/test/java/JupiterTests
    2. Web object & reusable methods are under pages folder,
        -->path: src/main/java/pages/
    3. Reports generation, handling Drivers, test listners are under folder utilities,
        -->path: src/main/java/utilities
    4. Drivers & Test results(html report & pdf reports) are unders resources
        -->path: src/main/resources
    
Please follow below instruction to run this project,

Step1:Download or clone project from
    Repo url->https://github.com/rajumalle/SeleniumJava.git

Step2:Install below tools required:
    -->Java 11
    -->IntelliJ 2021 community version(No license required)
    -->Maven latest
     -->Configure Android Studion and Java path in Appium settings
    
Step3:Run test cases
    3.1 Open the project from Intellij and build the project
    3.2 Pull maven dependencies
    3.3 To run from local ->run from testng.xml directly
    3.4 To run from Jenkins
        -->Create maven job and configure job with repo url, java path, maven path & pom.xml path.
        -->Use maven command -- mvn clean test -DsuiteXmlFile=testng.xml

Step4:Check test results in below folder path:
    -HTML report->src/main/resources/Reports/resultsReport.html
    -Pdf reports-->src/main/resources/pdfReports

