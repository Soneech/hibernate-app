package org.soneech;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.soneech.model.Passport;
import org.soneech.model.Person;


public class HibernateApp {
    public static void main( String[] args ) {
        // + Automatically reads properties from hibernate.properties
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Passport.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person = session.get(Person.class, 6);
            session.remove(person);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
