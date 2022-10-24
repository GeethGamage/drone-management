
## Drones Management Service


---

### Introduction

There is a major new technology that is destined to be a disruptive force in the field of transportation: **the drone**.  Just as the mobile phone allowed developing countries to leapfrog older technologies for personal communication, the drone has the potential to leapfrog traditional transportation infrastructure.

Useful drone functions include delivery of small items that are (urgently) needed in locations with difficult access.

---

#### Requirements

- Java 1.8
- IDE (intellij)
- MYSQL databse  (local or in-memory databse)

---

### How to Build / Run / Test the project locally

- Clone the the project using this url: https://github.com/GeethGamage/drone-management.git

- Open the project in IDE

- Create mysql database and name the scema as drone_db

- Update the Mysql connection details in 'application.properties' file

- All required tables are created automatically using 'liquibase'

- Maven Build the project and run

- Before running you can run the JUnit test cases 

---

### Sample Postman API Request and Response 

- **Registering a drone** : http://localhost:8080/api/v1/drone

![reg-drone-payload.png](https://i.postimg.cc/6QQ90bCG/p1.png)

---
- **Checking available drones for loading** : http://localhost:8080/api/v1/drone/state/IDLE 

![available.png](https://i.postimg.cc/rwj4sb8d/p2.png)

---
- **Check drone battery level for a given drone**: http://localhost:8080/api/v1/drone/battery/0000111 


![load-drone.png](https://i.postimg.cc/vHjjjqRV/p3.png)

---

- **Loading a drone with medication items**: http://localhost:8080/api/v1/drone/medications

![details.png](https://i.postimg.cc/kMRhSNRM/p4.png)

---

- **Checking loaded medication items for a given drone**: http://localhost:8080/api/v1/drone/medications/0000111

![battery.png](https://i.postimg.cc/253xDm4j/p5.png)

---


