package pragmatech.digital.workshops.lab3.exercises;

import org.junit.jupiter.api.Test;

/**
 * Exercise 1: Testing the OpenLibraryApiClient with WireMock
 * <p>
 * In this exercise, you will test the OpenLibraryApiClient using WireMock without Spring context.
 * <p>
 * Tasks:
 * 1. Create tests to verify successful API calls (status code 200)
 * 2. Create tests to verify error handling (status code 500)
 * <p>
 * Hints:
 * - You can use the WireMock JUnit 5 extension (@RegisterExtension)
 * or bootstrap the WireMock server manually
 * - Use WebClient.builder() to create a WebClient instance that points to your WireMock server
 * - Check the __files directory in test resources for sample response JSON files
 */
public class Exercise1WireMockTest {

  @Test
  void shouldReturnBookMetadataWhenApiReturnsValidResponse() {
    // TODO:
    // 1. Set up WireMock server (using extension or manually)
    // 2. Configure WebClient to point to WireMock
    // 3. Create OpenLibraryApiClient with the WebClient
    // 4. Stub a successful response for a specific ISBN
    // 5. Call the client and verify the response
  }

  @Test
  void shouldHandleServerErrorWhenApiReturns500() {
    // TODO:
    // 1. Set up WireMock server
    // 2. Configure WebClient and OpenLibraryApiClient
    // 3. Stub a 500 response for a specific ISBN
    // 4. Call the client and verify that the expected exception is thrown
  }
}
