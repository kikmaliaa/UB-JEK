public class Ride {
    private User user;
    private Driver driver;
    private Vehicle vehicle;
    private String destination;
    private int distance; // in km
    private double fare;
    private Payment payment;

    public Ride(User user, Driver driver, Vehicle vehicle, String destination, int distance) {
        this.user = user;
        this.driver = driver;
        this.vehicle = vehicle;
        this.destination = destination;
        this.distance = distance;
    }

    // Method Overloading: calculateFare
    public double calculateFare(int distance) {
        fare = vehicle.calculateBaseFare() + distance * 2000;
        return fare;
    }

    public double calculateFare(int distance, boolean promo) {
        double base = calculateFare(distance);
        if (promo) {
            fare = base * 0.8; // 20% promo
        } else {
            fare = base;
        }
        return fare;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public boolean pay() {
        if (payment != null) {
            return payment.processPayment(fare);
        }
        return false;
    }

    public void printInvoice() {
        System.out.println("====== INVOICE ======");
        System.out.println("Nama Pengguna : " + user.getUsername());
        System.out.println("Driver        : " + driver.getName());
        System.out.println("Kendaraan     : " + vehicle.getType() + " (" + vehicle.getModel() + ")");
        System.out.println("Plat Nomor    : " + vehicle.getPlateNumber());
        System.out.println("Tujuan        : " + destination);
        System.out.println("Jarak         : " + distance + " km");
        System.out.println("Metode Bayar  : " + payment.getMethodName());
        System.out.println("Total Bayar   : Rp" + fare);
        System.out.println("=====================");
    }
}