# Smart Expense Tracker

A full-stack expense tracking application with AI-powered insights.

## Repositories

| Module | Tech | Link |
|--------|------|------|
| Backend | Spring Boot, Java 21, PostgreSQL | [expense-tracker-backend](https://github.com/voidirl/ai-expense-tracker-backend) |
| Frontend | React, Vite | [expense-tracker-frontend](https://github.com/voidirl/ai-expense-tracker-frontend) |
| AI Service | Python/FastAPI | [expense-tracker-ai](https://github.com/voidirl/expense-tracker-ai-service) |

## Architecture

Frontend (React) → Backend (Spring Boot) → Database (PostgreSQL)
                          ↕
                    AI Service (Python)

## Features
- Full CRUD for expenses
- Filter by category & date
- Total sum calculation
- AI-powered insights

## 🌐 Live Demo
[![Live Demo](https://img.shields.io/badge/Live%20Demo-voidledger.vercel.app-black?style=for-the-badge&logo=vercel)](https://voidledger.vercel.app)

## Running with Docker

This repo containerizes all three services (backend, frontend, ai-service) plus a PostgreSQL database using Docker Compose.

### Prerequisites
- Docker & Docker Compose installed
- Clone the frontend and ai-service repos as sibling directories to this one:
parent-folder/
├── expensetracker/          (this repo — backend + docker-compose.yml)
├── expenseTracker-frontend/
└── expense-ai-service/
### Environment Variables
Copy the example env files and fill in real values:
```bash
cp .env.example .env
cp ../expense-ai-service/.env.example ../expense-ai-service/.env
```

| Variable | Location | Description |
|---|---|---|
| `DB_PASSWORD` | `expensetracker/.env` | PostgreSQL superuser password, used by both `db` and `backend` services |
| `DB_USER` | `expensetracker/.env` | Optional. Defaults to `postgres` |
| `GROQ_API_KEY` | `expense-ai-service/.env` | API key for Groq LLM used by the AI insights service |

The frontend reads its own configuration from `expenseTracker-frontend/.env`
(see that repo's `.env.example`). Its defaults, `/api` and `/ai-api`, are
same-origin paths that nginx forwards to the `backend` and `ai-service`
containers, so no frontend configuration is needed for the Compose stack.

### Running the backend outside Docker
`src/main/resources/application.properties` reads the datasource from
placeholders with **no defaults on purpose**: a container that is missing its
configuration should fail immediately with an unresolved-placeholder error
rather than silently connecting to localhost and hanging. Compose supplies all
three, so `docker-compose up` needs nothing extra. To run the backend directly
you must set them yourself:

```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/expense_tracker
export SPRING_DATASOURCE_USERNAME=postgres
export SPRING_DATASOURCE_PASSWORD=<same value as DB_PASSWORD>
./mvnw spring-boot:run
```

`SHOW_SQL=true` turns on Hibernate's SQL logging, which is off by default so
production logs stay quiet.

### Build & Run
```bash
docker-compose up --build
```

### Ports
| Service | Port | URL |
|---|---|---|
| Frontend | 3000 | http://localhost:3000 |
| Backend | 8080 | http://localhost:8080 |
| AI Service | 8001 | http://localhost:8001 |
| PostgreSQL | 5432 | localhost:5432 (mapped from container's 5432) |

The AI service publishes host port 8001 because 8000 is often already taken by a
local dev server. The app never calls that port directly — nginx forwards
`/ai-api/*` to the container's internal 8000, so nothing in the browser changes.

To stop:
```bash
docker-compose down
```
