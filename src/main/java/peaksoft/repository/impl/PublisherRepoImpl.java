package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import peaksoft.dateBaseConnection.HibernateConnection;
import peaksoft.entity.Author;
import peaksoft.entity.Publisher;
import peaksoft.entity.Reader;
import peaksoft.repository.PublisherRepository;

import java.util.ArrayList;
import java.util.List;

public class PublisherRepoImpl implements PublisherRepository {
    private final EntityManagerFactory entityManagerFactory = HibernateConnection.createEntityManagerFactory();
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public Publisher savePublisher(Publisher publisher) {
        entityManager.getTransaction().begin();
        entityManager.persist(publisher);
        entityManager.getTransaction().commit();
        entityManager.close();
        return publisher;
    }

    @Override
    public Publisher getPublisherById(Long id) {
        entityManager.getTransaction().begin();
        Publisher publisher = entityManager.find(Publisher.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return publisher;
    }

    @Override
    public List<Publisher> getAllPublishers() {
        entityManager.getTransaction().begin();
        Query nativeQuery = entityManager.createNativeQuery("select * from publishers;");
        Publisher publisher = (Publisher) nativeQuery.getSingleResult();
        List<Publisher> publishers = new ArrayList<>();
        publishers.add(publisher);
        return publishers;
    }

    @Override
    public Publisher updatePublisher(Publisher publisher, Long id) {
        entityManager.getTransaction().begin();
        Publisher publisher1 = entityManager.find(Publisher.class, id);
        publisher1.setName(publisher.getName());
        publisher1.setAddress(publisher.getAddress());
        entityManager.getTransaction().commit();
        return publisher;
    }



    @Override
    public String deletePublisherByName(String name) {

            entityManager.getTransaction().begin();

            Query updateQuery = entityManager.createQuery("update Book b set b.publisher = null where b.publisher.name = :name");
            updateQuery.setParameter("name", name);
            int numUpdated = updateQuery.executeUpdate();

            Query deleteQuery = entityManager.createQuery("delete from Publisher p where p.name = :name");
            deleteQuery.setParameter("name", name);
            int numDeleted = deleteQuery.executeUpdate();

            entityManager.getTransaction().commit();


            entityManager.close();
        return "Successfully deleted " + numDeleted + " publisher(s) and updated " + numUpdated + " book(s) associated with the publisher.";

    }


}


