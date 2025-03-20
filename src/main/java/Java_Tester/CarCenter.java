package Java_Tester;

public class CarCenter {
    public static void main(String[] args) {
        //Kiểu class
        Car car = new Car();

        System.out.println(car.getCarColor());

        car.setCarColor("green");
        System.out.println(car.getCarColor());
    }
}
