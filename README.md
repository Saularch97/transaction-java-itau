
# **Spring Boot Project with Kotlin**

This is an example project developed with **Spring Boot** and **Kotlin**. Below, you will find the instructions to configure and run the project on your local machine.

## **Requirements**

Before running the project, you need to ensure that the following tools are installed on your machine:

- **Java 21** or higher (JDK)
- **Gradle** (optional, you can use the Gradle wrapper)
- **Docker** (optional, if you want to run the project in a container)

## **Step-by-Step Guide**

### 1. **Clone the Repository**

First, clone the project repository to your local machine:

```bash
git clone <REPOSITORY_URL>
cd <REPOSITORY_NAME>
```

### 2. **Install Java 21 or higher**

The project is configured to run with **Java 21**. If you don't have Java 21 installed, follow these instructions:

#### **Install Java 21 (Ubuntu/Zorin OS)**

```bash
sudo apt update
sudo apt install openjdk-21-jdk
```

To verify if Java was installed correctly, run:

```bash
java -version
```

### 3. **Install Gradle (optional)**

Although you can use the **Gradle Wrapper** (recommended), if you prefer to install Gradle globally, follow these steps:

```bash
sudo apt update
sudo apt install gradle
```

To check Gradle version:

```bash
gradle -v
```

Alternatively, you can use the **Gradle Wrapper**, which is already configured in the project, with the following command:

```bash
./gradlew build
```

### 4. **Install Dependencies and Build the Project**

If you're using the **Gradle Wrapper**, just run the following command to download the dependencies and build the project:

```bash
./gradlew build
```

If you have **Gradle** installed globally, run:

```bash
gradle build
```

This command will generate the `.jar` file inside the `build/libs/` folder.

### 5. **Run the Project Locally**

After generating the `.jar` file, you can run the application locally with the following command:

```bash
java -jar build/libs/<JAR_FILE_NAME>.jar
```

This will start the server locally. By default, Spring Boot uses port **8080**.

### 6. **Access the API**

The API will be available at:

```
http://localhost:8080
```

If Swagger is configured, you can access the API documentation at:

```
http://localhost:8080/swagger-ui.html
```

---

## **Technologies Used**

- **Kotlin**: Programming language
- **Spring Boot**: Framework to build APIs and backend applications
- **Spring Actuator**: For application metrics and monitoring
- **Springdoc OpenAPI**: For automatic API documentation (Swagger)
- **Gradle**: Build automation tool

---

## **Conclusion**

Now you're ready to run and test your Spring Boot + Kotlin application. If you have any questions or encounter any issues during setup, feel free to ask!