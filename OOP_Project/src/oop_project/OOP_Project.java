/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package oop_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author AMAL
 */
public class OOP_Project {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
       try {
            // Load the MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database connection details
            String url = "jdbc:mysql://localhost:3306/hope";
            String user = "root";
            String password = "1234";

            // Establish the connection
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful: " + con);

            // Close the connection
            con.close();

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
   
         Login LoginFrame = new Login();
         LoginFrame.setVisible(true);
         LoginFrame.pack();// This adjusts the frame size based on its components
         LoginFrame.setLocationRelativeTo(null); // Centers the frame on the screen
        
    }
    
}
