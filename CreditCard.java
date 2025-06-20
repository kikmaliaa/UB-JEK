public class CreditCard extends Payment {
    private String cardNumber;
    private String owner;
    private double limit;

    public CreditCard(String cardNumber, String owner, double limit) {
        this.methodName = "CreditCard";
        this.cardNumber = cardNumber;
        this.owner = owner;
        this.limit = limit;
    }

    @Override
    public boolean processPayment(double amount) {
        if (amount <= limit) {
            limit -= amount;
            System.out.println("Pembayaran menggunakan Kartu Kredit (" + cardNumber + ") sebesar Rp" + amount + " berhasil. Sisa limit: Rp" + limit);
            return true;
        } else {
            System.out.println("Limit kartu kredit tidak cukup.");
            return false;
        }
    }
}