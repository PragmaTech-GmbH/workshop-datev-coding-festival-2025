package pragmatech.digital.workshops.lab4.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pragmatech.digital.workshops.lab4.client.OpenLibraryApiClient;
import pragmatech.digital.workshops.lab4.dto.BookMetadataResponse;

@Configuration
public class InitializationConfig {

  private static final Logger logger = LoggerFactory.getLogger(InitializationConfig.class);

  private final OpenLibraryApiClient openLibraryApiClient;

  public InitializationConfig(OpenLibraryApiClient openLibraryApiClient) {
    this.openLibraryApiClient = openLibraryApiClient;
  }

  @Bean
  public CommandLineRunner initializeBookMetadata() {
    return args -> {
      logger.info("Starting book metadata initialization...");

      // Sample ISBNs for well-known programming books
      List<String> sampleIsbns = List.of(
        "9780132350884",  // Clean Code by Robert C. Martin
        "9780201633610",  // Design Patterns by Gang of Four
        "9780134757599"   // Effective Java by Joshua Bloch
      );

      logger.info("Fetching metadata for {} books", sampleIsbns.size());

      for (String isbn : sampleIsbns) {
        BookMetadataResponse metadata = this.openLibraryApiClient.getBookByIsbn(isbn);
        logger.info("Fetched metadata for ISBN {} - {}", isbn, metadata);
      }

      logger.info("Book metadata initialization completed");
    };
  }
}
