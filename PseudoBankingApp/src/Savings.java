public class Savings extends BankAccount {
    int days;
    public Savings(){
        days = 0;
        super();
    }

    public double interestAccrued() {
        return savings = savings * 0.015;
    }

    public void calcInterest() {
        if (days > 30) {
            this.deposit(interestAccrued);
        }


    }

}
