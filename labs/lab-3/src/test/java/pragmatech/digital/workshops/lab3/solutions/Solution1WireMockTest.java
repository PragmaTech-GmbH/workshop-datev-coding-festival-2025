package pragmatech.digital.workshops.lab3.solutions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import pragmatech.digital.workshops.lab3.client.OpenLibraryApiClient;
import pragmatech.digital.workshops.lab3.dto.BookMetadataResponse;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Solution for Exercise 1: Testing the OpenLibraryApiClient with WireMock
 * <p>
 * This test demonstrates how to:
 * 1. Set up WireMock using the JUnit 5 extension
 * 2. Configure WebClient to point to WireMock
 * 3. Create test cases for successful responses and error handling
 */
class Solution1WireMockTest {

  @RegisterExtension
  static WireMockExtension wireMockServer = WireMockExtension.newInstance()
    .options(wireMockConfig().dynamicPort().notifier(new ConsoleNotifier(true)))
    .build();

  private OpenLibraryApiClient cut;

  @BeforeEach
  void setUp() {
    WebClient webClient = WebClient.builder()
      .baseUrl(wireMockServer.baseUrl())
      .build();

    cut = new OpenLibraryApiClient(webClient, new ObjectMapper());
  }

  @Test
  void shouldReturnBookMetadataWhenApiReturnsValidResponse() {
    // Arrange
    String isbn = "9780132350884";

    wireMockServer.stubFor(
      get(urlPathEqualTo("/api/books"))
        .willReturn(aResponse()
          .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
          .withBodyFile(isbn + "-success.json"))
    );

    // Act
    BookMetadataResponse result = cut.getBookByIsbn(isbn);

    // Assert
    assertThat(result).isNotNull();
    assertThat(result.title()).isEqualTo("Clean Code");
    assertThat(result.getIsbn13()).isEqualTo("9780132350884");
    assertThat(result.getPublisher()).isEqualTo("Prentice Hall");
    assertThat(result.numberOfPages()).isEqualTo(431);
  }

  @Test
  void shouldHandleServerErrorWhenApiReturns500() {
    // Arrange
    String isbn = "9999999999";

    wireMockServer.stubFor(
      get(urlPathEqualTo("/api/books"))
        .willReturn(aResponse()
          .withStatus(500)
          .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
          .withBody("{\"error\": \"Internal Server Error\"}")));

    // Act & Assert
    WebClientResponseException exception = assertThrows(WebClientResponseException.class, () ->
      cut.getBookByIsbn(isbn)
    );

    assertThat(exception.getStatusCode().value()).isEqualTo(500);
  }
}
