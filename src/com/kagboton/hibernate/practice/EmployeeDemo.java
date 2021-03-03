package com.kagboton.hibernate.practice;

import com.kagboton.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EmployeeDemo {

    public static void main(String[] args) {

        // create section factory
        SessionFactory employeeFactory = new Configuration()
                .configure("hibernate.employee.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        // retrieve session object from factory
        Session session = employeeFactory.getCurrentSession();

        try{
            // begin the transaction
            session.beginTransaction();

            // create 3 employees
            System.out.println("Creating 3 employees...");
            Employee employee1 = new Employee("Tony", "Almeida", "FBI");
            Employee employee2 = new Employee("Jack", "Bauer", "CIA");
            Employee employee3 = new Employee("Mac", "Guyver", "DPJ");
            Employee employee4 = new Employee("Mike", "Caffret", "FBI");

            // save the employees
            System.out.println("Saving created employees...");
            session.save(employee1);
            session.save(employee2);
            session.save(employee3);
            session.save(employee4);

            // commit the transaction
            session.getTransaction().commit();

            // retrieve an employee id=2
            int employeeId = 2;
            session = employeeFactory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Getting employee id: "+ employeeId);
            Employee myEmployee = session.get(Employee.class, employeeId);

            System.out.println(myEmployee);

            session.getTransaction().commit();


            // find employees of FBI
            session = employeeFactory.getCurrentSession();
            session.beginTransaction();

            List<Employee> employees = session.createQuery("from Employee where company='FBI'").getResultList();

            session.getTransaction().commit();

            System.out.println("List of the employee of FBI");
            for (Employee e: employees) {
                System.out.println(e);
            }

            System.out.println("Done!");

        }finally {
            employeeFactory.close();
            // close the factory
        }
    }
}
