import models.Car;

public class AppRunner {

    public static void main(String[] args) {
        Car car = new Car();
        System.out.println(car.getByID(1L));
        System.out.println(car.getAll());
        System.out.println(car.delete(1L));
        System.out.println(car.getAll());

    }
}
