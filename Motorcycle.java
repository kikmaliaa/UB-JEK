public class Motorcycle extends Vehicle {
    public Motorcycle(String plateNumber, String model) {
        super(plateNumber, model);
    }

    @Override
    public String getType() {
        return "Motorcycle";
    }

    @Override
    public double calculateBaseFare() {
        return 5000; // base fare for motorcycle
    }
}