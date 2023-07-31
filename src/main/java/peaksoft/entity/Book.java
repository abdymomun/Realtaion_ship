package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import peaksoft.enums.Genre;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "book_gen")
    @SequenceGenerator(name = "book_gen",sequenceName = "book_seq",allocationSize = 1)
    private Long id;
    private String name;
    private String country;
    @Column(name = "published_year")
    private LocalDate publishedYear;
    private BigDecimal price;
    private Genre genre;
    @ManyToOne(cascade = CascadeType.ALL)
    private Publisher publisher;
    @OneToOne(mappedBy = "book",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private Reader reader;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private Author author;

    public Book(String name, String country, LocalDate publishedYear, BigDecimal price, Genre genre) {
        this.name = name;
        this.country = country;
        this.publishedYear = publishedYear;
        this.price = price;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", publishedYear=" + publishedYear +
                ", price=" + price +
                ", genre=" + genre +
                '}';
    }
}
