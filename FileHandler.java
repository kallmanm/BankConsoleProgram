import java.io.*;
import java.util.HashMap;

public class FileHandler implements Serializable {


    //Writes HashMap containing data on accounts to a text file.
    public static void writeToFile (HashMap <String, Account> accountDB){

        try{
            FileOutputStream fo = new FileOutputStream(new File("accountsDB.txt"));
            ObjectOutputStream oo = new ObjectOutputStream(fo);

            oo.writeObject(accountDB);

            oo.close();
            fo.close();
        }
        catch(IOException e){

            System.out.println("Exception caught: " + e);
        }
    }

    //Reads data from file and places it in a HashMap for use by program,
    public static HashMap <String, Account> readFromFile(){

        HashMap<String,Account> accountDB = new HashMap();

        try {
            FileInputStream fi = new FileInputStream(new File("accountsDB.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            accountDB = (HashMap<String, Account>) oi.readObject();

            oi.close();
            fi.close();
        }
        catch (IOException e){

            System.out.println("Exception caught: " + e);
        }catch (ClassNotFoundException e){

            System.out.println("Exception caught: " + e);
        }

        return accountDB;
    }
}
