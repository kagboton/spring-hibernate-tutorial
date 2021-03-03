package com.kagboton.hibernate.demo.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UpdateStudentDemo {

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

            int studentId = 1;

            // start a transaction
            session.beginTransaction();

            // retrieve a student based on the id: primary key
            System.out.println("Getting student with the id: " + studentId);
            Student student = session.get(Student.class, studentId);

            // update the student firstname
            System.out.println("Updating the student...");
            student.setFirstName("Scooby");

            // commit the transaction
            session.getTransaction().commit();


            // Another update :: update email for all student

            session = factory.getCurrentSession();
            session.beginTransaction();

            // update email for all students
            System.out.println("Update email for all students");
            session.createQuery("update Student set email='foo@mail.com'").executeUpdate();

            session.getTransaction().commit();


            System.out.println("Done!");
        }finally {
            factory.close();
        }

    }

}
