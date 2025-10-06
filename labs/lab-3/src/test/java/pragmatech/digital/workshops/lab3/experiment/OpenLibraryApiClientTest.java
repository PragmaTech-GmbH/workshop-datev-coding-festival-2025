package pragmatech.digital.workshops.lab3.experiment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
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
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OpenLibraryApiClientTest {

  @RegisterExtension
  static WireMockExtension wireMockServer = WireMockExtension.newInstance()
    .options(wireMockConfig().dynamicPort()
      .notifier(new ConsoleNotifier(true)))
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
        .withQueryParam("jscmd", WireMock.equalTo("data"))
        .withQueryParam("format", WireMock.equalTo("json"))
        .withQueryParam("bibkeys", WireMock.equalTo(isbn))
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
  void shouldThrowExceptionWhenBookNotFound() {
    // Arrange
    String isbn = "9999999999";

    wireMockServer.stubFor(
      get("/api/books" + isbn)
        .withQueryParam("jscmd", WireMock.equalTo("data"))
        .withQueryParam("format", WireMock.equalTo("json"))
        .withQueryParam("bibkeys", WireMock.equalTo(isbn))
        .willReturn(aResponse()
          .withStatus(404)));

    // Act & Assert
    assertThrows(WebClientResponseException.class, () ->
      cut.getBookByIsbn(isbn)
    );
  }
}
