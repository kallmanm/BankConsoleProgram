import java.util.HashMap;
import java.util.Random;

public class Validator {

    //-----------------------------------------------------------------
    //  Random number generator
    //-----------------------------------------------------------------
    public static int accountNumberGenerator(){
        //min gives the lowest possible value for number generator.
        int min = 100;
        //max gives the highest possible value for number generator.
        int max = 1000;

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    //-----------------------------------------------------------------
    //  Method that creates and validates if unique number
    //-----------------------------------------------------------------
    public static int createUniqueNumber(HashMap<String,Account> accountDB){

        //generates a random number with method and places in variable i.
        int i = Validator.accountNumberGenerator();
        String iString = Integer.toString(i);

        //checks if number already exists as key in HashMap
        if(accountDB.containsKey(iString)){

            //generates new number until unique number is found.
            while(accountDB.containsKey(iString)){
                i = Validator.accountNumberGenerator();
                iString =Integer.toString(i);
            }
        }
        return i;
    }
}
