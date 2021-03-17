package io.kagboton.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/hb-02-one-to-many-uni?useSSL=false";
        String user = "root";
        String pwd = "root";

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
