package rediska.entities;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
@Check(constraints = "rating <= 100 AND rating >= 0 AND pages > 0")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(nullable = false)
    String title;

    @Column(unique = true, length = 13)
    String isbn;

    //    @Basic()
    Integer pages;

    @Column(columnDefinition = "DECIMAL(5,2)")
    Float rating;


    @Temporal(TemporalType.DATE)
    @Column(name = "publish_date")
    Calendar publishDate;

    @ManyToMany(cascade = CascadeType.ALL)
    public Set<Genre> genres = new HashSet<>();

    @ManyToMany (cascade = CascadeType.ALL)
    public Set<Author> authors = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    @LazyToOne(LazyToOneOption.NO_PROXY)
    Publisher publisher;

    public Book () {}

    public Book (String title, String isbn, Integer pages, Float rating, Calendar publishDate, Publisher publisher) {
        this.title       = title;
        this.isbn        = isbn;
        this.pages       = pages;
        this.rating      = rating;
        this.publishDate = publishDate;
        this.publisher   = publisher;
    }
}
