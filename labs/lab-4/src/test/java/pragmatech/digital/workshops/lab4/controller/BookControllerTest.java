package pragmatech.digital.workshops.lab4.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import pragmatech.digital.workshops.lab4.dto.BookUpdateRequest;
import pragmatech.digital.workshops.lab4.entity.Book;
import pragmatech.digital.workshops.lab4.entity.BookStatus;
import pragmatech.digital.workshops.lab4.service.BookService;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private BookService bookService;

    @Nested
    class UpdateBook {

        @Test
        void shouldReturnOkWhenValidBookUpdateRequest() throws Exception {
            BookUpdateRequest updateRequest = new BookUpdateRequest(
                "Updated Title",
                "Updated Author",
                LocalDate.of(2020, 1, 1),
                BookStatus.AVAILABLE
            );

            Book updatedBook = createUpdatedBook();
            when(bookService.updateBook(eq(1L), any(BookUpdateRequest.class)))
                .thenReturn(Optional.of(updatedBook));

            mockMvc.perform(put("/api/books/{id}", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk());
        }

        @Test
        void shouldReturnNotFoundWhenBookDoesNotExist() throws Exception {
            BookUpdateRequest updateRequest = new BookUpdateRequest(
                "Updated Title",
                "Updated Author",
                LocalDate.of(2020, 1, 1),
                BookStatus.AVAILABLE
            );

            when(bookService.updateBook(eq(999L), any(BookUpdateRequest.class)))
                .thenReturn(Optional.empty());

            mockMvc.perform(put("/api/books/{id}", 999L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isNotFound());
        }

        @Test
        void shouldReturnBadRequestWhenTitleIsBlank() throws Exception {
            BookUpdateRequest updateRequest = new BookUpdateRequest(
                "",
                "Valid Author",
                LocalDate.of(2020, 1, 1),
                BookStatus.AVAILABLE
            );

            mockMvc.perform(put("/api/books/{id}", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnBadRequestWhenAuthorIsBlank() throws Exception {
            BookUpdateRequest updateRequest = new BookUpdateRequest(
                "Valid Title",
                "",
                LocalDate.of(2020, 1, 1),
                BookStatus.AVAILABLE
            );

            mockMvc.perform(put("/api/books/{id}", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnBadRequestWhenPublishedDateIsNull() throws Exception {
            BookUpdateRequest updateRequest = new BookUpdateRequest(
                "Valid Title",
                "Valid Author",
                null,
                BookStatus.AVAILABLE
            );

            mockMvc.perform(put("/api/books/{id}", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnBadRequestWhenPublishedDateIsInFuture() throws Exception {
            BookUpdateRequest updateRequest = new BookUpdateRequest(
                "Valid Title",
                "Valid Author",
                LocalDate.now().plusDays(1),
                BookStatus.AVAILABLE
            );

            mockMvc.perform(put("/api/books/{id}", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnBadRequestWhenStatusIsNull() throws Exception {
            BookUpdateRequest updateRequest = new BookUpdateRequest(
                "Valid Title",
                "Valid Author",
                LocalDate.of(2020, 1, 1),
                null
            );

            mockMvc.perform(put("/api/books/{id}", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isBadRequest());
        }

        @Test
        void shouldUpdateBookWithAllValidFields() throws Exception {
            BookUpdateRequest updateRequest = new BookUpdateRequest(
                "New Title",
                "New Author",
                LocalDate.of(2021, 6, 15),
                BookStatus.BORROWED
            );

            Book updatedBook = createUpdatedBookWithAllFields();
            when(bookService.updateBook(eq(1L), any(BookUpdateRequest.class)))
                .thenReturn(Optional.of(updatedBook));

            mockMvc.perform(put("/api/books/{id}", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk());
        }

        private Book createUpdatedBook() {
            Book book = new Book("978-0134685991", "Updated Title", "Updated Author", LocalDate.of(2020, 1, 1));
            book.setId(1L);
            book.setStatus(BookStatus.AVAILABLE);
            return book;
        }

        private Book createUpdatedBookWithAllFields() {
            Book book = new Book("978-0134685991", "New Title", "New Author", LocalDate.of(2021, 6, 15));
            book.setId(1L);
            book.setStatus(BookStatus.BORROWED);
            return book;
        }
    }
}
