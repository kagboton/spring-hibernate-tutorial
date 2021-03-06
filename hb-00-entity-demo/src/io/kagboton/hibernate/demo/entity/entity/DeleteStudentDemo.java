package io.kagboton.hibernate.demo.entity.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {

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

            int studentId = 5;

            // start a transaction
            session.beginTransaction();

            // retrieve a student based on the id: primary key
            System.out.println("Getting student with the id: " + studentId);
            Student student = session.get(Student.class, studentId);

            // delete the student
            System.out.println("Deleting the student: " + studentId);
            session.delete(student);

            // commit the transaction
            session.getTransaction().commit();


            // Another delete via query

            session = factory.getCurrentSession();
            session.beginTransaction();

            // delete student id=6
            System.out.println("Delete the student");
            session.createQuery("delete Student where id=6").executeUpdate();

            session.getTransaction().commit();

            System.out.println("Done!");
        }finally {
            factory.close();
        }

    }

}
