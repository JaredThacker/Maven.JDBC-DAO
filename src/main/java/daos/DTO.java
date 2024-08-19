package daos;

import models.Car;

import java.sql.*;

import static daos.ConnectionFactory.getConnection;

public class DTO{

    public static Car extractUserFromResultSet(ResultSet rs) throws SQLException {
        Car car = new Car();

        car.setId( rs.getLong("id") );
        car.setColor( rs.getString("color"));
        car.setMake(rs.getString("make"));
        car.setModel(rs.getString("model"));
        car.setYear(rs.getInt("year"));
        car.setVin(rs.getLong("vin"));

        return car;
    }
}
