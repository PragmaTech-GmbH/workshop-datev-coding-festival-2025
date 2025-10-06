package pragmatech.digital.workshops.lab4.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

public class OpenLibraryApiStub {

  private final WireMockServer wireMockServer;

  public OpenLibraryApiStub(WireMockServer wireMockServer) {
    this.wireMockServer = wireMockServer;
  }

  public void stubForSuccessfulBookResponse(String isbn) {
    this.wireMockServer.stubFor(
      get(urlPathEqualTo("/api/books"))
        .withQueryParam("jscmd", equalTo("data"))
        .withQueryParam("format", equalTo("json"))
        .withQueryParam("bibkeys", equalTo(isbn))
        .willReturn(
          aResponse()
            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .withBodyFile(isbn + "-success.json")));
  }
}
