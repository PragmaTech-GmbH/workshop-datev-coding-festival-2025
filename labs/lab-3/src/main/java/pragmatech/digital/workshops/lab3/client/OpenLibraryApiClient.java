package pragmatech.digital.workshops.lab3.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pragmatech.digital.workshops.lab3.dto.BookMetadataResponse;

/**
 * Client for interacting with the OpenLibrary API.
 */
@Component
public class OpenLibraryApiClient {

  private final WebClient webClient;
  private final ObjectMapper objectMapper;

  public OpenLibraryApiClient(WebClient openLibraryWebClient, ObjectMapper objectMapper) {
    this.webClient = openLibraryWebClient;
    this.objectMapper = objectMapper;
  }

  public BookMetadataResponse getBookByIsbn(String isbn) {
    ObjectNode node = webClient.get()
      .uri(
        "/api/books",
        uriBuilder ->
          uriBuilder
            .queryParam("jscmd", "data")
            .queryParam("format", "json")
            .queryParam("bibkeys", isbn)
            .build())
      .retrieve()
      .bodyToMono(ObjectNode.class)
      .block();

    return objectMapper.convertValue(node.get(isbn), BookMetadataResponse.class);
  }
}
