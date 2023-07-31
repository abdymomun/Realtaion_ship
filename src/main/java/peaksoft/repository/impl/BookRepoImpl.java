package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import peaksoft.dateBaseConnection.HibernateConnection;
import peaksoft.entity.Author;
import peaksoft.entity.Book;
import peaksoft.entity.Publisher;
import peaksoft.repository.BookRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookRepoImpl implements BookRepository, AutoCloseable {
    private final EntityManagerFactory entityManagerFactory = HibernateConnection.createEntityManagerFactory();
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public String saveBook(Book book, Long IdAuthor, Long pubId) {
        entityManager.getTransaction().begin();
        Author author = entityManager.find(Author.class, IdAuthor);
        entityManager.persist(book);
        List<Book> books = new ArrayList<>();
        books.add(book);
        author.setBooks(books);
        book.setAuthor(author);

        Publisher publisher = entityManager.find(Publisher.class, pubId);
        book.setPublisher(publisher);
        publisher.setBooks(books);
        entityManager.getTransaction().commit();
        entityManager.close();

        return "Succesfuly saved book with id: " + book.getId();
    }

    @Override
    public void updateBookAuthor(Book book, Long IdBook, Long IdAuthor, Author author) {
        entityManager.getTransaction().begin();

        Book existingBook = entityManager.find(Book.class, IdBook);
        Author existingAuthor = entityManager.find(Author.class, IdAuthor);

        existingBook.setName(book.getName());
        existingBook.setCountry(book.getCountry());

        existingAuthor.setFirstName(author.getFirstName());
        existingAuthor.setLastName(author.getLastName());
        existingAuthor.setEmail(author.getEmail());
        existingAuthor.setDateOfBirth(author.getDateOfBirth());
        existingAuthor.setCountry(author.getCountry());
        existingAuthor.setGender(author.getGender());

        entityManager.getTransaction().commit();
        System.out.println();

    }

    @Override
    public void getBookAndPublisherByBookId(Long id) {
        entityManager.getTransaction().begin();
        Book book = entityManager.createQuery("select b from Book b join Publisher  p  on  b.publisher.id = p.id where b.id = :id", Book.class).setParameter("id", id).getSingleResult();
      //  List<Publisher> publishers = entityManager.createQuery("SELECT p FROM Publisher p JOIN p.books b WHERE b.id = :id", Publisher.class)
      //          .setParameter("id", id).getResultList();
        System.out.println(book);
        System.out.println(book.getPublisher());
        entityManager.getTransaction().commit();
        entityManager.close();
    }


    @Override
    public String deleteBookByAuthorId(Long id) {

            entityManager.getTransaction().begin();
            Author author = entityManager.find(Author.class, id);
            if (author != null) {
                List<Book> books = author.getBooks();
                for (Book book : books) {
                    entityManager.remove(book);
                }
                entityManager.getTransaction().commit();
            }
        return "Successfully deleted Books by Author with id " + id;


    }
    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
}
