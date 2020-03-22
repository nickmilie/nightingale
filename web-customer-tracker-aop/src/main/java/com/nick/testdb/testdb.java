package com.nick.testdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "TestDbServlet")
public class testdb extends javax.servlet.http.HttpServlet {
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String user = "springstudent";
        String password = "springstudent";

        String url = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&amp;amp;serverTimezone=UTC";
        String driver = "com.msql.cj.jdbc.Driver";

        try{
            PrintWriter out = response.getWriter();

            out.println("Connecting database: " + url);

            Class.forName(driver);

            Connection connection = DriverManager.getConnection(url, user, password);

            System.out.println("SUCCESS!");

            connection.close();
        }
        catch (Exception exc){
            exc.printStackTrace();
            throw  new ServletException(exc);
        }

    }
}
