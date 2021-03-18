package io.kagboton;

import io.kagboton.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteOneCourseDemo {

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // get session from session factory
        Session session = factory.getCurrentSession();

        try{

            // start a transaction
            session.beginTransaction();

            // get the course]
            int theId = 10;
            Course theCourse = session.get(Course.class, theId);

            System.out.println("Course loaded: " + theCourse);

            // delete the course
            System.out.println("Deleting the course ...");

            session.delete(theCourse);

            System.out.println("Course deleted");

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        }catch (Exception e){
            e.printStackTrace();
            session.close();
        }finally {
            factory.close();
        }
    }



}
