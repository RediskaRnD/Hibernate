package rediska;

import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import rediska.entities.Author;
import rediska.entities.Book;
import rediska.entities.Genre;
import rediska.entities.Publisher;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;


@Log4j2
public class BookStore {

    @SuppressWarnings("SpellCheckingInspection")
    public static void main (String[] args) {

        try (val factory = getSessionFactory()) {
            try (val session = factory.openSession()) {
                val transaction = session.beginTransaction();
                val rock        = new Genre("Rock");
                val punkRock    = new Genre("PunkRock", rock);
                val metalRock   = new Genre("MetalRock", rock);

                var author      = new Author("Vytautas", null, "Petkevicius");
                var publisher   = new Publisher("Tabula rasa");
                var publishDate = new GregorianCalendar(2003, Calendar.JANUARY, 1);
                var book = new Book("Durniu laivas", "9789986625421", 100500, 100.0f, publishDate, publisher);
//                book.genres.add(metalRock);
                book.authors.add(author);
                session.save(book);

                publisher   = new Publisher("Vaga");
                publishDate = new GregorianCalendar(1990, Calendar.JANUARY, 1);
                book = new Book("Kaip gimsta baubas: Apybraižos", "9785415006755", 504, 99.992f, publishDate, publisher);
                book.authors.add(author);
                session.save(book);

                session.flush();
                transaction.commit();
            }
        }
    }

    private static SessionFactory getSessionFactory () {
        val configuration = new Configuration();
        configuration.addAnnotatedClass(Author.class);
        configuration.addAnnotatedClass(Book.class);
        configuration.addAnnotatedClass(Genre.class);
        configuration.addAnnotatedClass(Publisher.class);
        val builder = new StandardServiceRegistryBuilder().build();
        return configuration.buildSessionFactory(builder);
    }
}
