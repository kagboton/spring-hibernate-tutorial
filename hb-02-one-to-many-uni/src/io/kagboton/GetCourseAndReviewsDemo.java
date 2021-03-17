package io.kagboton;

import io.kagboton.hibernate.demo.entity.Course;
import io.kagboton.hibernate.demo.entity.Instructor;
import io.kagboton.hibernate.demo.entity.InstructorDetail;
import io.kagboton.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCourseAndReviewsDemo {

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        // get session from session factory
        Session session = factory.getCurrentSession();

        try{

            // start a transaction
            session.beginTransaction();

            // get a course
            int courseId = 10;
            Course theCourse = session.get(Course.class, courseId);

            System.out.println("Find course : " + theCourse);

            // get course reviews
            System.out.println("Course reviews: " + theCourse.getReviews());

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
