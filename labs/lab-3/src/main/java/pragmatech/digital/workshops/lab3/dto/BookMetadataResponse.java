package pragmatech.digital.workshops.lab3.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookMetadataResponse(
  String url,
  String key,
  String title,
  String subtitle,
  List<Author> authors,
  @JsonProperty("number_of_pages") Integer numberOfPages,
  Identifiers identifiers,
  Classifications classifications,
  List<Publisher> publishers,
  @JsonProperty("publish_date") String publishDate,
  List<Subject> subjects,
  Cover cover
) {

  public String getCoverUrl() {
    return cover().medium();
  }

  public String getIsbn13() {
    if (identifiers() != null && identifiers().isbn13() != null && !identifiers().isbn13().isEmpty()) {
      return identifiers().isbn13().get(0);
    }
    return null;
  }

  public String getPublisher() {
    if (publishers() != null && !publishers().isEmpty()) {
      return publishers().get(0).name();
    }
    return null;
  }
}

record Author(
  String url,
  String name
) { }

@JsonIgnoreProperties(ignoreUnknown = true)
record Identifiers(
  @JsonProperty("isbn_13") List<String> isbn13,
  @JsonProperty("isbn_10") List<String> isbn10,
  List<String> openlibrary
) { }

record Classifications(
  @JsonProperty("lc_classifications") List<String> lcClassifications
) { }

record Publisher(
  String name
) { }

record Subject(
  String name,
  String url
) { }

record Cover(
  String small,
  String medium,
  String large
) { }
