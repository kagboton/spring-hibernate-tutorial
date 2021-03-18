package io.kagboton;

import io.kagboton.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {

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

            // create a course
            Course myCourse = new Course("Pacman - How to score 1B points");

            System.out.println("Saving the course...");

            // save the course
            session.save(myCourse);

            System.out.println("Course saved: " + myCourse);

            // create students
            Student tempStudent1 = new Student("John", "Doe", "john@kagboton.io");
            Student tempStudent2 = new Student("Mary", "Public", "mary@kagboton.io");


            // add students to course
            myCourse.addStudent(tempStudent1);
            myCourse.addStudent(tempStudent2);

            // save the students
            System.out.println("Saving the students");
            session.save(tempStudent1);
            session.save(tempStudent2);
            System.out.println("Saved the students: " + myCourse.getStudents());

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
