public class EWallet extends Payment {
    private String walletName;
    private double balance;

    public EWallet(String walletName, double balance) {
        this.methodName = "eWallet";
        this.walletName = walletName;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public boolean processPayment(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Pembayaran melalui eWallet " + walletName + " sebesar Rp" + amount + " berhasil. Sisa saldo: Rp" + balance);
            return true;
        } else {
            System.out.println("Saldo eWallet tidak cukup.");
            return false;
        }
    }
}