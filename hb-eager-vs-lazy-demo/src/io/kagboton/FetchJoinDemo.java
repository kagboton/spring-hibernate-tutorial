package io.kagboton;

import io.kagboton.hibernate.demo.entity.Course;
import io.kagboton.hibernate.demo.entity.Instructor;
import io.kagboton.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class FetchJoinDemo {

    public static void main(String[] args) {

        // create section factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // retrieve session object from factory
        Session session = sessionFactory.getCurrentSession();

        try{

            // begin the transaction
            session.beginTransaction();

            // lazy loading option 2 : hibernate query with HQL

            // get the instructor from db
            int theId = 3;

            Query<Instructor> query =
                    session.createQuery("select i from Instructor i " +
                                    "JOIN FETCH i.courses " +
                                    "WHERE i.id=:theInstructorId",
                            Instructor.class);

            // set parameter on query
            query.setParameter("theInstructorId", theId);

            // execute query and get instructor

            Instructor theInstructor = query.getSingleResult();

            System.out.println("io.kagboton: Instructor: "+ theInstructor);

            session.getTransaction().commit();

            // close the session
            session.close();

            System.out.println("io.kagboton : Session is now close");

            //get course for the instructor using HQL query
            System.out.println("io.kagobton : Courses :" + theInstructor.getCourses());

            System.out.println("Done!");

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            // close the session
            session.close();

            // close the factory
            sessionFactory.close();

        }
    }
}
