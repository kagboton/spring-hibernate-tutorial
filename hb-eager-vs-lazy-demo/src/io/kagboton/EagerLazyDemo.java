package io.kagboton;

import io.kagboton.hibernate.demo.entity.Course;
import io.kagboton.hibernate.demo.entity.Instructor;
import io.kagboton.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {

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
            int theId = 3;
            Instructor theInstructor = session.get(Instructor.class, theId);

            System.out.println("io.kagboton: Instructor: "+ theInstructor);

            // first call of courses getter to have courses in the memory
            System.out.println("io.kagobton : Courses :" + theInstructor.getCourses());

            session.getTransaction().commit();

            // close the session
            session.close();

            System.out.println("io.kagboton : Session is now close");

            // code below should fail since courses are lazy loaded - but we call the getter to have the courses in the memory

            //get course for the instructor
            System.out.println("io.kagobton : Courses :" + theInstructor.getCourses());

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
