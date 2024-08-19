package models;

import daos.DAO;
import daos.DTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static daos.ConnectionFactory.getConnection;

public class Car implements DAO<Car>{
    private static Long id;
    private static String model;
    private static String make;
    private static String color;
    private static Long vin;
    private static Integer year;

    public Car() {
    }

    public Car(String model, String make, String color, Long vin, Integer year) {
        this.model = model;
        this.make = make;
        this.color = color;
        this.vin = vin;
        this.year = year;
    }

    public Car(Long id, String model, String make, String color, Long vin, Integer year) {
        this.id = id;
        this.model = model;
        this.make = make;
        this.color = color;
        this.vin = vin;
        this.year = year;
    }

    public static Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public static String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public static String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static Long getVin() {
        return vin;
    }

    public void setVin(Long vin) {
        this.vin = vin;
    }

    public static Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", make='" + make + '\'' +
                ", color='" + color + '\'' +
                ", vin=" + vin +
                ", year=" + year +
                '}';
    }

    @Override
    public Car getByID(Long id) {
        Connection connection = getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cars WHERE id=" + id);

            if(rs.next())
            {
                return DTO.extractUserFromResultSet(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public List getAll() {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM cars";
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next())
            {
                cars.add(new Car(rs.getLong("id"),rs.getString("model"),rs.getString("make"),rs.getString("color"),rs.getLong("vin"),rs.getInt("year")));
            }
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cars;
    }

    @Override
    public boolean create(Car DTO) {
        String query = "INSERT INTO cars (model,make,color,vin,year) VALUES (?,?,?,?,?)";
        try {
            Connection connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, Car.getModel());
            pstm.setString(2, Car.getMake());
            pstm.setString(3, Car.getColor());
            pstm.setLong(4, Car.getVin());
            pstm.setInt(5, Car.getYear());
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean update(Car DTO) {

        String query = "UPDATE cars SET model = ?,make = ?,color = ?,vin = ?,year = ? WHERE id = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, DTO.getModel());
            pstm.setString(2, DTO.getMake());
            pstm.setString(3, DTO.getColor());
            pstm.setLong(4, DTO.getVin());
            pstm.setInt(5, DTO.getYear());
            pstm.setLong(6, DTO.getId());
            pstm.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM cars WHERE id =?";
        try {
            Connection connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setLong(1,id);
            pstm.executeUpdate();

            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
