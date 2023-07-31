package peaksoft.dateBaseConnection;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import peaksoft.entity.Author;
import peaksoft.entity.Book;
import peaksoft.entity.Publisher;
import peaksoft.entity.Reader;

import java.util.Properties;

public class HibernateConnection {
    public static SessionFactory createSessionFactory(){
        Properties properties = new Properties();
        properties.setProperty(Environment.DRIVER,"org.postgresql.Driver");
        properties.setProperty(Environment.URL,"jdbc:postgresql://localhost:5432/postgres");
        properties.setProperty(Environment.USER,"postgres");
        properties.setProperty(Environment.PASS,"postgres");
        properties.setProperty(Environment.HBM2DDL_AUTO,"update");
        properties.setProperty(Environment.DIALECT,"org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty(Environment.SHOW_SQL,"false");
        Configuration configuration = new Configuration();
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(Author.class);
        configuration.addAnnotatedClass(Book.class);
        configuration.addAnnotatedClass(Publisher.class);
        configuration.addAnnotatedClass(Reader.class);
        return configuration.buildSessionFactory();
    }
    public static EntityManagerFactory createEntityManagerFactory(){
        return createSessionFactory().unwrap(EntityManagerFactory.class);
    }
}
