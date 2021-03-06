package io.kagboton;

import io.kagboton.hibernate.demo.entity.Instructor;
import io.kagboton.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {

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

            // get the instructor detail object
            int theId = 2;
            InstructorDetail instructorDetail =
                    session.get(InstructorDetail.class, theId);

            // print instructor detail
            System.out.println("Instructor detail: " + instructorDetail);

            // print associate instructor
            System.out.println("Associate instructor: " + instructorDetail.getInstructor());

            // delete instructor detail
            System.out.println("Deleting instructorDetail: " + instructorDetail);
            session.delete(instructorDetail);

            // commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // handle connection leak issue
            session.close();

            // close the factory
            sessionFactory.close();

        }
    }
}
