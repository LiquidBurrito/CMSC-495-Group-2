public class Savings extends BankAccount {
    int days;
    public Savings(){
        days = 0;
        super();
    }

    public void calcInterest(){
        if(days>30){
            this.deposit(interestAccrued);
        }

        public double interestAccrued() {
            return savings = savings * 0.015;
        }

}
