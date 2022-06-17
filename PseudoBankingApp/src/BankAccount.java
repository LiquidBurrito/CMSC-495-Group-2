public class BankAccount {
    Double balance;

    public BankAccount() {
        balance = 0.0;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(Double value) {
        balance = balance + value;
    }

    public void withdrawChecking(Double value) {
        if (balance - value < 0) {
            Throw exception;
            balance = balance - value;
        }

        public int getDays () {
            return accountData.get(“Days”);
        }

    }
}
