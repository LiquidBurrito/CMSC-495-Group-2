public class Savings extends BankAccount {

    double balance;
    final double interest = 0.015;

    public Savings() {
        super();

    }

    public double deposit(double addBalance) {
        return balance += addBalance;
    }

    public double withdraw(double subBalance) {
        return balance -= subBalance;
    }

    public double accrueInterest() {
        return balance = balance * interest;
    }
}
