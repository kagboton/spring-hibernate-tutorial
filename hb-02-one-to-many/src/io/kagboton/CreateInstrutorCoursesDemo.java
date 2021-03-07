package io.kagboton;

import io.kagboton.hibernate.demo.entity.Course;
import io.kagboton.hibernate.demo.entity.Instructor;
import io.kagboton.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstrutorCoursesDemo {

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
            // create the objects
            Instructor instructor = new Instructor("Rudy", "Gulliani", "rudy@mail.com");
            InstructorDetail instructorDetail = new InstructorDetail("rudyChannel", "Do research");
            Course course = new Course("SVT");

            // associate the objects
            instructor.setInstructorDetail(instructorDetail);
            instructor.addCourse(course);

            // begin the transaction
            session.beginTransaction();

            // save instructor
            session.save(instructor);

            // save course
            session.save(course);

            // commit the transaction
            System.out.println("Saving instructor: "+ instructor);
            System.out.println("Saving course: "+ course);
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
