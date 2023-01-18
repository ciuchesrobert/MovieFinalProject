# **Final Movie Project**


## Overview
After 8 hard weeks of training, Engineering 141 put their skills to the test in their final group project.
The task in hand was to develop a RESTful API and Web Application using a MongoDB Backend. The Frontend would need
to provide full **C**reate, **R**ead, **U**pdate and **D**elete access to the sample_mflix database. The project would need a security mechanism
and also a way to allow an administrator to create a schedule for a theatre.

This was a 5 day project and we worked in scrum team of 14 people

## Specific Requirements

### Scrum
- We assigned roles with Abdullah as our Scrum Master and Rob as our Git guru

- A Product Backlog was created and increments from there were chosen for our Sprints
- Each sprint consisted of a Sprint Plan, Backlog, Retrospective and Review
- Each day included a daily stand-up meeting where we discussed our progress
and any changes that needed to be made

### Product

1. The product owner wants to explore the possibility of having a MongoDB and cloud-based solution for accessing information about movies, allowing users to post or read comments on movies and managing a film schedule for cinemas.

2. The two require products:
  - A RESTful API which allows full CRUD access to the existing data for these 4 collections
 - A Web application providing a browser-based interface to the same set of existing collections


3.  a new feature is required to allow an administrator to create a schedule for a theatre, adding films to the roster and specifying showing times



### Required Technologies

- The persistence mechanism must be MongoDB
- The database must be hosted on MongoDB Atlas using the "Shared" (free) tier hosted on AWS
- The database must be based on the sample_mflix database, which is provided for use within MongoDB - this tutorial explains how to install the sample
- Spring Boot must be used for the API and Web front ends
- Spring Data MongoDB must be used for accessing the database from both the API and the Web application
The code repository for the project must be hosted on GitHub




## User Stories

Our first step towards creating the programme was to write user stories to cement our
understanding of the require product. We had to think of what the user would want from
the product. We also had to think of how a developer would want the code to be formatted
in case they want to make alterations.

| As a user I want... | As an administrator I want...| As a developer I want... |
| --------            | ---------------------| ---------------------- |
| Read access to the database so I can see details about movies and comments |Create, Update and Delete Access so I can make changes to the database and the website| To have a security mechanism to provide the right level of access to users and administrators|
|There to be clear navigation on the website so that I can find the information I need| A mechanism to create a theatre schedule so I can show the correct information about show times| To use CSS and/or Bootstrap so that I can design user friendly webpages|
| To read comments people have written to help me decide what movies to watch | There to be a way I can change the comments in case there are inappropriate ones | To use features of Spring Web MVC so that I can easily create web controllers to navigate to endpoints

---
## Sprint 1

### Plan
Scrum Master, Abdullah led the first sprint plan and we divided the tasks in the following way

| Testing | Frontend | Mappers/DAO/DTO | Entities and Repositories | Web Controllers | Rest Controllers | Security | Exception Handling and Logging | Documentation |
|-----    | -----    |-------          | -------------------------  | -------------- | ---------------- | --------  | ---------------------------   | ------------   |
| Yash, Rob, JB, Emre, Hanibal | Cameron, Abdu;;ah, Patryk, Ben, Omari | David, Emre | Yash, David | Ben, Ignas | Liam, Omari | Craig, Hanibal | Rob | Liam          |


### Review

The main objective of day 1 was to make sure everyone had their MongoDB accounts set up and has access to database.
We also managed to organise the team and make a start on the intelliJ project by connecting it to the database and creating the entities and repositories.

### Retrospective

After realising that we could not use JPA Buddy to create our entities and repositories, we had to create them the long way.
This was a challenge and time consuming, however, Yash did brilliantly to get them working and left us in a good place going into the second sprint.

---

## Sprint 2
### Plan
After David spent all night creating DAOs and DTOs, we began Sprint 2

| Testing | Frontend | Mappers/DAO/DTO | Web Controllers | Rest Controllers | Security | Exception Handling and Logging | Documentation |
|-----    | -----    |-------          | -------------- | ---------------- | --------  | ---------------------------   | ------------   |
| Yash, Rob, JB, Emre, Hanibal | Cameron, Abdu;;ah, Patryk, Ben, Omari | David, Emre | Yash, David | Ben, Ignas | Liam, Omari | Craig, Hanibal | Rob | Liam          |



### Review

Good progress was made on both the Frontend and backend

### Retrospective
We had trouble resolving conflicts when merging on GitHub

## Sprint 3

---

### Plan

Following on with the tasks from the second sprint

| Testing | Frontend | Mappers/DAO/DTO | Web Controllers | Rest Controllers | Security | Exception Handling and Logging | Documentation |
|-----    | -----    |-------          | -------------- | ---------------- | --------  | ---------------------------   | ------------   |
| Yash, Rob, JB, Emre, Hanibal | Cameron, Abdu;;ah, Patryk, Ben, Omari | David, Emre | Yash, David | Ben, Ignas | Liam, Omari | Craig, Hanibal | Rob | Liam          |

### Review

### Retrospective

---
## Sprint 4
### Plan
### Review
### Retrospective

---

## Code


### Model
#### Entities and Repositories
#### DAOs, DTOs & Mappers
#### Testing

---

### View
#### HTML Templates
#### Website

---

### Controller
#### Rest controllers
#### Web Controllers
#### Testing

