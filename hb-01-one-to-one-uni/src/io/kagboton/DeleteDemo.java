package io.kagboton;

import io.kagboton.hibernate.demo.entity.Instructor;
import io.kagboton.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

    public static void main(String[] args) {

        // create section factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // retrieve session object from factory
        Session session = sessionFactory.getCurrentSession();

        try{
            // begin the transaction
            session.beginTransaction();

            // get instructor by primary key / id
            int theId = 1;
            Instructor instructor = session.get(Instructor.class, theId);

            System.out.println("Found instructor: " + instructor);

            // delete the instructor
            if(instructor != null){
                System.out.println("Deleting: " + instructor);
                // the associate instructor detail will be deleted also because of Cascade.ALL
                session.delete(instructor);
            }

            // commit the transaction
            System.out.println("Instructor deleted");
            session.getTransaction().commit();

            System.out.println("Done!");

        }finally {
            sessionFactory.close();
            // close the factory
        }
    }
}
