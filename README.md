# Testing Spring Boot Applications Demystified Workshop

[![Open in GitHub Codespaces](https://github.com/codespaces/badge.svg)](https://codespaces.new/PragmaTech-GmbH/workshop-datev-coding-festival-2025)

Welcome to the full-day workshop "Testing Spring Boot Applications Demystified" during the DATEV Coding Festival 2025 at the 09th of October 2025.

This workshop helps developers become more confident and productive when implementing automated tests for Spring Boot applications.

Proudly presented by [PragmaTech GmbH](https://pragmatech.digital/). 

## Workshop Overview

This workshop demystifies testing in Spring Boot applications through hands-on exercises, covering everything from basic unit testing to advanced integration testing techniques.

The workshop consists of four lab sessions, each focusing on different aspects of testing Spring Boot applications:

- [Lab 1](labs/lab-1): Introduction to the Workshop & Unit Testing
- [Lab 2](labs/lab-2): Sliced Testing: Working with a minimal Spring TestContext
- [Lab 3](labs/lab-3): Integration Testing: Testing against a full Spring TestContext
- [Lab 4](labs/lab-4): Pitfalls, Best Practices, Outlook and AI

Each lab concludes with hands-on tasks that are explained in the subsequent lab session.

Throughout the workshop, you'll learn to effectively use testing frameworks like JUnit 5, Mockito, Testcontainers, and WireMock in Spring Boot applications.

- You'll understand how to structure tests and when different test types provide the most value and confidence.
- We'll cover best practices for writing integration tests with the Spring TestContext, optimizing your test suite for fast & reliable results and explore the vast and mature Java testing ecosystem.
- Finally, we'll discuss common pitfalls and how to avoid them, plus how AI tools can assist in writing and maintaining tests.

The workshop will be held in either English or German, depending on the participants' preferences.

## Workshop Requirements

- Existing knowledge of Java and Spring Boot
- Familiarity with Maven and basic Java/Spring Boot testing concepts
- A laptop with Java 21 JDK installed
- Access to a Docker engine to use Testcontainers for integration tests (consider Podman)
- An IDE of your choice (preferably IntelliJ IDEA)
- To verify your setup, follow the instructions in [Getting Started](#getting-started)
- Alternative option to a local setup: Use [GitHub Codespaces](#github-codespaces), this requires a personal GitHub Account

## Workshop Timeline

- 9:00 - 10:30: Lab 1 (90 minutes)
- 10:30 - 11:00: **Coffee Break and time for exercises**
- 11:00 - 12:30: Lab 2 (90 minutes)
- 12:30 - 13:30 **Lunch**
- 13:30 - 15:00: Lab 3 (90 minutes)
- 15:00 - 15:30 **Coffee Break and time for exercises**
- 15:30 - 16:30: Lab 4 (60 minutes)
- 16:30 - 17:00: **Final Q&A and wrap-up**

## GitHub Codespaces

This repository is configured for use with GitHub Codespaces, which provides a complete, ready-to-use development environment in the cloud. To use GitHub Codespaces:

1. Follow this [link](https://codespaces.new/PragmaTech-GmbH/workshop-datev-coding-festival-2025)
2. Continue with the default confiugration (`main` branch, region `Europe West`) and select at least 4 cores (120 hours are [free per month](https://docs.github.com/en/billing/managing-billing-for-your-products/managing-billing-for-github-codespaces/about-billing-for-github-codespaces))
3. Wait for the codespace to start and setup to complete

The codespace includes:
- Java 21
- Maven
- Docker (for Testcontainers)
- VS Code with Spring Boot extensions

For more information, see the [Codespaces documentation](.devcontainer/README.md).

## Lab Structure

See the content of the [labs](labs) folder for the content per lab.

Each lab (`lab-1` through `lab-4`) includes:

- Exercise files with instructions and TODO comments
- Solution files that show the complete implementation
- Supporting code and configurations

## Getting Started

1. Clone this repository:

```bash
git clone https://github.com/PragmaTech-GmbH/workshop-datev-coding-festival-2025.git
```

2. Import the project at the root level into your IDE of choice.

3. Ensure you're using Java 21 for the project.

```
java -version
```

4. Run all builds from the root level of the project with:

```bash
./mvnw verify
```

## Additional Resources

- [Spring Boot Testing Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing)
- [Spring Test Documentation](https://docs.spring.io/spring-framework/reference/testing.html)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito Documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/Mockito.html)
- [Testcontainers Documentation](https://www.testcontainers.org/)
- [WireMock Documentation](http://wiremock.org/docs/)

## License

This project is licensed under the MIT License - see the LICENSE file for details.
