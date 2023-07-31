package peaksoft.service;

import peaksoft.entity.Author;
import peaksoft.entity.Book;

import java.util.Map;
import java.util.Objects;

public interface BookService {
    //Book да
    String saveBook(Book book, Long IdAuthor,Long pubId);
    // (Book сакталып жатканда кандайдыр бир авторго тиешелуу болуп сакталуусу керек),
   void updateBookAuthor(Book book, Long IdBook, Long IdAuthor, Author author);
    void getBookAndPublisherByBookId(Long id);
    //(Бир Id ге тиешелуу book тун маалыматтары жана ага тиешелуу издательствосу чыксын),
    String  deleteBookByAuthorId(Long id);
}
