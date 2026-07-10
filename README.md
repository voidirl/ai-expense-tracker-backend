# Smart Expense Tracker

A full-stack expense tracking application with AI-powered insights.

## Repositories

| Module | Tech | Link |
|--------|------|------|
| Backend | Spring Boot, Java 21, MariaDB | [expense-tracker-backend](https://github.com/voidirl/ai-expense-tracker-backend) |
| Frontend | React, Vite | [expense-tracker-frontend](https://github.com/voidirl/ai-expense-tracker-frontend) |
| AI Service | Python/FastAPI | [expense-tracker-ai](https://github.com/voidirl/expense-tracker-ai-service) |

## Architecture

Frontend (React) → Backend (Spring Boot) → Database (MariaDB)
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

This repo containerizes all three services (backend, frontend, ai-service) plus a MariaDB database using Docker Compose.

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
| `DB_PASSWORD` | `expensetracker/.env` | MariaDB root password, used by both `db` and `backend` services |
| `GROQ_API_KEY` | `expense-ai-service/.env` | API key for Groq LLM used by the AI insights service |

### Build & Run
```bash
docker-compose up --build
```

### Ports
| Service | Port | URL |
|---|---|---|
| Frontend | 3000 | http://localhost:3000 |
| Backend | 8080 | http://localhost:8080 |
| AI Service | 8000 | http://localhost:8000 |
| MariaDB | 3307 | localhost:3307 (mapped from container's 3306) |

To stop:
```bash
docker-compose down
```
