

# Run application locally 
First you need to run the following command to start localstack and fill the database 

```docker-compose --project-name=lottery up -d```

Then you can run the application with the following command

```./gradlew runLocal```


did't add caching for simplicity sake, but it would be a good idea to add it for some endpoints

# TODO: REMOVE THIS
You will need to create a service that handles a lottery system

Requirements are:
- The service will allow anyone to register as a lottery participant.
- Lottery participants will be able to submit as many ballots as they want for any lottery that isn't yet closed.
- Each day at midnight the lottery event will be closed and a random lottery winner will be selected.
- All users will be able to check the winning ballot for any specific date.
- The service will have to persist the data regarding the lottery.
- Provide a README.md

Be creative and do not limit yourself by those requirements: use this exercise to show us what you can do best and what you are passionate about. Let us know if the requirements are not clear or if you have any questions.

Some additional information about Bynder that might be useful in the assignment:
- Incorrect domain modelling has been an issue in Bynder's past, we have solved many company-wide problems by moving towards software guided by Domain Driven Design.
- We prefer simplicity over complexity in our software solutions.
- We value readable and maintainable code.