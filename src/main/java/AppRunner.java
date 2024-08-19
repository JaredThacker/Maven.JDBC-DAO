import models.Car;

public class AppRunner {

    public static void main(String[] args) {
        Car car = new Car();
        System.out.println(car.getByID(6L));
        System.out.println(car.update(new Car(6L, "honda", "civic", "blue", 1298471298748912L, 2002)));
        System.out.println(car.getByID(6L));
    }
}
