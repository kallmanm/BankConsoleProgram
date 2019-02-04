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
      int an;
      double balance;
      double amount;
      int accountType;
      double fee = 0.00;
      Account tempHolder;

      //HasMap for keeping track of account number (as key) and Account classes (as Objects) that are connected to the key.
      //FileHandler method readFromFile check if there is a file to read from and if not creates a new HashMap.
      HashMap<String, Account> accountDB = FileHandler.readFromFile();

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
                     an=Validator.createUniqueNumber(accountDB);

                     tempHolder = new SavingsAccount(name, balance, accountType, an);
                     accountDB.put(tempHolder.getAcctNumber(),tempHolder);
                     System.out.println("SavingsAccount successfully created:");
                     System.out.println(accountDB.get(tempHolder.getAcctNumber()));
                     break;

                  case "2":

                     System.out.println("Enter Account Holder Name");
                     name = input.nextLine();

                     System.out.println("Enter Starting Balance");
                     balance = Double.parseDouble(input.nextLine());
                     accountType = 2;
                     an=Validator.createUniqueNumber(accountDB);

                     tempHolder = new CheckingAccount(name, balance, accountType, an);
                     accountDB.put(tempHolder.getAcctNumber(),tempHolder);
                     System.out.println("CheckingAccount successfully created:");
                     System.out.println(accountDB.get(tempHolder.getAcctNumber()));
                     break;

                  case "3":

                     System.out.println("Enter Account Holder Name");
                     name = input.nextLine();
                     balance = 0;
                     accountType = 3;
                     an=Validator.createUniqueNumber(accountDB);

                     tempHolder = new CreditAccount(name, balance, accountType, an);
                     accountDB.put(tempHolder.getAcctNumber(),tempHolder);
                     System.out.println("CreditAccount successfully created:");
                     System.out.println(accountDB.get(tempHolder.getAcctNumber()));

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

               //WriteToFile saves the current state of the accounts so when the program is started next time
               //it will load the last saved data.
               FileHandler.writeToFile(accountDB);
               break;

//--------------------------DEFAULT-------------------------------------------------------------
            default:
               System.out.println("wrong input, try again");
         }
      }
   }
}

