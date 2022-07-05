import java.util.HashMap;
import java.util.Map;
public class BankAccount {

    private Map<String, Object>account = new HashMap<>();
    private double checkingBalance = 0;
    private double savingsBalance = 0;
    private int dayz = 0;

    public BankAccount(){
        account.put("Username", "admin");
        account.put("Password", "password");
        account.put("Checking", checkingBalance);
        account.put("Savings", savingsBalance);
        account.put("Days", dayz);
    }

    public boolean checkUser(String usrnm){
        String s = (String) account.get("Username");
        return s.equalsIgnoreCase(usrnm);
    }

    public boolean checkPass(String pass){
        return account.get("Password").equals(pass);
    }


    public double getChecking(){
        return checkingBalance;
    }

    public double getSavings(){
        return savingsBalance;
    }

    public void depositCheck(Double value){
        checkingBalance = checkingBalance + value;
    }

    public void depositSaving(Double value){
        savingsBalance = savingsBalance + value;
    }

    public void withdrawChecking(Double value) {
        if (checkingBalance - value < 0)
            //Throw exception;
            checkingBalance = checkingBalance - value;

    }

    public void withdrawSaving (Double value) {
        if (savingsBalance - value < 0)
            //Throw exception;
            savingsBalance = savingsBalance - value;

    }

    public int getDays(){
        return (int) account.get("Days");
    }



    public void calcInterest() {
        int d = (int) account.get("Days");
        if (d > 30) {
            //this.deposit(interestAccrued);
        }
    }
    public double interestAccrued() {
        return savingsBalance = savingsBalance * 0.015;
    }



    /*
    int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public BankAccount() {

    }

    public void Deposit() {

    }

    public void Interest() {

    }

    @Override
    public String toString() {
        return super.toString();

    }

     */
}
