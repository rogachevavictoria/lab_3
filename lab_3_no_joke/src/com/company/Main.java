package com.company;

import java.sql.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Connection con = getConnection();
        String select = "SELECT * FROM airlines;";
        Scanner scan = new Scanner(System.in);
        boolean exit = false;
        try {
            Statement s = con.createStatement();
            ResultSet rows = s.executeQuery(select);
            Methods.output(rows);
            Methods.write(rows);
            while(!exit) {

                System.out.println("Press: add - 1, exit - 2:");
                int n = scan.nextInt();
                switch(n) {
                    case 1: s.addBatch(Methods.add()); break;
                    case 2: exit = true; System.exit(0);
                }
                s.executeBatch();
                rows = s.executeQuery(select);
                Methods.output(rows);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static Connection getConnection()
    {	//get a connection to database
        Connection con = null;
        String url = "jdbc:mysql://localhost/airlines?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String pw = "npass";
        try {
            con = DriverManager.getConnection(url, user, pw);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }}
