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

## OUTPUT :-

<img width="1920" height="1080" alt="Screenshot (311)" src="https://github.com/user-attachments/assets/2791dcea-ff7f-46e8-a2cb-503532d03d5b" />
---
<img width="1920" height="1080" alt="Screenshot (312)" src="https://github.com/user-attachments/assets/85abf712-e1b4-49d8-9051-c977ec182a69" />
---
<img width="1920" height="1080" alt="Screenshot (314)" src="https://github.com/user-attachments/assets/64cabcfa-a14f-45b7-a94d-b30922a68853" />
---
<img width="1920" height="1080" alt="Screenshot (315)" src="https://github.com/user-attachments/assets/fb7740a3-8631-4162-aa28-a075f9e20977" />
---
<img width="1920" height="1080" alt="Screenshot (316)" src="https://github.com/user-attachments/assets/3af7233c-d6f9-47e0-b47a-1db8e448d0df" />
---

