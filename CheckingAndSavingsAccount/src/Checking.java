public class Checking extends BankAccount {

    double balance;

    public Checking() {
        super();

    }

    public double deposit(double addBalance) {
        return balance += addBalance;
    }

    public double withdraw(double subBalance) {
        return balance -= subBalance;
    }

}
