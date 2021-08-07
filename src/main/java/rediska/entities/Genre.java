package rediska.entities;
import org.hibernate.annotations.Check;
import javax.persistence.*;

@Entity
@Check(constraints = "id <> parent_id")
@Table(name = "genres")
public
class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column (nullable = false)
    String name;

    @OneToOne
    Genre parent;

    public Genre() {}

    public Genre (String name) {
        this(name, null);
    }

    public Genre (String name, Genre parent) {
        this.name   = name;
        this.parent = parent;
    }
}