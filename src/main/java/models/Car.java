package models;

import daos.DAO;
import daos.DTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static daos.ConnectionFactory.getConnection;

public class Car implements DAO{
    private Long id;
    private String model;
    private String make;
    private String color;
    private Long vin;
    private Integer year;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getVin() {
        return vin;
    }

    public void setVin(Long vin) {
        this.vin = vin;
    }

    public Integer getYear() {
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
        String query = "INSERT INTO cars (make,year,color,vin,model) VALUES (?,?,?,?,?)";
        try {Connection connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, DTO.getMake());
            pstm.setInt(2, DTO.getYear());
            pstm.setString(3, DTO.getColor());
            pstm.setString(4, DTO.getModel());
            pstm.setLong(5, DTO.getVin());
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean update(Long id) {
//        String query = "UPDATE car SET make = ?,year = ?,color = ?,vin = ?,model = ? WHERE id = ?";
//        try {Connection connection = getConnection();
//            PreparedStatement pstm = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
//            pstm.setString(1, .getMake());
//            pstm.setInt(2,DTO.getYear());
//            pstm.setString(3,DTO.getColor());
//            pstm.setLong(4,DTO.getVin());
//            pstm.setString(5,DTO.getModel());
//            pstm.setLong(6,DTO.getId());
//            pstm.executeUpdate();
//
//
//            return DTO;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM cars WHERE id =?";
        try {
            Connection connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setLong(1,id);
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
