package io.kagboton;

import io.kagboton.hibernate.demo.entity.Course;
import io.kagboton.hibernate.demo.entity.Instructor;
import io.kagboton.hibernate.demo.entity.InstructorDetail;
import io.kagboton.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviewsDemo {

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

            // create a course
            Course newCourse = new Course("Pacman - How to score 1B points");

            // add some reviews
            newCourse.addReview(new Review("Great Course, love it"));
            newCourse.addReview(new Review("Just excellent"));
            newCourse.addReview(new Review("Awersome course"));


            // save the course .. and leverage the cascade all
            System.out.println("Saving the course: " + newCourse);
            System.out.println(newCourse.getReviews());

            session.save(newCourse);

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
