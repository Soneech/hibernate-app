package org.soneech;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.soneech.model.Person;

public class HibernateApp {
    public static void main( String[] args ) {
        // + Automatically reads properties from hibernate.properties
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person1 = new Person("Test1", 30);
            Person person2 = new Person("Test2", 20);
            Person person3 = new Person("Test3", 25);

            session.persist(person1);
            session.persist(person2);
            session.persist(person3);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }
}
