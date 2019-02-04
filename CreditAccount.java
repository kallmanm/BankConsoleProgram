public class CreditAccount extends Account {

    //variables
    private double interestRate = 0.2;
    private double tempBalance = 0.00;
    private double totalInterestEarned = 0.00;

    public CreditAccount(String name, double balance, int type, int acctNumber) {

        super(name, balance, type, acctNumber);

    }

    @Override
    public double withdraw (double amount, double fee)
    {

        double newBalance = super.getBalance();
        newBalance = newBalance - amount - fee;
        if (newBalance <= 0 && newBalance >= -1000) {
            setBalance(newBalance);

            return newBalance;
        }
        else{
            System.out.println("You can't overdraw!");
            return newBalance;
        }

    }

    @Override
    public double deposit (double amount)
    {

        double newBalance = super.getBalance();
        newBalance = newBalance + amount;

        if (newBalance <= 0) {
            setBalance(newBalance);

            return newBalance;
        } else {
            System.out.println("Invalid input");
            return newBalance;
        }

    }

    public void addInterest() {

        //calculates and adds interest to balance
        tempBalance = this.getBalance();
        tempBalance *= interestRate;
        this.deposit(tempBalance);

        //calculates and keeps track of total cumulative interest earned.
        totalInterestEarned += tempBalance;

    }

    //Overriding SetBalance because Accounts version has a safe guard against negetive values.
    //need to be able to input neg value to credit account
    @Override
    public void setBalance(double balance){
        this.balance = balance;
    }

    //Gets the total amount of accumulated interest.
    public double getTotalInterest(){
        return totalInterestEarned;
    }
}
