public class Cash extends Payment {
    public Cash() {
        this.methodName = "Cash";
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Pembayaran tunai sebesar Rp" + amount + " berhasil.");
        return true;
    }
}