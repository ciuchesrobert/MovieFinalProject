# **Final Movie Project**

---
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
----

### MongoDB Setup

1. If you wanted to use the same database from the project, you will need to [create an account with MongoDB](https://www.mongodb.com/cloud/atlas/register)
2. Follow [these steps](https://www.mongodb.com/docs/drivers/node/current/quick-start/#create-a-mongodb-cluster) to create a new cluster
3. You will then need to download the [sample data](https://www.mongodb.com/docs/atlas/sample-data/#std-label-load-sample-data) to your MongoDB Atlas - this includes the sample_mflix database
4. Copy the connection string and use it to connect to database from intelliJ 
5. Use the following for your application.properties file but change USER, PASSWORD HOST and DATABASE to their respective values:

```java
spring.data.mongodb.uri=mongodb+srv://USER:PASSWORD@HOST/DATABASE
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
```

---

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
| Yash, Rob, JB, Emre, Hanibal | Cameron, Abdullah, Patryk, Ben, Omari | David, Emre | Yash, David | Ben, Ignas | Liam, Omari | Craig, Hanibal | Rob | Liam          |


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
| Yash, Rob, JB, Emre, Hanibal | Cameron, Abdullah, Patryk, Ben, Omari | David, Emre | Yash, David | Ben, Ignas | Liam, Omari | Craig, Hanibal | Rob | Liam          |



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
| Yash, Rob, JB, Emre, Hanibal | Cameron, Abdullah, Patryk, Ben, Omari | David, Emre | Yash, David | Ben, Ignas | Liam, Omari | Craig, Hanibal | Rob | Liam          |

### Review
Made good progress. Testing needs to be completed and a scheduling method. Adjustments
may have to be made because of time

### Retrospective
Worked hard as team to complete challenging tasks

---
## Sprint 4
| Testing | Frontend | Scheduling DTO/DAO | Web Controllers| Presentation   | Security |  Documentation |
|-----    | -----    |-------          | -------------- | ---------------- | --------  | ---------------------------   | 
| Yash, Rob | Cameron, Abdullah, Omari | David| David, Cameron | Ben, Ignas, Emre, Patryk, JB | Craig, Hanibal | Liam         |

### Plan
With the project coming to an end we had to turn our focus to the Presentation and prepare for that. 
Our aims were:
- to create functionality for the Scheduling method
- Make the website user friendly and professional
- Test our Rest and Web controllers
### Review
The backend of the Scheduling was created and big improvements were made to the frontend usability.
A lot of the web controllers still need testing however the web application runs well.
The Presentation is ready to go.
### Retrospective
Another hard day of work. Although more time would be good, we are very happy with how the project has turned out.

---

## Code


### Model
#### Entities and Repositories
```java
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)

    private ObjectId id;

    private String email;

    private String name;

    private String password;

    public boolean entityEqualsDto(UserDTO obj) {
        if (this.getId() == obj.getId() &&
                this.getEmail() == obj.getEmail() &&
                this.getName() == obj.getName() &&
                this.getPassword() == obj.getPassword()){
            return true;
        }
        else {
            return false;
        }
    }
}
```
```java
@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {

    Optional<User> findByEmail(String email);

    void deleteByEmail(String email);
}
```
#### DTOs, Converters & DAOs
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private ObjectId id;
    private String email;
    private String name;
    private String password;

    public UserDTO(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public boolean dtoEqualsEntity(User obj) {
        if (this.getId() == obj.getId() &&
                this.getEmail() == obj.getEmail() &&
                this.getName() == obj.getName() &&
                this.getPassword() == obj.getPassword()){
            return true;
        }
        else {
            return false;
        }
        
    }
}
```
```java
public class UserConverter implements Converter<UserDTO, User> {

    @Override
    public User dtoToEntity(UserDTO userDto) {
        return new User(userDto.getId(),
                userDto.getEmail(),
                userDto.getName(),
                userDto.getPassword());
    }

    @Override
    public UserDTO entityToDto(User user) {
        return new UserDTO(user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPassword());
    }
}
```
```java
@Service
public class UserDAO implements com.sparta.moviefinalproject.daos.interfaces.UserDAO {

    private final UserRepository userRepo;

    public UserDAO(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDTO create(UserDTO user) {
        return new UserConverter().entityToDto(userRepo.insert(new UserConverter().dtoToEntity(user)));
    }

    @Override
    public Optional<UserDTO> findById(ObjectId id) {
        if(userRepo.findById(id).isPresent()) {
            User user = userRepo.findById(id).get();
            return Optional.of(new UserConverter().entityToDto(user));
        }
        return Optional.empty();
    }

    public Optional<UserDTO> findByEmail(String email) {
        if(userRepo.findByEmail(email).isPresent()) {
            User user = userRepo.findByEmail(email).get();
            return Optional.of(new UserConverter().entityToDto(user));
        }
        return Optional.empty();
    }

    @Override
    public UserDTO update(ObjectId id, UserDTO updatedUser) {
            User user = new UserConverter().dtoToEntity(updatedUser);
            user.setId(id);
            userRepo.save(user);
            return new UserConverter().entityToDto(user);
    }

    @Override
    public void deleteById(ObjectId id) {
        if(userRepo.findById(id).isPresent()) {
            userRepo.deleteById(id);
        }
    }

    public void deleteByEmail(String email) {
        if(userRepo.findByEmail(email).isPresent()) {
            userRepo.deleteByEmail(email);
        }
    }

    @Override
    public List<UserDTO> findAll()
    {
        List<User> users = userRepo.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for(User user : users)
        {
            userDTOs.add(new UserConverter().entityToDto(user));
        }
        return userDTOs;
    }

    public Page<User> findAllUsers(int pageNum){
        return userPage(PageRequest.of(pageNum, 10));
    }

    public Page<User> userPage(Pageable pageable){
        return userRepo.findAll(pageable);
    }
}
```
#### Testing
```java
@SpringBootTest
public class UserTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    UserDAO userDao;

    @Test
    void createTest() {
        UserDTO user = new UserDTO(new ObjectId("507f1f77bcf86cd799439011"),
                "testEmail",
                "testName",
                "testPass");
        userDao.create(user);
        System.out.println(userDao.findById(new ObjectId("507f1f77bcf86cd799439011")));
    }

    @Test
    void readTest() {
        System.out.println(userDao.findById(new ObjectId("507f1f77bcf86cd799439011")));
    }

    @Test
    void updateTest() {
        UserDTO user = new UserDTO("testEmailUpdated",
                "testNameUpdated",
                "testPassUpdated");
        userDao.update(new ObjectId("507f1f77bcf86cd799439011"), user);
        System.out.println(userDao.findById(new ObjectId("507f1f77bcf86cd799439011")));
    }

    @Test
    void deleteTest() {
        userDao.deleteById(new ObjectId("507f1f77bcf86cd799439011"));
        System.out.println(userDao.findById(new ObjectId("507f1f77bcf86cd799439011")));
    }

    @Test
    void miscTesting() {
        String dateTimeStr = "2002-08-18T04:56:07.000Z";
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, ISO_DATE_TIME);
        System.out.println(dateTime);
    }

}
```

---

### View
#### HTML Templates
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Scope Cinemas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js" type="text/javascript"></script>

    <style>
        @import url(https://fonts.googleapis.com/css?family=Josefin+Sans:300);

        *, *:before, *:after {
            box-sizing: border-box;
            -moz-box-sizing: border-box;
            -webkit-box-sizing: border-box;
        }
        h1, h2, h3 {
            margin: 0;
        }
        body {
            margin: 0;
            padding: 0;
            font-family: 'Josefin Sans';
            overflow: auto;
        }
        h1, a {
            text-transform: uppercase;
            color: black;
            text-decoration: none;
        }

        .title {
            color: #fff;
        }

        /* Panels Width */
        [class*='col-'] {
            float: left;
        }
        .col-1-5 {
            width: calc(100% * 1 / 5);
        }

        /* Left Side Bar */
        .sidebar {
            height: 100%;
            min-height: 100%;
            position: fixed;
            z-index: 10;
            left: 0;
            background-color: #333;
        }

        .panels {
            height: 100%;
            position: fixed;
            z-index: 1;
            -webkit-transition: all 0.3s ease;
            -moz-transition: all 0.3s ease;
            -o-transition: all 0.3s ease;
            transition: all 0.3s ease;
        }
        .panels:hover {
            opacity: 0.8;
        }
        .title {
            text-align: center;
            padding: 15% 5%;
            font-weight: 300;
        }
        .panel1 {
            left: calc(100% * 1 / 5);
            background-color:#3498db;
        }
        .panel2 {
            left: calc(100% * 2 / 5);
            background-color: #e67e22;
        }
        .panel3 {
            left: calc(100% * 3 / 5);
            background-color: #9b59b6;
        }
        .panel4 {
            left: calc(100% * 4 / 5);
            background-color: #e74c3c;
        }
        .panel-active {
            left: calc(100% * 1 / 5);
        }
        .panel-push-left {
            left: 0;
        }
        .panel-push-right {
            left: 100%;
        }
    </style>
</head>
<body>
<div th:insert="/navbar/navbar.html :: navbar">  </div>
<div class="container">
    <!-- Left Side Bar -->
    <section class="sidebar col-1-5">
        <h1 class="title">Home</h1>
    </section>

    <!-- Panels -->
    <section>
        <!-- Panel One -->
        <a th:href="@{/movies/home}" class="panels panel1 col-1-5">
            <h1 class="title">Movies</h1>
        </a>

        <!-- Panel Two -->
        <a th:href="@{/comments/home}" class="panels panel2 col-1-5">
            <h1 class="title">Comments</h1>
        </a>

        <!-- Panel Three -->
        <a th:href="@{/theaters/home}" class="panels panel3 col-1-5">
            <h1 class="title">Theaters</h1>
        </a>

        <!-- Panel Four -->
        <a href="#" class="panels panel4 col-1-5">
            <h1 class="title">Schedules & Showtimes</h1>
        </a>
    </section>
</div>
</body>
</html>
```





---

### Controller
#### Rest controllers
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    final UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/{id}")
    public Optional<UserDTO> findById(@PathVariable("id") String id) {
        return userDAO.findById(new ObjectId(id));
    }

    @GetMapping
    public List<UserDTO> findAll() {
        return userDAO.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody UserDTO user){
        this.userDAO.create(user);
        return user;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        this.userDAO.deleteById(new ObjectId(id));
    }

    @PutMapping("/{id}")
    public UserDTO update(@RequestBody UserDTO user, @PathVariable("id") String id) {
        userDAO.update(new ObjectId(id), user);
        return user;

    }
}
```
#### Web Controllers
```java
@Controller
@RequestMapping("/users")
public class UserWebController {

    private final UserDAO userDAO;
    public UserWebController(UserDAO userDao) {
        this.userDAO = userDao;
    }
    
    @RequestMapping("/home")
    public String usersHome(Model model){
        return "usersHome/usersHome";
    }

    @GetMapping("/adminHome")
    public String adminHome(Model model){
        return "adminExtras/adminExtras";
    }

//    This is a method for finding user by id:
    @GetMapping("/basic/searchById/{id}")
    public String getUserById(@PathVariable ObjectId id, Model model){
        Optional<UserDTO> userOptional = userDAO.findById(id);
        UserDTO user = null;
        if(userOptional.isPresent()){
            user = userOptional.get();
        }
        model.addAttribute("user",user);
        model.addAttribute("id",id);
        return "user/displayUser";
    }

    //    This is a method for finding user by email:
    @GetMapping("/basic/searchByEmail/{email}")
    public String getUserByEmail(@PathVariable String email, Model model){
        Optional<UserDTO> userOptional = userDAO.findByEmail(email);
        UserDTO user = null;
        if(userOptional.isPresent()){
            user = userOptional.get();
        }
        model.addAttribute("user",user);
        model.addAttribute("email",email);
        return "user/displayUser";
    }

    @GetMapping("/basic/{pageNum}")
    public String getAllUsers(Model model, @PathVariable int pageNum){
        Page<User> users = userDAO.findAllUsers(pageNum);
        model.addAttribute("users", users);
        return "user/displayAllUsers";
    }

    @GetMapping("/admin/create")
    public String createUser(Model model){
        UserDTO user = new UserDTO();
        user.setId(new ObjectId());
        model.addAttribute("user", user);
        return "user/createUser";
    }

    @PostMapping("/admin/createSuccess")
    public String creatUserSuccess(@ModelAttribute("user") UserDTO user, Model model){
        model.addAttribute("user", user);
        userDAO.create(user);


        return "user/createUserSuccess";
    }


    @GetMapping("/admin/update")
    public String updateUser(Model model, UserDTO user) {
        model.addAttribute("user", user);
        return "user/updateUser";
    }
    @PostMapping("/admin/updateSuccess")
    public String updateSuccess(@ModelAttribute("user") UserDTO user, Model model) {
        Optional<UserDTO> userDTOOptional = userDAO.findById(user.getId());
        if (userDTOOptional.isPresent()) {
            userDAO.update(user.getId(),user);
        }
        return "user/updateUserSuccess";
    }

    @GetMapping("/admin/delete")
    public String deleteUser(Model model, UserDTO user) {
        model.addAttribute("user", user);
        return "user/deleteUser";
    }
    @PostMapping("/admin/deleteSuccess")
    public String deleteSuccess(@ModelAttribute("user") UserDTO user, Model model) {
        Optional<UserDTO> userDTOOptional = userDAO.findByEmail(user.getEmail());
        if (userDTOOptional.isPresent()) {
            userDAO.deleteByEmail(user.getEmail());
        }
        return "user/deleteUserSuccess";
    }
}
```
#### Testing
```java
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = MovieFinalProjectApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class UserControllerTesting {
    @Autowired
    private MockMvc mvc;

    public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Testing @GetMapping for findById method on users with ID of 59b99db4cfa9a34dcd7885b6")
    public void FindUserById_IfExistsReturnSuccess() throws Exception {
        String id = "59b99db4cfa9a34dcd7885b6";
        mvc.perform(get("/api/users/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id.timestamp").exists())
                .andExpect(jsonPath("$.email").value("sean_bean@gameofthron.es"))
                .andExpect(jsonPath("$.password")
                        .value("$2b$12$UREFwsRUoyF0CRqGNK0LzO0HM/jLhgUCNNIJ9RJAqMUQ74crlJ1Vu"));
    }

    @Test
    @DisplayName("Test @GetMapping for findAll method for users")
    public void FindAllUsers() throws Exception {
        mvc.perform(get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].email").value("sean_bean@gameofthron.es"))
                .andExpect(jsonPath("$[1].email").value("mark_addy@gameofthron.es"))
                .andExpect(jsonPath("$[2].email").value("nikolaj_coster-waldau@gameofthron.es"));
    }

    @Test
    @DisplayName("Test @PostMapping for create method for users")
    public void CreateUser_CheckIfExists() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/users")
                        .content(asJsonString(new User(
                                new ObjectId("63c7cf248b1a8a9b4bf831a0"), "yash2@gmail.com", "yash2", "yash2"
                        )))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("yash2@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("yash2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("yash2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    @DisplayName("Test @PutMapping for update method for users")
    public void UpdateUser_CheckIfUpdatesPersist() throws Exception {
        String id = "59b99db6cfa9a34dcd7885bc";
        mvc.perform( MockMvcRequestBuilders
                        .put("/api/users/" + id)
                        .content(asJsonString(new User(
                                new ObjectId("59b99db6cfa9a34dcd7885bc"), "iain_glen@gameofthron.es",
                                "Jorah Mormont",
                                "$2b$12$K8bKkwnpkrjsBPzASZxO/.yj7d9kvupiVtO6JA3Xl106AKXr3pXFK"
                        )))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id.timestamp").value(1505336758))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id.date").value("2017-09-13T21:05:58.000+00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("iain_glen@gameofthron.es"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Jorah Mormont"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("$2b$12$K8bKkwnpkrjsBPzASZxO/.yj7d9kvupiVtO6JA3Xl106AKXr3pXFK"));
    }

}
```

