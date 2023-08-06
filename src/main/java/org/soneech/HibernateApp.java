package org.soneech;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.soneech.model.Director;
import org.soneech.model.Movie;

import java.util.List;


public class HibernateApp {
    public static void main( String[] args ) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Director.class)
                .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            // task 2
            Director director = session.get(Director.class, 2);
            System.out.println(director);
            List<Movie> movies = director.getMovies();
            for (Movie movie: movies) {
                System.out.println(movie);
            }

            // task 3
            Movie movie = session.get(Movie.class, 3);
            System.out.println(movie.getDirector());
            System.out.println(movie);

            // task 4
            Movie movie1 = new Movie("Whatever Works", 2009);
            Director director1 = session.get(Director.class, 4);
            director1.addMovie(movie1);

            session.persist(director1);

            // task 5
            Director director2 = new Director("George Walton Lucas", 79);
            Movie movie2 = new Movie("Star Wars. Episode IV: A New Hope", 1977);
            director2.addMovie(movie2);

            session.persist(director2);

            // task 6
            Movie movie3 = session.get(Movie.class, 3);
            Director director3 = session.get(Director.class, 5);
            movie3.setDirector(director3);

            // task 7
            Director director4 = session.get(Director.class, 1);
            session.remove(director4.getMovies().get(1));

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
