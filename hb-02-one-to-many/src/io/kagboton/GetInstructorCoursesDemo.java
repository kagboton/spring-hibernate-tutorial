package io.kagboton;

import io.kagboton.hibernate.demo.entity.Course;
import io.kagboton.hibernate.demo.entity.Instructor;
import io.kagboton.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorCoursesDemo {

    public static void main(String[] args) {

        // create section factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // retrieve session object from factory
        Session session = sessionFactory.getCurrentSession();

        try{

            // begin the transaction
            session.beginTransaction();

            // get the instructor from db
            int theId = 2;
            Instructor theInstructor = session.get(Instructor.class, theId);

            //get course for the instructor
            System.out.println("Courses :" + theInstructor.getCourses());

            session.getTransaction().commit();

            System.out.println("Done!");

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            // close the session
            session.close();

            // close the factory
            sessionFactory.close();

        }
    }
}
