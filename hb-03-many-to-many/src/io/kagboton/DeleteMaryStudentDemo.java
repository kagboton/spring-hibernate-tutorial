package io.kagboton;

import io.kagboton.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteMaryStudentDemo {

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

            // delete the student
            System.out.println("Deleting the student:" + mary);

            session.delete(mary);

            System.out.println("Student deleted");

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
