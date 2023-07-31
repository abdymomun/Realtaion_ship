package peaksoft.service.impl;

import peaksoft.entity.Author;
import peaksoft.entity.Book;
import peaksoft.repository.BookRepository;
import peaksoft.repository.impl.BookRepoImpl;
import peaksoft.service.BookService;

import java.util.Map;
import java.util.Objects;

public class BookServiceImpl implements BookService {
    private BookRepository bookRepository = new BookRepoImpl();

    @Override
    public String saveBook(Book book, Long IdAuthor, Long pubId) {
        return bookRepository.saveBook(book, IdAuthor, pubId);
    }

    @Override
    public void updateBookAuthor(Book book, Long IdBook, Long IdAuthor, Author author) {
        bookRepository.updateBookAuthor(book, IdBook, IdAuthor, author);
    }

    @Override
    public void getBookAndPublisherByBookId(Long id) {
        bookRepository.getBookAndPublisherByBookId(id);
    }

    @Override
    public String deleteBookByAuthorId(Long id) {
        return bookRepository.deleteBookByAuthorId(id);
    }
}
