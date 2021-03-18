package io.kagboton;

import io.kagboton.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesForMaryDemo {

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

            // get the student mary
            Student mary = session.get(Student.class, 2); // 2 is Mary ID in the current db, update this with your db ID

            System.out.println("Loaded student:" + mary);
            System.out.println("Courses: " + mary.getCourses());

            // create some courses
            Course tempCourse1 = new Course("Head First - Design Pattern");
            Course tempCourse2 = new Course("Java - To begining to advance");

            // add Mary to courses
            tempCourse1.addStudent(mary);
            tempCourse2.addStudent(mary);

            // save the courses
            System.out.println("Saving the courses...");
            session.save(tempCourse1);
            session.save(tempCourse2);

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
