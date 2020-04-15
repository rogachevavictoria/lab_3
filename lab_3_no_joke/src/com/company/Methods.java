package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Methods {

    static void output(ResultSet rows) {
        try {
            System.out.format("%-3s|%-20s|%-20s|%-10s|%-10s|%-20s|%-20s", "id" , "airline_name", "alias", "iata", "icao", "callsign", "country");
            System.out.println("\n+--+--------------------+--------------------+----------+----------+--------------------+--------------------");
            while (rows.next()) {
                System.out.format("%-3d|%-20s|%-20s|%-10s|%-10s|%-20s|%-20s",
                        rows.getInt("id"),
                        rows.getString("airline_name"),
                        rows.getString("alias"),
                        rows.getString("iata"),
                        rows.getString("icao"),
                        rows.getString("callsign"),
                        rows.getString("country"));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static String add() {
        Scanner scan = new Scanner(System.in);
        LinkedHashMap<String, String> rows = new LinkedHashMap<String, String>(4);
        String column;
        boolean check = false;
        rows.put("id", " ");
        rows.put("airline_name", " ");
        rows.put("alias", " ");
        rows.put("iata", " ");
        rows.put("icao", " ");
        rows.put("callsign", " ");
        rows.put("country", " ");
        Set set = rows.entrySet();
        while(!check) {
            Iterator i = set.iterator();
            while (i.hasNext()) {
                Map.Entry me = (Map.Entry)i.next();
                column = (String)me.getKey();
                System.out.print("New "+column + ": ");
                rows.put(column, scan.nextLine());
            }
            check = true;
        }
        String values = "\""+rows.get("id")+"\""+ ", " +"\""+ rows.get("airline_name")+"\""+ ", " +"\""+ rows.get("alias")+"\""
        +", " +"\""+ rows.get("iata")+"\""+", " +"\""+ rows.get("icao")+"\""+", " +"\""+ rows.get("callsign")+"\""+", " +"\""+ rows.get("country")+"\"";
        String add = "INSERT INTO airlines (id , airline_name, alias, iata, icao, callsign, country)"
                + "VALUES" + "(" + values + ")" +";";
        return add;
    }
        public static void write(ResultSet rows){
            try {
                FileWriter myWriter = new FileWriter("airlines.txt");
                myWriter.write("id   airline_name    alias   iata    icao    callsign    country");
                    while (rows.next()) {
                        myWriter.write(rows.getInt("id")+"  "+rows.getString("airline_name")+"  "
                        +rows.getString("alias")+"  "+rows.getString("iata")+"  "+rows.getString("icao")+"  "
                        +rows.getString("callsign")+"  "+rows.getString("country")+"\n"); }
                myWriter.close();
            } catch (IOException | SQLException e) {}
        }

}

