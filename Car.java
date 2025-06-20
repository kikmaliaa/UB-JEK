public class Car extends Vehicle {
    public Car(String plateNumber, String model) {
        super(plateNumber, model);
    }

    @Override
    public String getType() {
        return "Car";
    }

    @Override
    public double calculateBaseFare() {
        return 8000; // base fare for car
    }
}