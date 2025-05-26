# Response Code Manager

A web-based application built using **Java Servlets**, **JSP**, **HTML/CSS**, and **MySQL** that allows users to manage, search, and organize HTTP response codes into custom lists.

## 🚀 Features

- ✅ User registration and login
- 🗂️ Create, edit, and delete custom response code lists
- 🔍 Search response codes using patterns (e.g., `2xx`, `40x`,`1x3`,`xx4`)
- ➕ Add/remove response codes from lists
- 📸 Displays images for HTTP status codes
- 🔒 Session-based authentication

## 🛠️ Tech Stack

| Layer        | Technology     |
|--------------|----------------|
| Backend      | Java Servlets, JSP |
| Frontend     | HTML5, CSS3    |
| Database     | MySQL          |
| Build Tool   | Apache Tomcat  |

## 📂 Project Structure
<pre> ├── src/ 
  │ └── com/maneesh/ 
  │ ├── model/ # Java model classes (User, ResponseCode, ResponseList) 
  │ ├── dao/ # DAO interfaces 
  │ ├── daoimpl/ # DAO implementations using JDBC 
  │ └── servlet/ # Java Servlets (RegisterServlet, LoginServlet, etc.) 
  │
  │ ├── WebContent/  
  │ ├── images/ # Icons or HTTP status images 
  │ ├── *.jsp # JSP pages (register.jsp, login.jsp, list.jsp, edit-list.jsp)  
  │
  │ └── README.md # Project documentation </pre>


🙋‍♂️ Author
Maneesh Reddy Bhumireddy
Connect on LinkedIn(www.linkedin.com/in/maneesh-reddy-bhumireddy-6566a3276) | Email: maneeshreddy200@gmail.com
