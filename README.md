# DietApp Backend

Spring Boot backend for a dietology application (patients, assessments, diagnoses, goals, monitoring, AI recommendations).

---

## How to run the project

Clone the repository using:
git clone https://github.com/YOUR-USERNAME/YOUR-REPO.git  
cd YOUR-REPO

Make sure you have Java 17+, Maven, and PostgreSQL installed. Create a PostgreSQL database called `dietapp` (or any name you prefer) and update the database connection inside `src/main/resources/application.properties` with your credentials like this:

spring.datasource.url=jdbc:postgresql://localhost:5432/dietapp  
spring.datasource.username=postgres  
spring.datasource.password=your_password  
spring.jpa.hibernate.ddl-auto=update  
spring.jpa.show-sql=true

To run the application, set your environment variable for the Groq API key in PowerShell:

setx GROQ_API_KEY "your_key_here"

Then restart your terminal and ensure the application reads it in `application.properties` like this:

groq.api.key=${GROQ_API_KEY}

Finally, start the backend with Maven:

mvn spring-boot:run

or if you use the wrapper:

./mvnw spring-boot:run

Once running, the backend will be available on http://localhost:8081.

---

## Project overview

This backend handles:
- Patient management with anonymous codes
- Medical and nutritional assessments
- Diagnoses (PES structure)
- AI-based recommendations
- Monitoring of weight, nutrition, and progress


---

## Database tables used

patient_stammdaten  
patientenfall  
assessment_allgemein  
assessment_koerper  
assessment_ernaehrung  
assessment_umwelt  
diagnose  
ziele  
monitoring  
ki_entscheidung

---

## Important notes

Do not commit API keys or passwords into the repository. Always use environment variables for sensitive data.