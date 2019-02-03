import java.util.HashMap;
import java.util.Scanner;

public class Transactions
{

   public static void main (String[] args)
   {
      //Scanner for entering in data to the program
      Scanner input= new Scanner(System.in);

      //Variables
      String menuChoice = "0";
      String name;
      String accNumber;
      double balance;
      double amount;
      int accountType;
      double fee = 0.00;
      String keyCatcher;

      //HasMap for keeping track of account number (as key) and Account classes (as Objects) that are connected to the key.
      HashMap<String, Account> accountDB = new HashMap();

      //Populating List with examples
      accountDB.put(Account.getCounter(),new SavingsAccount("Ted Murphy", 102.56, 1));
      accountDB.put(Account.getCounter(),new CheckingAccount ("Edward Demsey",759.32, 2));
      accountDB.put(Account.getCounter(),new CreditAccount ("Test Credit Account", 0.00, 3));

      //The Thread that manages interest calculations.
      InterestHandler ih = new InterestHandler(accountDB);
      ih.start();

      //Main loop of options in program
      while(!menuChoice.equals("5")){


         System.out.println(" ------------------------");
         System.out.println("|1. Create Account       |");
         System.out.println("|2. Deposit to Account   |");
         System.out.println("|3. Withdraw from Account|");
         System.out.println("|4. View Accounts        |");
         System.out.println("|5. Exit Program         |");
         System.out.println(" ------------------------");
         System.out.println("What would you like to do?");

         menuChoice = input.nextLine();

         switch (menuChoice){
//--------------------------CASE 1: Create Account------------------------------------------
            case "1":

               System.out.println(" -------------------------");
               System.out.println("|1. Create SavingsAccount |");
               System.out.println("|2. Create ChechingAccount|");
               System.out.println("|3. Create CreditAccount  |");
               System.out.println("|4. Return to main menu   |");
               System.out.println(" -------------------------");

               menuChoice = input.nextLine();
               switch (menuChoice){

                  case "1":

                     System.out.println("Enter Account Holder Name");
                     name = input.nextLine();

                     System.out.println("Enter Starting Balance");
                     balance = Double.parseDouble(input.nextLine());
                     accountType = 1;
                     accountDB.put(keyCatcher=Account.getCounter(),new SavingsAccount(name, balance, accountType));
                     System.out.println("SavingsAccount successfully created:");
                     System.out.println(accountDB.get(keyCatcher));
                     break;

                  case "2":

                     System.out.println("Enter Account Holder Name");
                     name = input.nextLine();

                     System.out.println("Enter Starting Balance");
                     balance = Double.parseDouble(input.nextLine());
                     accountType = 2;
                     accountDB.put(keyCatcher=Account.getCounter(),new CheckingAccount(name, balance, accountType));
                     System.out.println("CheckingAccount successfully created:");
                     System.out.println(accountDB.get(keyCatcher));
                     break;

                  case "3":

                     System.out.println("Enter Account Holder Name");
                     name = input.nextLine();
                     balance = 0;
                     accountType = 3;
                     accountDB.put(keyCatcher=Account.getCounter(),new CreditAccount(name,balance,accountType));
                     System.out.println("CreditAccount successfully created:");
                     System.out.println(accountDB.get(keyCatcher));

                     break;
                  case "4":

                     System.out.println("Returning to Main Menu...");

                     break;

                  default:
                     System.out.println("Wrong input, Returning to Main Menu...");
               }
               break;

//--------------------------CASE 2: Deposit to Account------------------------------------------
            case "2":

               System.out.println("Deposit to Account");
               System.out.println("Enter Account Number:");

               try{
                  accNumber = input.nextLine();


                  for (Account element : accountDB.values()) {

                     if(element.getAcctNumber().equals(accNumber)){

                        System.out.println(element);
                        System.out.println("Enter amount to be deposited:");
                        amount = Double.parseDouble(input.nextLine());
                        double checkAccNr =amount;
                        if(checkAccNr<0){
                           System.out.println("Can't deposit negative amount!");
                           break;
                        }
                        element.deposit(amount);
                        System.out.println("The new balance is: "+ element.getBalance());

                     }
                  }

               }
               catch(Exception e){
                  System.out.println("Invalid input");
               }
               break;

//--------------------------CASE 3: Withdraw from Account---------------------------------------
            case "3":

               System.out.println("Withdraw from Account");
               System.out.println("Enter Account Number:");


               try{
                  accNumber = input.nextLine();

                  for (Account element : accountDB.values()) {

                     if(element.getAcctNumber().equals(accNumber)){

                        System.out.println(element);
                        System.out.println("Enter amount to be withdrawn:");
                        amount = Double.parseDouble(input.nextLine());
                        element.withdraw(amount, fee);

                        //Split into if-statement so it is possible to return custom answers.
                        if(element instanceof SavingsAccount){
                           System.out.println("The new balance is: "+ element.getBalance());
                        }
                        if(element instanceof CheckingAccount){
                           System.out.println("The new balance is: "+ element.getBalance());
                        }
                        if(element instanceof CreditAccount){
                           System.out.println("The new balance is: "+ element.getBalance());
                        }


                     }
                  }
               }
               catch(Exception e){
                  System.out.println("Invalid input");
               }

               break;

//--------------------------CASE 4: View Accounts-----------------------------------------------

            case "4":

               System.out.println(" -------------------------");
               System.out.println("|1. View all Accounts     |");
               System.out.println("|2. View Specific Account |");
               System.out.println("|3. Return to main menu   |");
               System.out.println(" -------------------------");

               menuChoice = input.nextLine();


               switch (menuChoice){
                  case "1":

                     for (Account element : accountDB.values()) {

                        //Split into if-statement so it is possible to return custom answers.
                        if(element instanceof SavingsAccount){
                           System.out.println(element);
                        }
                        if(element instanceof CheckingAccount){
                           System.out.println(element);
                        }

                        if(element instanceof CreditAccount){
                           System.out.println(element);
                        }

                     }
                     break;

                  case "2":

                     System.out.println("Enter Account Number");
                     try{
                        accNumber = input.nextLine();
                        for (Account element : accountDB.values()) {
                           if(element.getAcctNumber().equals(accNumber)){

                              //Split into if-statement so it is possible to return custom answers.
                              if(element instanceof SavingsAccount){
                                 System.out.println(element);
                                 System.out.println("Interest earned: "+((SavingsAccount) element).getTotalInterest());
                              }
                              if(element instanceof CheckingAccount){
                                 System.out.println(element);
                                  System.out.println("Interest earned: "+((CheckingAccount) element).getTotalInterest());
                              }
                              if(element instanceof CreditAccount){

                                 System.out.println(element);
                                 System.out.println("Interest accumulated: "+((CreditAccount) element).getTotalInterest());
                              }
                           }
                        }
                     }
                     catch(Exception e){
                        System.out.println("Invalid input");
                     }
                     break;

                  case "3":

                     System.out.println("Returning to Main Menu...");
                     break;

                  default:
                     System.out.println("Wrong input, Returning to Main Menu...");
               }
               break;

//--------------------------CASE 5: Exit Program------------------------------------------------
            case "5":

               System.out.println("Exiting Program...");
               //Stops the InterestHandler Thread...
               ih.interrupt();
               break;

//--------------------------DEFAULT-------------------------------------------------------------
            default:
               System.out.println("wrong input, try again");
         }
      }
   }
}

