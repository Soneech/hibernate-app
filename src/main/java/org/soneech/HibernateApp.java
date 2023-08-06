package org.soneech;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.soneech.model.Principal;
import org.soneech.model.School;


public class HibernateApp {
    public static void main( String[] args ) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Principal.class)
                .addAnnotatedClass(School.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            // task 2
            Principal principal = session.get(Principal.class, 1);
            System.out.println(principal.getSchool().getSchoolNumber());

            // task 3
            School school = session.get(School.class, 3);
            Principal principal1 = school.getPrincipal();
            System.out.println(principal1);

            // task 4
            Principal principal2 = new Principal("Ann", 38);
            School school1 = new School(54);
            principal2.setSchool(school1);
            school1.setPrincipal(principal2);

            session.persist(principal2);

            // task 5
            Principal principal3 = new Principal("Mike", 29);

            principal2.setSchool(null);

            principal3.setSchool(school1);
            school1.setPrincipal(principal3);

            session.persist(principal3);

            // task 6
            School school2 = new School(81);
            school2.setPrincipal(principal3);
            // session.persist(school2); // error

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
