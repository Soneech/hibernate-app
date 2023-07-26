package org.soneech;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.soneech.model.Item;
import org.soneech.model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HibernateApp {
    public static void main( String[] args ) {
        // + Automatically reads properties from hibernate.properties
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person = session.get(Person.class, 3);
            System.out.println(person);
            Item item = new Item("Lamp", person);
            person.getItems().add(item);  // good practice (due to Hibernate caching)
            session.persist(item);

            Person person1 = new Person("Ann", 25);
            Item item1 = new Item("Pen", person1);
            person1.setItems(new ArrayList<>(Collections.singletonList(item1)));
            session.persist(person1);
            session.persist(item1);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
