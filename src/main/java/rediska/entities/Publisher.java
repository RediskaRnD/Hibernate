package rediska.entities;

import javax.persistence.*;

@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column (nullable = false)
    String name;

    public Publisher () {}

    public Publisher (String name) {
        this.name = name;
    }
}
