package io.kagboton;

import io.kagboton.hibernate.demo.entity.Instructor;
import io.kagboton.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CreateDemo {

    public static void main(String[] args) {

        // create section factory
        SessionFactory employeeFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // retrieve session object from factory
        Session session = employeeFactory.getCurrentSession();

        try{
            // create the objects
            Instructor instructor = new Instructor("Rudy", "Gulliani", "rudy@mail.com");
            InstructorDetail instructorDetail = new InstructorDetail("rudyChannel", "Do research");

            // associate the objects
            instructor.setInstructorDetail(instructorDetail);

            // begin the transaction
            session.beginTransaction();

            // save instructor
            session.save(instructor);

            // commit the transaction
            System.out.println("Saving instructor: "+ instructor);
            session.getTransaction().commit();

            System.out.println("Done!");

        }finally {
            employeeFactory.close();
            // close the factory
        }
    }
}
