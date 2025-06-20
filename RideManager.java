import java.util.*;

public class RideManager {
    private List<User> users;
    private List<Driver> drivers;
    private Scanner scanner;
    private User currentUser;

    public RideManager() {
        users = new ArrayList<>();
        drivers = new ArrayList<>();
        scanner = new Scanner(System.in);
        seedData();
    }

    private void seedData() {
        drivers.add(new Driver("Pak Budi", new Car("B1234CD", "Toyota Avanza")));
        drivers.add(new Driver("Pak Agus", new Car("B5678EF", "Daihatsu Xenia")));
        drivers.add(new Driver("Mbak Sari", new Motorcycle("B9101GH", "Yamaha Nmax")));
        drivers.add(new Driver("Mas Andre", new Motorcycle("B1213JK", "Honda Beat")));
    }

    public void start() {
        System.out.println("=== Selamat Datang di UB-JEK ===");
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Registrasi");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                login();
                if (currentUser != null) {
                    menuUser();
                }
            } else if (choice == 2) {
                register();
            } else if (choice == 3) {
                System.out.println("Terima kasih sudah menggunakan UB-JEK!");
                break;
            }
        }
    }

    private void login() {
        System.out.print("Masukkan username: ");
        String uname = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String pwd = scanner.nextLine();
        for (User u : users) {
            if (u.getUsername().equals(uname) && u.checkPassword(pwd)) {
                currentUser = u;
                System.out.println("Login berhasil!");
                return;
            }
        }
        System.out.println("Username atau Password salah.\n");
        currentUser = null;
    }

    private void register() {
        System.out.print("Masukkan username baru: ");
        String uname = scanner.nextLine();
        System.out.print("Masukkan password baru: ");
        String pwd = scanner.nextLine();
        User u = new User(uname, pwd);
        users.add(u);
        System.out.println("Registrasi berhasil. Silakan login.\n");
    }

    private void menuUser() {
        while (true) {
            System.out.println("\n=== Menu Utama ===");
            System.out.println("1. Pesan Transportasi");
            System.out.println("2. Lihat Riwayat Pemesanan");
            System.out.println("3. Logout");
            System.out.print("Pilih menu: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                orderRide();
            } else if (choice == 2) {
                showHistory();
            } else if (choice == 3) {
                currentUser = null;
                System.out.println("Logout berhasil.\n");
                break;
            }
        }
    }

    private void orderRide() {
        // Pilih kendaraan
        System.out.println("Pilih jenis kendaraan:");
        System.out.println("1. Mobil");
        System.out.println("2. Motor");
        System.out.print("Masukkan pilihan: ");
        int vOpt = scanner.nextInt();
        scanner.nextLine();

        // Filter driver yang sesuai
        List<Driver> filtered = new ArrayList<>();
        for (Driver d : drivers) {
            if ((vOpt == 1 && d.getVehicle() instanceof Car) ||
                (vOpt == 2 && d.getVehicle() instanceof Motorcycle)) {
                filtered.add(d);
            }
        }

        // Pilih driver random
        Driver driver = filtered.get(new Random().nextInt(filtered.size()));

        // Input tujuan dan jarak
        System.out.print("Masukkan tujuan: ");
        String tujuan = scanner.nextLine();
        System.out.print("Masukkan jarak (km): ");
        int jarak = scanner.nextInt();
        scanner.nextLine();

        // Promo
        System.out.print("Gunakan promo? (y/n): ");
        boolean promo = scanner.nextLine().trim().equalsIgnoreCase("y");

        // Buat ride
        Ride ride = new Ride(currentUser, driver, driver.getVehicle(), tujuan, jarak);
        double tarif = ride.calculateFare(jarak, promo);
        System.out.println("Tarif perjalanan: Rp" + tarif);

        // Pilih metode pembayaran
        Payment payment = choosePayment();
        ride.setPayment(payment);

        // Proses pembayaran
        if (ride.pay()) {
            ride.printInvoice();
            currentUser.addRide(ride);
        } else {
            System.out.println("Pemesanan gagal karena pembayaran gagal.");
        }
    }

    private Payment choosePayment() {
        System.out.println("Pilih metode pembayaran:");
        System.out.println("1. Tunai");
        System.out.println("2. eWallet");
        System.out.println("3. Kartu Kredit");
        System.out.print("Masukkan pilihan: ");
        int payOpt = scanner.nextInt();
        scanner.nextLine();
        if (payOpt == 1) {
            return new Cash();
        } else if (payOpt == 2) {
            System.out.print("Nama eWallet: ");
            String wallet = scanner.nextLine();
            System.out.print("Saldo eWallet: ");
            double saldo = scanner.nextDouble();
            scanner.nextLine();
            return new EWallet(wallet, saldo);
        } else if (payOpt == 3) {
            System.out.print("Nomor Kartu: ");
            String card = scanner.nextLine();
            System.out.print("Nama di Kartu: ");
            String owner = scanner.nextLine();
            System.out.print("Limit kartu: ");
            double limit = scanner.nextDouble();
            scanner.nextLine();
            return new CreditCard(card, owner, limit);
        } else {
            System.out.println("Pilihan tidak valid, default ke Tunai.");
            return new Cash();
        }
    }

    private void showHistory() {
        List<Ride> rides = currentUser.getRideHistory();
        if (rides.isEmpty()) {
            System.out.println("Belum ada riwayat pemesanan.");
        } else {
            System.out.println("=== Riwayat Pemesanan ===");
            int idx = 1;
            for (Ride r : rides) {
                System.out.println("Pemesanan #" + (idx++));
                r.printInvoice();
            }
        }
    }
}