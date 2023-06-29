# 🚘 Auto-Service-App 🚘

This server application enables clients to send HTTP requests, such as GET, POST, PUT, and DELETE, and handles them accordingly. The application stores the received data in a PostgreSQL database, allowing efficient organization and storage of information.

With this application, various operations can be performed on the database, including creating new records, updating existing records, deleting records, and retrieving data from the database using HTTP requests.

For the convenience of development and API documentation, Swagger and SpringDoc are utilized.

### ▶️Video presentation Auto-Service-App. [LINK](https://www.youtube.com/watch?v=vRVwoTyOpxY)
  
### The following principles were followed during the development of the program:

* REST API
* SOLID principles

### Project based on 3-layer architecture:
* Presentation layer (controllers)
* Application layer (services)
* Data access layer (repositories)

## 📄 Functionalities
* `POST: /masters` - Creation of the "Master" entity
* `PUT: /masters/{id}` - Editing entity "Master" data
* `GET: /masters/{id}/orders` - Receiving master's orders
* `GET: /masters/{id}/salary` - Calculation and issuance of wages to the master
* `POST: /cars` - Creation of the "Car" entity
* `PUT: /cars/{id}` - Editing entity "Car" data
* `POST: /car-owners` - Creation of the entity "Owner of the Machine"
* `PUT: /car-owners/{id}` - Editing the data of the entity
* `GET: /car-owners/{id}/orders` - Get this customer's orders
* `POST: /orders` - Creation of the "Order" entity
* `POST: /orders/{orderId}}/goods/{goodsId}` - Adding "Goods" to the "Order" entity
* `PUT: /orders/{id}` - Editing entity data 
* `PUT: /orders/{id}/{newStatus}` - Editing the status of the "Order"
* `GET: /{Id}/calculatePrice` - Calculation of the cost of the "Order"
* `POST: /maintenances` - Creation of the "Maintenances" entity
* `PUT: /maintenances/{id}` - Editing entity "Maintenances" data
* `PUT: /maintenances/{id}/{newStatus}` - Editing the "Maintenances" status
* `POST: /goods` - Creation of the entity "Goods"
* `PUT: /goods/{id}` - Editing entity "Goods" data

### 📦Package Structure
* `controller` - contains controllers for endpoints
* `dto` - wrapper for model objects to unify the requests and responses in controllers
  * `mapper` - converts model objects to DTO objects
* `repository` - data access layer (repository) that calls CRUD methods in the database
* `model` - contains models for the database
* `service` - contains services that call repositories and the Authentication class


## 🛠 Technologies 🛠
* Java `17`
* Apache Maven `3.10.1`
* Apache Tomcat  `9.0.73`
* PostgreSQL `42.5.4`
* Spring: 
    * Boot `3.0.6`
    * Data Jpa `3.0.6`
    * Web Mvc `6.0.8`    
* Liquibase-core `4.17.2`
* lombok `1.18.26`
* Hibernate `6.1.7.Final`
* Swagger UI
* SpringDoc `2.1.0`
* Checkstyle Plugin `3.1.1`

## 💻How to Run and Test
⚠️Important: You must have Docker installed. If it is not installed, please download it from the website [link](https://www.docker.com/products/docker-desktop/) and proceed with the installation.

* Clone the repo on GitHub
* Run docker client
* In the terminal, run the command: `docker-compose up`
* Use the address `http://localhost:6868/swagger-ui/index.html#/` to access the documentation.

### Also, you can use my Postman request collection for testing: [LINK](https://www.postman.com/maintenance-geologist-24055309/workspace/autoservice/collection/26843599-364110e2-d4a3-4b06-b1ed-b73cfea2b09d?action=share&creator=26843599)

## Contacts
If you have any questions or suggestions, please feel free to contact me via [My LinkedIn](https://www.linkedin.com/in/sergiy-golubchenko-74646485/) 
I am open to new opportunities as a Junior Java Developer.
