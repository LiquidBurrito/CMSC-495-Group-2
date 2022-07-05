package CheckingAndSavingsAccount.src;

public class Checking extends BankAccount {

    double balance;

    public Checking() {
        super();

    }

    public double deposit(double addBalance) {
        //if jcb

        return balance += addBalance;
    }

    public static boolean isDouble(String str) {

        return false;
    }

    public double withdraw(double subBalance) {
        return balance -= subBalance;
    }

}
