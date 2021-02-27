package com.kagboton.hibernate.demo.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {

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
            System.out.println("Creating 3 students objects ...");
            Student student1 = new Student("Paul", "Street", "paul@mail.com");
            Student student2 = new Student("John", "Doe", "john@mail.com");
            Student student3 = new Student("Mary", "Public", "mary@mail.com");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the students ...");
            session.save(student1);
            session.save(student2);
            session.save(student3);

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }finally {
            factory.close();
        }
    }
}
