import java.text.NumberFormat;

public abstract class Account
{

   //static variable for unique acctNumber that increments in generateAccountNr()
   private static int counter =100;
   private String acctNumber;
   protected double balance;
   private String name;
   private int type;


   //-----------------------------------------------------------------
   //  Constructor
   //-----------------------------------------------------------------
   public Account (String name, double balance, int type)
   {

      setName(name);
      setAcctNumber(counter);
      setBalance(balance);
      setType(type);
      Account.generateAccountNr();
   }

   //-----------------------------------------------------------------
   //  Deposits the specified amount into the account. Returns the
   //  new balance.
   //-----------------------------------------------------------------
   public double deposit (double amount)
   {
      if(amount >= 0){
         balance = balance + amount;

         return balance;
      }else {
         System.out.println("Invalid input, try again...");
         return balance;
      }
   }

   //-----------------------------------------------------------------
   //  Withdraws the specified amount from the account and applies
   //  the fee. Returns the new balance.
   //-----------------------------------------------------------------
   public double withdraw (double amount, double fee)
   {
      if(balance>=amount && amount>= 0){
         balance = balance - amount - fee;

         return balance;
      }else{
         System.out.println("Invalid input, try again...");
         return balance;
      }
   }

   //-----------------------------------------------------------------
   //  Set Functions
   //-----------------------------------------------------------------
   public void setName(String name)
   {
      this.name = name;
   }
   public void setAcctNumber(int acctNumber) { this.acctNumber = Integer.toString(acctNumber); }
   public void setBalance(double balance){
      if(balance>=0){
         this.balance =balance;
      }else{
         System.out.println("Cannot be negetive value, input valid balance.");
      }
   }
   public void setType(int type){
      try{
         switch (type){
            case 1:
               //Savings
               this.type = type;
               break;
            //Checking
            case 2:
               this.type = type;
               break;
            case 3:
               this.type = type;
               break;
            default:
               this.type = 1;
         }
      }
      catch(Exception e){
         System.out.println("error");
      }
   }

   //-----------------------------------------------------------------
   //  Get Functions
   //-----------------------------------------------------------------

   public String getAcctNumber()
   {
      return acctNumber;
   }
   public String getName()
   {
      return name;
   }
   public double getBalance ()
   {
      return balance;
   }
   public String getType(){
       if(this.type == 1){
            return "Savings Account";
       }
       if(this.type ==2){
          return "Checkings Account";
       }
      if(this.type ==3){
         return "Credit Account";
      }
       else{
          return "Account";
       }
   }

   //-----------------------------------------------------------------
   //  Abstract Functions
   //-----------------------------------------------------------------

   public abstract void addInterest();


   //-----------------------------------------------------------------
   //  Static Functions
   //-----------------------------------------------------------------

   static void generateAccountNr(){ counter++; }
   static String getCounter(){return Integer.toString(counter);}

   //-----------------------------------------------------------------
   //  Returns a one-line description of the account as a string.
   //-----------------------------------------------------------------
   public String toString ()
   {
      NumberFormat fmt = NumberFormat.getCurrencyInstance();

      return ("\nAccount Number: " + acctNumber + "\t| " + "Account Holder: " + name + "\t| " + "Account Balance: " + fmt.format(balance) + "\t| "+"Account Type: " + this.getType());
   }
}
