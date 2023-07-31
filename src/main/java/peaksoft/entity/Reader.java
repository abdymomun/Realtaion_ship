package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "readers")
@Getter
@Setter
@NoArgsConstructor
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "reader_gen")
    @SequenceGenerator(name = "reader_gen",sequenceName = "reader_seq",allocationSize = 1)
    private Long id;
    private String name;
    private String email;
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REMOVE})
    private Book book;

    public Reader(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", book=" + book +
                '}';
    }
}
