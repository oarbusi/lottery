
# Requirements


Requirements are:
- The service will allow anyone to register as a lottery participant.
- Lottery participants will be able to submit as many ballots as they want for any lottery that isn't yet closed.
- Each day at midnight the lottery event will be closed and a random lottery winner will be selected.
- All users will be able to check the winning ballot for any specific date.
- The service will have to persist the data regarding the lottery.
- Provide a README.md

# Prerequisites to run application locally 
This project uses `java 17` and `docker`
First you need to run the following command to start localstack and fill the database with some initial lottery participants

```docker-compose --project-name=lottery up -d```

With the docker container running the application is ready to be run. By default, it will run in port 8080

# Use the application

The application is a REST API that exposes the following endpoints:

```POST /register - Register a new lottery participant```
Body needs to be a JSON with the following format:
```
{
    "name": "Name of the participant",
    "email": "Email of the participant",
}
```

```GET /participants - Get all registered participants```

```POST /ballot - Submit a ballot for the current date```
Body needs to be a JSON with the following format:
```
{
    {"participant_id": 1}
}
```


```GET /winner/{date} - Get the winning ballot for a specific date```
where the date has to follow a format of yyyy-mm-dd



Improvements to be done:
- Improve testing
- Add caching
- Add authentication of some kind
