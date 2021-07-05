package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnect {

    private static Connection connection = null;

    public static Connection dbConnect(){
        try {

            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/veritabani7?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey","root","1234");

            return connection;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
