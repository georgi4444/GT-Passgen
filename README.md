# GT-Passgen

## About The Project

[![GT-Passgen Screen Shot][project-screenshot]](https://gt-passgen.herokuapp.com/)

GT-Passgen is a password generator web app, which uses Java Spring Boot and the Vaadin framework.

### Built With

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Vaadin](https://vaadin.com/)
* [Maven](https://maven.apache.org/)

## Getting Started

To get a local copy up and running follow these simple example steps.

### Prerequisites

* Java Development Kit (JDK) 8

### Installation

1. Import the project as Maven project in your IDE.
2. To run the application, run the `src/main/java/com/georgitsipov/passwordgenerator/PasswordGeneratorApplication.java` class. You can also use `mvn spring-boot:run` in the command line. If you want to run the application locally in the production mode, use `mvn spring-boot:run -Pproduction` command instead.
3. You can now open `localhost:8080` in your browser.

#### Building a Production-Optimized JAR

Build the application with the production profile:
```sh
mvn clean package -Pproduction
```
This builds a production-optimized JAR file in the target folder.

## Usage

You are making a registration in a random website and you need a secure password, then visit [GT-Passgen](https://gt-passgen.herokuapp.com). You can choose the desired length and what characters should be included. Hit `Generate Password`, the app will generate a secure random password and will also show its strength. You can also type your own password and see how strong it ist (I promise nothing is stored ;) )

## License

Distributed under the MIT License. See `LICENSE` for more information.

## Contact

Georgi Tsipov - georgi.tsipov@gmail.com

Project Link: [https://github.com/georgi4444/GT-Passgen](https://github.com/georgi4444/GT-Passgen)


## Acknowledgements

* [zxcvbn4j](https://github.com/nulab/zxcvbn4j)


<!-- MARKDOWN LINKS & IMAGES -->
[project-screenshot]: ./screenshot.png
