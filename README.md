# emerchantpay-qa-task

The QA task is separated on two projects. Java web app task is in the "java-server",
 the automation task is in separate folder automation-tests.

## Build - Web app
1. Clone repo https://github.com/ggaydarov/emerchantpay-qa-task
2. Open java-server folder
3. Run `gradle build -x test`. This will build the project without running the tests.
4. New snapshot will be generated. Go to build/libs
5. Run `java -jar java-com.server-0.0.1-SNAPSHOT.jar`

## Web app details
- Port: 11111
- Host: localhost
- Url: http://localhost:11111
- DB: jdbc:mysql://localhost:3306/java_task
- DB: username and password - "root"

###Screens and functions:
- Index - "/"
- Sign in - "/signin"
- Registration - "/registration"
- Hr welcome - "/hr"
- add new employee - "/add"
- edit employee "/editemp/{id}"
- delete employee

## Unit & integration tests
To run the tests `gradle -q test`. This command will run "test" task.

## Run Automation tests
1. Open automation-tests
2. Run `gradle build`. This will build and run all automation tests

## Known issues
1. Missing tag for integration tests, in order to not start with the gradle build.
2. Missing coverage on unit and integration tests
3. Missing logout functionality
4. Missing error handler and FE validation