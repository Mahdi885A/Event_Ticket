package util;

import exception.DatabaseConfigException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final  String PASSWORD = "ZqX.7391";
    public static final String USER = "postgres";

    public static Connection getConnection(){
        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("connected");
            return connection;


        }
        catch (SQLException e){
            throw new DatabaseConfigException("Connection to Database Failed!");
        }
    }

}
