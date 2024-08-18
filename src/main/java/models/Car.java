package models;

public class Car {
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
}
