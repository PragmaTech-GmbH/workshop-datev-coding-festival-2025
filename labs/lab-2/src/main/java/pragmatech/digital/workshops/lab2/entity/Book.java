package pragmatech.digital.workshops.lab2.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String isbn;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String author;

  @Column(nullable = false)
  private LocalDate publishedDate;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private BookStatus status;

  // Default constructor for JPA
  protected Book() {
  }

  public Book(String isbn, String title, String author, LocalDate publishedDate) {
    this.isbn = isbn;
    this.title = title;
    this.author = author;
    this.publishedDate = publishedDate;
    this.status = BookStatus.AVAILABLE;
  }

  public Long getId() {
    return id;
  }

  public String getIsbn() {
    return isbn;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public LocalDate getPublishedDate() {
    return publishedDate;
  }

  public void setPublishedDate(LocalDate publishedDate) {
    this.publishedDate = publishedDate;
  }

  public BookStatus getStatus() {
    return status;
  }

  public void setStatus(BookStatus status) {
    this.status = status;
  }

  public boolean isAvailable() {
    return status == BookStatus.AVAILABLE;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Book book = (Book) o;
    if (id != null) {
      return id.equals(book.id);
    }
    return isbn.equals(book.isbn);
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : isbn.hashCode();
  }

  @Override
  public String toString() {
    return "Book{" +
      "id=" + id +
      ", isbn='" + isbn + '\'' +
      ", title='" + title + '\'' +
      ", author='" + author + '\'' +
      ", publishedDate=" + publishedDate +
      ", status=" + status +
      '}';
  }
}
