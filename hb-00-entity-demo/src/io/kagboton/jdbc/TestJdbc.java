package io.kagboton.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
        String user = "hb_student";
        String pwd = "hb_student";

        try{
            System.out.println("Connection to database: " + jdbcUrl);

            Connection myConn =
                    DriverManager.getConnection(jdbcUrl, user, pwd);

            System.out.println("Connection successful!!!");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
