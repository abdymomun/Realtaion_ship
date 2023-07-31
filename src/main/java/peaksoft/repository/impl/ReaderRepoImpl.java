package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import peaksoft.dateBaseConnection.HibernateConnection;
import peaksoft.entity.Author;
import peaksoft.entity.Book;
import peaksoft.entity.Reader;
import peaksoft.repository.ReaderRepository;

import java.util.ArrayList;
import java.util.List;

public class ReaderRepoImpl implements ReaderRepository,AutoCloseable {

    private final EntityManagerFactory entityManagerFactory = HibernateConnection.createEntityManagerFactory();
    private EntityManager entityManager = entityManagerFactory.createEntityManager();
    @Override
    public String saveReader(Reader reader,Long bookId) {
        entityManager.getTransaction().begin();
        entityManager.persist(reader);
        Book book = entityManager.find(Book.class, bookId);
        book.setReader(reader);
        reader.setBook(book);
        entityManager.getTransaction().commit();

        return "Succesfuly saved reader with id " + reader.getId();
    }

    @Override
    public Reader updateReader(Long id,Reader reader) {
        entityManager.getTransaction().begin();
        Reader reader1 = entityManager.find(Reader.class, id);
        reader1.setName(reader.getName());
        reader1.setEmail(reader.getEmail());
        entityManager.getTransaction().commit();
        return reader;
    }


//        entityManager.getTransaction().begin();
//        entityManager.find(Book.class,bookId);
//        Query query = entityManager.createNativeQuery("select r from readers r join books b on b.id = r.book_id where b.id = ?").setParameter(1, bookId);
//        Reader reader = (Reader) query.getSingleResult();
//        entityManager.getTransaction().commit();
//        return reader;
        @Override
        public List<Reader> getReaderByBookId(Long bookId) {
            String queryString = "select r from Reader r join r.book b where b.id = :bookId";
            TypedQuery<Reader> query = entityManager.createQuery(queryString, Reader.class);
            query.setParameter("bookId", bookId);
            List<Reader> readers = query.getResultList();
            return readers;
        }
    @Override
    public String deleteReaderById(Long id) {
//        entityManager.getTransaction().begin();
//        Reader reader = entityManager.find(Reader.class, id);
//        entityManager.remove(reader);
        entityManager.getTransaction().begin();
        Reader reader = entityManager.find(Reader.class, id);
        if (reader != null) {
            List<Reader> readers =  new ArrayList<>();
            readers.add(reader);
            for (Reader reader1 : readers) {
                entityManager.remove(reader1);
            }
            entityManager.getTransaction().commit();
        }
        return "Succesfuly deleted Reader with id :" +id;
    }

    @Override
    public Reader getReadersByAuthorId(Long authorId) {
        entityManager.getTransaction().begin();

        //Author author = entityManager.find(Author.class, authorId);
        Query query = entityManager.createQuery("SELECT r FROM Reader r JOIN r.book b JOIN b.author a WHERE a.id = :authorId");
        query.setParameter("authorId", authorId);

        Reader reader = (Reader) query.getSingleResult();
        entityManager.getTransaction().commit();

        return reader;
    }


    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
        entityManager.close();
    }
}
