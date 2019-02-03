public class SavingsAccount extends Account{

    //variables
    private double interestRate = 0.1;
    private double tempBalance = 0.00;
    private double totalInterestEarned = 0.00;



    public SavingsAccount(String name, double balance, int type) {

        super(name, balance, type);

    }

    @Override
    public void addInterest() {

        //calculates and adds interest to balance
        tempBalance = this.getBalance();
        tempBalance *= interestRate;
        this.deposit(tempBalance);

        //calculates and keeps track of total cumulative interest earned.
        totalInterestEarned += tempBalance;
    }

    public double getTotalInterest(){
        return totalInterestEarned;
    }
}
