package io.kagboton.hibernate.demo.entity.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        // use the session object to save a Java object
        try {
            // create a student object
            System.out.println("Creating a new student object ...");
            Student student = new Student("Daff", "Punk", "daff@mail.com");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the student ...");
            System.out.println(student);
            session.save(student);

            // commit the transaction
            session.getTransaction().commit();

            // NEW CODE

            // find out the student's id: primary key
            System.out.println("Saved student. Generated id: "  + student.getId());

            // now get the new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve student based on the id: primary key
            System.out.println("Getting student with id: "  + student.getId());
            Student retrievedStudent = session.get(Student.class, student.getId());
            System.out.println("Get completed. " + retrievedStudent);

            // commit transaction
            session.getTransaction().commit();



            System.out.println("Done!");
        }finally {
            factory.close();
        }

    }
}
