package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBPropertyUtil {

    private static final String HOSTNAME = "localhost";
    private static final String PORT = "3306";
    private static final String DBNAME = "PetPlatform";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "@lzebKhan1";

    public static String getConnectionString() {
        return "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DBNAME 
               + "?user=" + USERNAME + "&password=" + PASSWORD;
    }
}

