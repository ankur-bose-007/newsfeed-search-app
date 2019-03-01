# Introduction
This is a boiler plate project for FSD Certification Practice Check. Admin can search and blacklist a news analyst. A logged in user can search for news. For each user, the system maintains the historical list of keywords searched. In the home screen the news related to the search keywords are displayed under relevant headings. Users can view the topics searched and can delete the searched topics. Fork this project and create the below specified issues in the forked project.

| **Issue Title** | **Issue Description** |
|-----------|-------------------|
| 1. Implement the functionality of the application | Create a website for users to signup and search new articles in a lively manner. Refer detailed description below this table. |
| 2. Analysis and Design | Define Screen Layout, ER Diagram, Classes and Method signatures. Include the documentation in README.md section of the project. |
| 3. Implement Repository and Service Layer | Create database and implement Service Layer using Hibernate. Unit Testing of the service method should be done using Mockito. Document the steps to build, unit test and deploy in Wiki. |
| 4. Implement Rest Controller | Create the Restful Web Service Controller using Spring MVC and create end to end tests using MockMvc library available in Spring. Document the steps to build, unit test and deploy. |
| 5. Implement Authentication Service | Modify the test cases based on inclusion of Authentication. |
| 6. Implement CI/CD | Automate the deployment of WAR using Jenkins. |
| 7. Implement Front End and consume Rest Services | Implement front end using Angular with responsive web design. Implement Unit Testing using Karma. Implement end to end testing with Protractor. |
| 8. Document the steps for build and deployment | Create a subheading for this in README.md and include the steps to deploy. |
| 9. Create docker compose for this application | |

# Application Functionality in detail (Include this in issue description of forked project)

## Role: News Analyst
1. Signup
2. Login
3. Search news articles after login (use https://newsapi.org to retrieve live new data.)
4. View the news related to the topics in search topic
5. List the searched topics
6. Remove topics from the search list
7. Logout

## Role: Admin
1. Login
2. Search News Analyst
3. Blacklist News Analyst
4. Logout

# Application Deployment

## Softwares Required
1. Visual Studio Code
2. Eclipse
3. Tomcat Server
4. MySql Workbench
5. Git 
6. MySql Server

## Get the project using Git
1. Create a folder in D: drive in which you want to download the project.
2. Open Windows Explorer.
3. Go to the folder you created.
4. Right click on the right hand side blank area.
5. Select "Git Bash Here".
6. Run following commands one by one:
    ```
    git clone https://code.cognizant.com/729706/newsfeed-search-app.git
    ```

## To start the Mysql Database
1. Inside your created folder for newsfeed-search-app project, goto "database" folder.
2. Inside database folder there will be script file with the name "data.sql".
3. Right click on the right hand side blank area.
4. Select "Open PowerShell window here".
5. Run following command:
    ```
    mysql -q root -p
    ```
6. Enter password if required
7. Run following command:
    ```
    source data.sql
    ```

## To run services
1. Inside your created folder for newsfeed-search-app project, goto "services" folder.
2. Right click on the right hand side blank area.
3. Select "Git Bash Here".
4. Run following command:   
    ```
    mvn clean package
    ```
5. A folder named "target" will be created,goto that folder.
6. copy "newsfeedservice.war" file into your tomcat/webapps folder and run tomcat using "startup.bat"
    

## To run angular
1. Inside your created folder for newsfeed-search-app,goto "angular" folder.
2. Press Shift+Right click on the right hand side blank area.
3. Select "Open PowerShell window here".
4. Run following commands:
    ```
    npm install
    ```
    ```
    ng build --prod --base-href=/newsfeedfront/ --output-path=newsfeedfront
    ```
5. A folder named "newsfeedfront" will be created inside folder angular will be created.
6. copy the folder into webapps folder of tomcat and run tomcat.


## To run karma test
1. Inside your created folder for newsfeed-search-app,goto "angular" folder.
2. Press Shift+Right click on the right hand side blank area.
3. Select "Open PowerShell window here".
4. Run following command:
    ```
    ng test
    ```

## To run protractor
1. Inside tomcat/webapps folder, goto the contents of "dist" folder.
2. Press Shift+Right on the right hand side blank area.
3. Select "Open PowerShell Window here".
4. Run the following commands one by one:
    ```
    webdriver-manager clean
    ```
    ```
    webdriver-manager update --versions.chrome=2.42 --proxy http://proxy.cognizant.com:6050
    ```
    ```
    webdriver-manager start --versions.chrome 2.42
    ```
5. Open another "PowerShell Window" and run the following commands one by one:
    ```
    protractor e2e/protractor.conf.js
    ``` 
