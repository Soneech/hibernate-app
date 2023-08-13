package org.soneech;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.soneech.model.Actor;
import org.soneech.model.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HibernateApp {
    public static void main( String[] args ) {
        // + Automatically reads properties from hibernate.properties
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try (sessionFactory) {
            session.beginTransaction();

            Movie movie = new Movie("Pulp fiction", 1994);
            Actor actor1 = new Actor("Harvey Keitel", 81);
            Actor actor2 = new Actor("Samuel L. Jackson", 72);

            movie.setActors(new ArrayList<>(List.of(actor1, actor2)));

            actor1.setMovies(new ArrayList<>(Collections.singletonList(movie)));
            actor2.setMovies(new ArrayList<>(Collections.singletonList(movie)));

            session.persist(movie);
            session.persist(actor1);
            session.persist(actor2);

            Movie movie1 = session.get(Movie.class, 1);
            movie1.getActors().forEach(System.out::println);

            Movie movie2 = new Movie("Reservoir Dogs", 1992);
            Actor actor = session.get(Actor.class, 1);

            movie2.setActors(new ArrayList<>(Collections.singletonList(actor)));
            actor.getMovies().add(movie2);

            session.persist(movie2);

            session.getTransaction().commit();
        }
    }
}
