package io.kagboton;

import io.kagboton.hibernate.demo.entity.Course;
import io.kagboton.hibernate.demo.entity.Instructor;
import io.kagboton.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseDemo {

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

            // get course by primary key / id
            int theId = 10;
            Course theCourse = session.get(Course.class, theId);

            System.out.println("Found course: " + theCourse);

            // delete the instructor
            if(theCourse != null){
                System.out.println("Deleting: " + theCourse);

                session.delete(theCourse);
            }

            // commit the transaction
            System.out.println("Course deleted");
            session.getTransaction().commit();

            System.out.println("Done!");

        }finally {
            // close the factory
            sessionFactory.close();

        }
    }
}
