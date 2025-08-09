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
spring.datasource.username=postgres
spring.datasource.password=Manik1092
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

## Note: Upon application startup, the system automatically seeds the database with demo data, including predefined students and courses for testing purposes. The output below excludes these demo entries. The application supports creating multiple students and courses, as well as marking the attendance status of individual students for any specific course.

---

## 🙌 Acknowledgements

Thanks to **Prajwal Sanade** for valuable contributions to this project.

---

## OUTPUT :-

--- 

## CREATING THE STUDENT :- 
<img width="1920" height="1080" alt="Screenshot (319)" src="https://github.com/user-attachments/assets/53eea3bb-e488-41a3-8bd0-b6dfdc935de9" />

---

## CREATING THE COURSE
<img width="1920" height="1080" alt="Screenshot (320)" src="https://github.com/user-attachments/assets/66416621-c562-40f9-8b72-cbcb06fb5102" />

---

## ENROLLING THE STUDENT TO THE COURSE :-
<img width="1920" height="1080" alt="Screenshot (321)" src="https://github.com/user-attachments/assets/8c0277c7-98de-46a2-a7dc-3c1ac4d05ca9" />

---

## CREATING THE SESSION OF THE COURSE :-
<img width="1920" height="1080" alt="Screenshot (322)" src="https://github.com/user-attachments/assets/c4ba9a96-38f9-44fb-a301-b3b170bd1505" />

---

## UPDATING THE STATUS OF THE STUDENT :-
<img width="1920" height="1080" alt="Screenshot (323)" src="https://github.com/user-attachments/assets/a215a0cf-036f-49a0-9688-2544b724d4c6" />

---

## ATTENDANCE RECORDS :-
<img width="1920" height="1080" alt="Screenshot (324)" src="https://github.com/user-attachments/assets/50aed5f6-2724-4f67-9713-6660d3a52a92" />
