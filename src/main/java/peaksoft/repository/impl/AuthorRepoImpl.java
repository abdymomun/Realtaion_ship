package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import peaksoft.dateBaseConnection.HibernateConnection;
import peaksoft.entity.Author;
import peaksoft.entity.Book;
import peaksoft.entity.Publisher;
import peaksoft.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AuthorRepoImpl implements AuthorRepository {
    private final EntityManagerFactory entityManagerFactory = HibernateConnection.createEntityManagerFactory();
    private EntityManager entityManager = entityManagerFactory.createEntityManager();
    @Override
    public String saveAuthor(Author author) {
        entityManager.getTransaction().begin();
        entityManager.persist(author);
        entityManager.getTransaction().commit();
        entityManager.close();
        return "Succesfuly saved author with id: "+author.getId();
    }

    @Override
    public Author updateAuthor(Long id,Author author) {
        entityManager.getTransaction().begin();
        Author author1 = entityManager.find(Author.class, id);
        author1.setFirstName(author.getFirstName());
        author1.setLastName(author.getLastName());
        author1.setEmail(author.getEmail());
        author1.setCountry(author.getCountry());
        author1.setDateOfBirth(author.getDateOfBirth());
        author1.setGender(author.getGender());
        return author;
    }

    @Override
    public Author getAuthorById(Long id) {
        entityManager.getTransaction().begin();
        Author author = entityManager.find(Author.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return author;
    }
    @Override
    public Author getAuthorsByPublisherId(Long publisherId) {

            entityManager.getTransaction().begin();

            Query result = entityManager.createQuery(
                            "select a from Author a join  a.publishers p where p.id = :publisherId", Author.class)
                    .setParameter("publisherId", publisherId);

            Author author = (Author) result.getSingleResult();

            entityManager.getTransaction().commit();
            entityManager.close();
            return author;

    }




    @Override
    public String deleteAuthorById(Long id) {
        entityManager.getTransaction().begin();
        Author author = entityManager.find(Author.class, id);
        if (author != null) {
            List<Author> authors = new ArrayList<>();
            authors.add(author);
            for (Author author1 : authors) {
                entityManager.remove(author1);
                List<Book> books = author.getBooks();
                for (Book b:books) {
                    entityManager.remove(b);
                }
            }
            entityManager.getTransaction().commit();
        }
        return "Succesfuly deleted author with id: "+id;

    }

    @Override
    public String assignAuthorToPublisher(Long idPublisher,Long authorId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Publisher publisher = entityManager.find(Publisher.class, idPublisher);
            List<Publisher>publishers = new ArrayList<>();
            publishers.add(publisher);
            Author author = entityManager.find(Author.class,authorId);
           List<Author>authors = new ArrayList<>();
authors.add(author);
            publisher.setAuthors(authors);
            author.setPublishers(publishers);

            entityManager.getTransaction().commit();
            return "Successfully assigned author to publisher.";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return "Error occurred. Assignment failed.";
        } finally {
            entityManager.close();
        }
    }

}