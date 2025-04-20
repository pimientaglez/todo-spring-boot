# 📝 Todo App – Spring Boot Backend

A simple and extensible Todo application built using **Spring Boot** and **PostgreSQL**. This app provides a clean RESTful API to manage your tasks with features like task creation, updating, deletion, filtering by status, and timestamps.

## 🚀 Features

- ✅ Create, read, update, and delete (CRUD) tasks
- 📋 Filter tasks by status (`pending`, `completed`)
- 🗃️ PostgreSQL as the persistent storage
- 📦 RESTful API following best practices

## 🛠️ Technologies

- Java 17+
- Spring Boot 3
- Spring Data JDBC
- PostgreSQL
- Maven (or Gradle)

## 🧪 API Endpoints

| Method | Endpoint                             | Description          |
| ------ | ------------------------------------ | -------------------- |
| GET    | `/api/tasks`                         | Get all tasks        |
| GET    | `/api/tasks/{id}`                    | Get task by ID       |
| POST   | `/api/tasks`                         | Create a new task    |
| PUT    | `/api/tasks/{id}`                    | Update existing task |
| DELETE | `/api/tasks/{id}`                    | Delete task by ID    |
| GET    | `/api/tasks/by-status?status=STATUS` | Get all by status    |

## ⚙️ Getting Started

1. **Clone the repository:**

```bash
git clone https://github.com/yourusername/todo-app-springboot.git
cd todo-app-springboot
```

### 1. Create `docker-compose.yml`

```yaml
services:
  postgres:
    image: "postgres:latest"
    environment:
      - "POSTGRES_DB=todo"
      - "POSTGRES_PASSWORD=pass"
      - "POSTGRES_USER=ricardo"
    ports:
      - "5432"
```
