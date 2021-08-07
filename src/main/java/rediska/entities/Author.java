package rediska.entities;

import javax.persistence.*;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "first_name")
    String firstName;

    @Column (name = "middle_name")
    String middleName;
    @Column (name = "last_name", nullable = false)
    String lastName;

    public Author (String firstName, String middleName, String lastName) {
        this.firstName  = firstName;
        this.middleName = middleName;
        this.lastName   = lastName;
    }

    public Author () {}
}
