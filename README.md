# Literalura

Literalura is a console-based Java application that allows users to search for books using the Gutendex API and store the results in a PostgreSQL database. The application provides a simple interface to explore books, authors, and statistics about stored data.

This project was developed as part of the Oracle Next Education (ONE) program challenge.

---

## Features

The application provides the following functionalities:

* Search books by title using the Gutendex API
* Store books and authors in a database
* List all searched books
* List all stored authors
* Find authors who were alive in a specific year
* Display statistics about books by language

---

## Technologies Used

* Java 25
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Maven
* Jackson (JSON parsing)

External API used:

* Gutendex API (Project Gutenberg books catalog)

---

## Project Structure

```
literalura
│
├── client
│   └── ApiClient.java
│
├── dto
│   ├── AuthorData.java
│   ├── BookData.java
│   └── GutendexResponse.java
│
├── model
│   ├── Author.java
│   └── Book.java
│
├── repository
│   ├── AuthorRepository.java
│   └── BookRepository.java
│
├── service
│   ├── JsonParserService.java
│   └── BookService.java
│
├── principal
│   └── MenuPrincipal.java
│
└── LiteraluraApplication.java
```

---

## How It Works

1. The user searches for a book title from the console.
2. The application queries the Gutendex API.
3. The first result is extracted.
4. Book and author data are converted into Java objects.
5. The information is stored in the PostgreSQL database.
6. Users can later list books, authors, or generate statistics.

---

## Example Menu

```
===== LITERALURA =====

1 - Search book by title
2 - List books
3 - List authors
4 - Authors alive in a given year
5 - Count books by language
0 - Exit
```

---

## Database

The application uses PostgreSQL to persist data.

Two main entities are stored:

**Book**

* Title
* Language
* Download count
* Author reference

**Author**

* Name
* Birth year
* Death year

Relationship:

```
Author (1) ---- (N) Book
```

---

## Running the Application

1. Clone the repository:

```
git clone https://github.com/your-username/literalura-java.git
```

2. Configure your PostgreSQL database in `application.properties`.

Example configuration:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=postgres
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

3. Run the application:

```
mvn spring-boot:run
```

---

## API Used

This project consumes data from the Gutendex API:

https://gutendex.com/

Gutendex provides access to the Project Gutenberg catalog of public domain books.

---

## Learning Goals

This project demonstrates the following concepts:

* REST API consumption in Java
* JSON parsing with Jackson
* Data persistence with Spring Data JPA
* PostgreSQL database integration
* Derived queries in JPA
* Console-based user interaction

---

## Author

Developed by Luis Fernando Serrano Madrigal
