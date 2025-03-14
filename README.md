# Payment Processing System

This project demonstrates a payment processing system using Java/Spring Boot, Apache Kafka, and Terraform. The system processes payment events and performs reconciliation via a Kafka listener.

## Features
- REST API for creating payment transactions.
- Apache Kafka integration for event-driven processing.
- Spring Boot for building the microservice.
- Terraform scripts to provision AWS resources (VPC, EC2, etc.).

## Getting Started

### Prerequisites
- Java 17+
- Maven
- Docker (for running Kafka and PostgreSQL locally)
- Terraform

### How to Run

1. **Start Dependencies:**
   - Start Kafka and PostgreSQL (for example, via Docker Compose).

2. **Build and Run the Application:**
   ```bash
   mvn clean install
   mvn spring-boot:run
