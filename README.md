# Web Service Client - FrontEnd
### by Johan Romeo, Jonas Torjman and Sandra Jeppsson Kristiansson

## Description
We have designed a frontend client for our classmates' web service Java backend project. The assignment was to assimilate how
programing actually works where you have one team doing one part of the application and other teams doing the other
parts. As a group we had two weeks for each part (back- and frontend) with the task to solve a problem by using
CRUD, AWS, being able to understand how another team's project works and both sell and present it to an audience.

Our motivation was to gain more knowledge in how to work as a team and also understand the whole process in building a
whole project with its different parts.

This project is the frontend of a project which connects students with the courses that they have been taking. So for an
example you can get a list of all the students that's been added to the system, then you can look up one individual
to see what he/she has been studying and then also find out what that course is. All the registered users, all the
students and all the courses will be added to a MySQL database online hosted by AWS.

We keep on learning how important it is to plan, plan and plan. If you have a well sorted and thought over plan you
won't have to put in too much time into the coding itself and the risk of getting stuck or even having to start from the
beginning will reduce drastically.
What else we learned from this is how important it is to have a good and detailed documentation. Especially when you
will have to turn over the project to another group.


## Table of Contents
- [Description](#description)
- [Installation](#installation)
- [Usage](#usage)
- [Credits](#credits)
- [Dependencies](#dependencies)
- [License](#license)


### Installation
- Download the Backend project on: https://github.com/qussaikh/school_managment_project_Group_7
- Download this Frontend project on: https://github.com/JohRome/client
- Open the projects on your preferred IDEA, as an example, IntelliJ
- Start the first project in one window and run it
- Start the second project in another and run that too
- In the console of the second, please follow the instructions and make your choices.

## Usage
- Application running:
- In the first step you will be asked if you want to log in or not
- Choosing "no" you will be able to:
    - See student details
    - See all available students
    - See course details
    - See all available courses
- Choosing "yes" you will be asked to:
    - Log in, or
        - Choosing "Log in" (as a User) you will be able to:
            - Do all in the list above
            - Update your information
            - Change your password
            - Add a new student
            - Assign a course to a student
        - Choosing "Log in" (as an Admin) you will be able to:
            - Do everything in the two lists above and:
            - Delete User
            - Convert User to Admin
            - Update Student
            - Delete Student
            - Add Course
            - Update Course
            - Delete Course
    - Register
        - As a User

## Credits
* Johan Romeo (https://github.com/JohRome)
* Jonas Torjman (https://github.com/JavaThorman)
* Sandra Jeppsson Kristiansson (https://github.com/Sandra1887)

## Dependencies
* [gson](https://mvnrepository.com/artifact/com.google.code.gson/gson)
* [lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok)
* [junit jupiter api](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api)
* [junit_jupiter_engine](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine)
* [mockito-core](https://mvnrepository.com/artifact/org.mockito/mockito-core)

## Tests
+ Go to the test folder and chose the class you want to test

## License
+ [MIT](LICENSE)
