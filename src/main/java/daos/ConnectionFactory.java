package daos;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static final String URL = "jdbc:mysql://localhost:3306/vehicles";
    public static final String USER = "j";
    public static final String PASS = "fluffy";

    public static Connection getConnection(){
        try {
            DriverManager.registerDriver(new Driver());
            System.out.println("I found the database!");
            return DriverManager.getConnection(URL,USER,PASS);
        } catch (SQLException ex){
            throw new RuntimeException("ERROR! Connecting to database failed.", ex);
        }
    }

    public static void main(String[] args) {
        Connection connection = ConnectionFactory.getConnection();
    }


}
