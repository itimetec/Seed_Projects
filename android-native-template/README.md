#Read Me File
Android-Native-Template

This sample android project is a Library management system providing the following functionality.

Backend
a REST API interface to perform the CRUD operation on following URL
http://<host>/books
Frontend
A simple UI which allows the user to list the library books and perform CRUD operations.

Prerequisite:
1) NodeJs
2) Android Studio 3.0.1
3) Programming Language used is Kotlin


Setting up mock server:

1. Prerequisite is to have nodeJS installed on the system.
2. Create a folder jsonserver anywhere on the system.
3. Open Node.js command prompt.
4. Navigate to jsonserver folder created in step 2.
5. Enter command npm init. Package.json file should be auto-created after running npm init command.
6. Enter command npm install –save json-server. This command will install json-server.
7. Open package.json file and modify “scripts” tag as follows 

"scripts": {
    "mock:server": "json-server --watch db.json"
  }

8. Copy db.json file from the repo and paste it in jsonserver folder created in step 2.
9. To start the json server, run command npm run mock:server .
10. To verify that server is running, open browser and enter URL http://localhost:3000 to make sure that server is up and running.


Running Style Check and Code formatting tool
1) Open terminal or command prompt.
2) Navigate to Project's root directory.
3) Enter command gradlew ktlint and observe the result.
4) To run code formatted, enter command gradlew ktlintFormat

