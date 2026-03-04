# Automatic Birthday Wisher

- A Spring Boot application that automatically sends birthday wishes via email using MongoDB Atlas and GitHub Actions.
- This project is fully automated and runs in the cloud without requiring a local machine.


## Features

- Automatically checks today’s birthdays
- Sends email wishes using Gmail SMTP
- Uses MongoDB Atlas (Cloud Database)
- Secure credentials using GitHub Secrets
- Fully automated with GitHub Actions (CI/CD)
- Runs daily based on cron schedule


## Tech Stack

- Java 17
- Spring Boot
- MongoDB Atlas
- Maven
- GitHub Actions
- Gmail SMTP


## Project Structure

birthday-wisher
│
├── src/main/java
│ ├── controller
│ ├── service
│ ├── repository
│ └── model
│
├── src/main/resources
│ └── application.properties
│
└── .github/workflows
└── birthday-automation.yml


## How It Works

1. GitHub Actions triggers daily using cron.
2. Spring Boot application starts.
3. Connects to MongoDB Atlas.
4. Fetches birthdays matching today's date.
5. Sends email wishes.
6. Application exits.


# Author
- Adarsha G C
