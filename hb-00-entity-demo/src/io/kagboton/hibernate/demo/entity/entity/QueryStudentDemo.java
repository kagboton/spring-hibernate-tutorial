package io.kagboton.hibernate.demo.entity.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

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
            // start a transaction
            session.beginTransaction();

            // query students
            List<Student> theStudents = session.createQuery("from Student").getResultList();

            // display students
            displayStudents(theStudents);

            // query students: lastname:'Doe'
            theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();

            //display students
            System.out.println("\n\nStudents who has last name of Doe");
            displayStudents(theStudents);

            // query students: lastName='Doe or firstName='Daff'
            theStudents = session.createQuery("from Student s where  s.lastName='Doe'" +
                    "or s.firstName='Daff'").getResultList();

            System.out.println("\n Student who has lastname of Doe or firstname of Daff");
            displayStudents(theStudents);

            //

            // commit the transaction
            session.getTransaction().commit();


            System.out.println("Done!");
        }finally {
            factory.close();
        }

    }

    private static void displayStudents(List<Student> theStudents) {
        for(Student tempStudent : theStudents){
            System.out.println(tempStudent);
        }
    }
}
