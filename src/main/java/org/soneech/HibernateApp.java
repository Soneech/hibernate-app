package org.soneech;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.soneech.model.Person;

import java.util.List;

public class HibernateApp {
    public static void main( String[] args ) {
        // + Automatically reads properties from hibernate.properties
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            // HQL work with entity
            List<Person> people = session.createQuery("FROM Person WHERE name LIKE 'T%'").getResultList();
            for (Person person: people) {
                System.out.println(person);
            }

            session.createQuery("update Person set name='Test' where age > 25").executeUpdate();

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
