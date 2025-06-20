public abstract class Vehicle {
    private String plateNumber;
    private String model;

    public Vehicle(String plateNumber, String model) {
        this.plateNumber = plateNumber;
        this.model = model;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getModel() {
        return model;
    }

    public abstract String getType();

    public abstract double calculateBaseFare();

}