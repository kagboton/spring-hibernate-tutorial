package com.kagboton.hibernate.demo.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {

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
            Student student = new Student("Phillip", "Cocrane", "pcocrane@mail.com");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the student ...");
            session.save(student);

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }finally {
            factory.close();
        }

    }
}
