package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "publishers")
@Getter
@Setter
@NoArgsConstructor

public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "publisher_gen")
    @SequenceGenerator(name = "publisher_gen",sequenceName = "publisher_seq",allocationSize = 1)
    private Long id;
    private String name;
    private String address;
    @ManyToMany(mappedBy = "publishers",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Author>authors;
    @OneToMany(mappedBy = "publisher",cascade = CascadeType.ALL)
    private List<Book>books;

    public Publisher(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
