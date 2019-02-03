import java.util.HashMap;
import java.util.List;

public class InterestHandler extends Thread{

    //Test Variables
    HashMap<String, Account> accountDB;

    //Constructor
    public InterestHandler(HashMap<String,Account> accountDB){

        this.accountDB = accountDB;

    }

    @Override
    public void run(){

        while(true){



            for(Account element : accountDB.values()){
                element.addInterest();
            }


            try{
                Thread.sleep(10000);
            }
            catch (Exception e){
                //System.out.println("Cause of exception: " + e.getMessage());
                System.out.println("Turning off interest calculations...\nGood Bye...");
                break;
            }
        }
    }
}
