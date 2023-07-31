package peaksoft;

import peaksoft.dateBaseConnection.HibernateConnection;
import peaksoft.entity.Author;
import peaksoft.entity.Book;
import peaksoft.entity.Publisher;
import peaksoft.entity.Reader;
import peaksoft.enums.Gender;
import peaksoft.enums.Genre;
import peaksoft.service.AuthorService;
import peaksoft.service.BookService;
import peaksoft.service.PublisherService;
import peaksoft.service.ReaderService;
import peaksoft.service.impl.AuthorServiceImpl;
import peaksoft.service.impl.BookServiceImpl;
import peaksoft.service.impl.PublisherServiceImpl;
import peaksoft.service.impl.ReaderServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        AuthorService authorService = new AuthorServiceImpl();
        BookService bookService = new BookServiceImpl();
        PublisherService publisherService = new PublisherServiceImpl();
        ReaderService readerService = new ReaderServiceImpl();

       try {
            HibernateConnection.createSessionFactory();
            Author author = new Author
                    ("Алыкул", "Осмонов", "a@gmail.com",
                            LocalDate.of(1915, 3, 12), "Кыргызстан", Gender.Male);
            Author author1 = new Author("Аалы", "Токомбаев", "t@gmail.com",
                    LocalDate.of(1904, 11, 7), "Кыргызстан", Gender.Male);
            Publisher publisher = new Publisher("Типография Издательства Турар", "ул. Горького, Бишкек");
            Book book = new Book("Волны Иссык-Куля", "Кыргызстан", LocalDate.of(1944, 11, 10), BigDecimal.valueOf(2000), Genre.Biography);
            Publisher publisher1 = new Publisher("Limon.KG", " Бишкек");

            Book book1 = new Book("Токтолбойт толкун", "Кыргызстан", LocalDate.of(1945, 9, 10), BigDecimal.valueOf(2999), Genre.Drama);
            Author author2 = new Author("Чингиз ", "Айтматов", "Ch@gmail.com", LocalDate.of(1928, 12, 12), "Кыргызстан", Gender.Male);
            Reader reader = new Reader("Abdymomun","Abdymomun@gmail.com");
            Reader reader1 = new Reader("Junuazak","Anashov@gmail.com");
            while (true) {
                switch (new Scanner(System.in).nextLine()) {
                    case "1" -> System.out.println(authorService.saveAuthor(author));
                    case "2" -> System.out.println(authorService.updateAuthor(1L, author1));
                    case "3" -> System.out.println(authorService.getAuthorById(1L));
                    case "4" -> System.out.println(authorService.getAuthorsByPublisherId(1L));
                    case "5" -> System.out.println(authorService.deleteAuthorById(1L));
                    case "6" -> System.out.println(authorService.assignAuthorToPublisher(1L, 1L));
                    case "7" -> System.out.println(publisherService.savePublisher(publisher));
                    case "8" -> System.out.println(publisherService.getPublisherById(1L));
                    case "9" -> System.out.println(publisherService.getAllPublishers());
                    case "10" -> System.out.println(publisherService.updatePublisher(publisher1, 1L));
                    case "11" -> System.out.println(publisherService.deletePublisherByName("Limon.KG"));
                    case "12" -> System.out.println(bookService.saveBook(book, 1L, 1L));
                    case "13" -> bookService.updateBookAuthor(book1, 3L, 1L, author2);
                    case "14" -> bookService.getBookAndPublisherByBookId(1L);
                    case "15" -> System.out.println(bookService.deleteBookByAuthorId(1L));
                    case "16" -> System.out.println(readerService.saveReader(reader,1L));
                    case "17" -> System.out.println(readerService.updateReader(1L,reader1));
                    case "18" -> System.out.println(readerService.getReaderByBookId(1L));
                    case "19" -> System.out.println(readerService.deleteReaderById(1L));
                    case "20" -> System.out.println(readerService.getReadersByAuthorId(1L));

                }
            }
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
}
