# HTTP_Server_JAVA

A simple HTTP server implementation in Java. This project is designed to help you understand the basics of building an HTTP server from scratch. While the code is in Java, the concepts can be applied to other backend languages.

## Features

- Handles basic HTTP requests (GET, POST, etc.)
- Parses and responds with JSON
- Easily extensible for custom endpoints

## Prerequisites

Before you begin, ensure you have the following installed:

1. **Java Development Kit (JDK)**  
   Required to compile and run Java applications.

2. **Maven**  
   A build automation tool used for managing dependencies and building the project.

3. **Dependencies**  
   All required dependencies are managed via Maven (see `pom.xml`). Notably, this project uses a JSON library for parsing and generating JSON.

## Getting Started

### 1. Clone the Repository

```sh
git clone https://github.com/yourusername/HTTP_Server_JAVA.git
cd HTTP_Server_JAVA
```

### 2. Build the Project

```sh
mvn clean install
```

### 3. Run the Server

```sh
mvn exec:java -Dexec.mainClass="your.main.ClassName"
```
Replace `your.main.ClassName` with the actual main class (e.g., `com.example.httpserver.Main`).

## Usage

Once running, the server listens for HTTP requests on the configured port (default: 8080).  
You can test it using tools like [curl](https://curl.se/) or Postman:

```sh
curl http://localhost:8080/
```

## Project Structure

- `src/main/java/` - Java source files
- `src/main/resources/` - Configuration and static files
- `pom.xml` - Maven configuration

## Customization

You can add new endpoints or modify existing ones by editing the handler classes in the source directory.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.

## License

This project is licensed under the MIT License.

## Contact

For questions or suggestions, open an issue or contact the maintainer.
