# Student Attendance Management

## 👥 Team Members  
**Manik Barad (Leader)**  
**Prajwal Sanade (Contributor)**

---

## 📌 Overview  
The Student Attendance Application is a full-stack web application designed to efficiently manage and track student attendance records for courses and sessions. It provides REST APIs for attendance sessions, student attendance marking, and reporting, with persistent storage in a PostgreSQL database.

---

## 🛠 Technologies Used

Backend  
- Java (Spring Boot) – REST API & business logic  
- Spring Data JPA + Hibernate – Database ORM  
- PostgreSQL – Relational database for persistent storage  
- Flyway – Database migrations  
- Maven – Build and dependency management  
- Jakarta Validation – Request validation  

---

## ⚙ Features

- Create and manage attendance sessions  
- Mark student attendance (Present, Absent, Late, Excused)  
- Prevent duplicate attendance entries per session & student  
- Fetch session roster with attendance status  
- Summary reports per session  
- Data validation for all inputs  
- PostgreSQL ENUM support for attendance status  

---

## 🚀 How to Run

Backend

1. Navigate to the backend folder:  
2. Configure `application.properties` with your database details:  
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
3. Run Flyway migrations (optional if using Flyway):  
mvn flyway:migrate
4. Build and run the Spring Boot application:  
mvn spring-boot:run

---

## 🖼 Example API Endpoints

| Method | Endpoint                              | Description                      |
|--------|-------------------------------------|---------------------------------|
| POST   | /api/attendance/sessions            | Create or get an attendance session |
| GET    | /api/attendance/sessions/{id}       | Get session details             |
| GET    | /api/attendance/sessions/course/{courseId} | List sessions for a course     |
| GET    | /api/attendance/sessions/{id}/roster| Get attendance roster          |
| POST   | /api/attendance/sessions/{id}/attendance | Mark or update attendance      |
| GET    | /api/attendance/sessions/{id}/summary | Get attendance summary         |

---

## 📈 Future Enhancements

- Add user authentication & role-based access  
- Frontend UI for easier attendance marking and reports  
- Export attendance reports to CSV or PDF  
- Notifications & alerts for absent students  
- Integration with course management systems  

---

## 🙌 Acknowledgements

Thanks to **Prajwal Sanade** for valuable contributions to this project.

---
