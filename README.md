# Groot 🤖

Groot is a real-time AI chatbot built using Spring Boot.  
It supports live messaging via WebSockets and stores chat history in MySQL.

---

## 🚀 Features
- Real-time chat using WebSocket (STOMP)
- Backend built with Spring Boot
- Message persistence using MySQL + JPA
- Simple web-based chat UI
- AI integration with graceful fallback handling

---

## 🛠 Tech Stack
- Java 17
- Spring Boot
- Spring WebSocket (STOMP)
- Spring Data JPA
- MySQL
- HTML, CSS, JavaScript
- OpenAI API (optional, requires quota)

---

## 📂 Project Structure
src/main/java
├── controller
├── service
├── entity
├── repository
└── config

---

## ▶️ How to Run
1. Clone the repository
2. Configure MySQL database
3. Update `application.properties`
4. Run the Spring Boot application
5. Open `http://localhost:8080/chat.html`

---

## 📌 Note
AI features require an active API key and available quota.  
The application handles API failures gracefully.

---

## 👨‍💻 Author
Aman Kumar  

---

## 📄 License
This project is licensed under the MIT License.
