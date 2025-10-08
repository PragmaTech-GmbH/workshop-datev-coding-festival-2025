# Lab 4: Advanced Spring Boot Testing Techniques

## Learning Objectives

- Compare JUnit 4 vs JUnit 5 testing styles
- Understand the differences between `@Mock`, `@MockBean`, and `@MockitoBean`
- Learn best practices for Spring Boot integration testing
- Master techniques for testing asynchronous code
- Optimize Spring test execution with strategic context configuration


## Test Parallelization

- Active JVMs: `watch -n 1 'jps -l | sort -n'` or only Surefire `watch -n 1 'jps -l | grep surefire'`
- Active threads per JVM: `watch -n 1 'PID=$(jps -l | grep surefire | awk "{print \$1}"); if [ -n "$PID" ]; then jstack $PID | awk "/^\".*\"/ {name=\$0} /java.lang.Thread.State/ {print name, \$2}" | column -t; else echo "No surefire JVM found"; fi'`
- JUnit docs on test parallelization: https://docs.junit.org/snapshot/user-guide/#writing-tests-parallel-execution

## PIT & JaCoCo

- Get the PIT report: `./mvnw test-compile  pitest:mutationCoverage`
- Get the JaCoCo report: `./mvnw package`

## Claude Code

Prompt: `in the project code for lab-4, help me develop an HTTP PUT request to update an existing book, outsource the actual work to the
BookService`

## Hints

- JUnit 5 provides a more flexible and powerful extension model than JUnit 4
- `@Mock` is for plain Mockito without Spring context, while `@MockBean/@MockitoBean` are for Spring tests
- `@MockitoBean` is preferred over `@MockBean` for better control and slightly better performance
- Async testing may require special utilities like Awaitility
- Test context caching is critical for fast Spring Boot tests
- Try to reuse test contexts whenever possible
- Consider using test slices to reduce context startup time
- Run mutation tests with `./mvnw test-compile org.pitest:pitest-maven:mutationCoverage`

## Exercises

No exercises for this last lab.
