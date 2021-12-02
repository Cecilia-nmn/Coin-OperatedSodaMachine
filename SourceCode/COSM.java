import java.util.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Timer;
import java.util.TimerTask;
public class COSM
{
    
    private static int balance = 0;
    private static String purchasedPdt = "";
    private static int times = 0;
    private static int limit = 5; //50.000 are too much so I set limit for 5, easier for testing
    private static int rate = 0;
    private static List<Integer> luckyNum = new ArrayList<Integer>();
    private static int val = 0;

    public int getTimes() {
        return times;
    }
    public int getLimit() {
        return limit;
    }
    public int getBalance() {
        return balance;
    }
    public int getRate() {
        return rate;
    }
    public String getProduct() {
        return purchasedPdt;
    }
    public boolean getResult() {
        for (int i = 0; i < luckyNum.size(); i++) {
            if (val == luckyNum.get(i)) {
                return true;
            }
        } 
        return false;
    }
    //1.
    //step 1
    public static void checkNotes() 
    {
        Pattern acceptableNotes = Pattern.compile("([12]0{1,2}|[5]0).0{3}");
        //check if the input notes are valid
        System.out.println("Please input note of value 10.000, 20.000, 50.000, 100.000 or 200.000 VND");
        Scanner sc = new Scanner(System.in);
        //accept only one note every times
        String note = sc.next();
        while (!acceptableNotes.matcher(note).matches())
        {
            System.out.println("Input Notes are not valid try again with different input");
            System.out.println("Please input notes of value 10.000, 20.000, 50.000, 100.000 or 200.000 VND");
            sc = new Scanner(System.in);
            note = sc.next();
        }
        strToInt(note);
    }
    public static void strToInt(String str) {
        String[] tmp = str.split("\\.");
        balance += Integer.parseInt(tmp[0]) * 1000;
    }
    public static void receiveNotes() { // here is step 1 
        if(balance > 500000) {
            System.out.println("Sorry I'm full, can't receive more than 500.000");
            return;
        }
        else {
            boolean done = true;
            do {
                checkNotes();
                System.out.println("Do you want to insert more money? Enter '1' for yes else then we will move to next step!");
                Scanner sc = new Scanner(System.in);
                int choice = checkInput(sc);
                if (choice != 1) {
                    done = false;
                }
            }
            while(done);
        }
    }
    //end step 1
    //step 2 
    public static int displayProducts() {
        //Print to screen all the products available
        System.out.println("Below are our available products (and prices):");
        System.out.println("1. Coke (10.000),\n2. Pepsi (10.000),\n3. Soda (20.000)");
        System.out.println("Please enter the number in front of the product to choose");
        Scanner sc = new Scanner(System.in);
        int product = checkInput(sc);
        return product;
        
    }
    public static int selectProduct() //step 2
    {
        int product = displayProducts();
        //checked if user have enough money to buy
        while (product > 3 || product == 0)
        {
            System.out.println("Input are not valid please try again with different input");
            System.out.println("Please enter the number among 1, 2 , 3");
            product = displayProducts();
        }
        return product;
    }
    //end step 2
    //step 3, 4
    public static int purchase(int product) {//step 3 and 4
        
        //verify the purchase
        int productsPrice = 0;
        if (product == 3) {
            productsPrice = 20000;
        }
        else {
            productsPrice = 10000;
        }
        if(productsPrice > balance) {
            //give user 3 choices: insert more money (back to step 1), choose another product (back to this step), cancel and refund
            System.out.println("Balance not enough to purchase product. Don't worry we have some selection for you,");
            System.out.println("Would you like to:\n1. insert more money,\n2. choose another product with lower price,\n3. cancel and refund.");
            System.out.println("Please enter the number in front of the choice to choose");
            Scanner sc = new Scanner(System.in);
            int choice = checkInput(sc);
            if(choice == 1) {
                //choice 1: back to step 1 
                System.out.println("You've choosen to insert more money!");
                return 1;
            }
            else if (choice == 2) {
                //choice 2: back to step 2
                System.out.println("You've choosen to choose another product!");
                return 2;
            }
            else if (choice == 3) {
                //choice 3: step 3
                System.out.println("Request cancelling! Your balance now is " + balance + "\nWe will give you a refund now");
                System.out.println("See you next times!");
                balance = 0;
                return 3;
            }
            else {
                //input invalid
                System.out.println("Oops! your input are invalid please try again with different input among 1, 2 , 3");
                return purchase(product);
            }   
        }
        else {
            String productName = "";
            if (product == 1) {
                productName = "Coke";
            }
            else if (product == 2) {
                productName = "Pepsi";
            }
            else if (product == 3) {
                productName = "Soda";
            }
            System.out.println("You've chosen the product: " + productName + ". Your balance is " + balance + ".\nDo you confirm to purchase it?");
            System.out.println("Enter '1' for yes else then request will be cancel and give a refund!");
            //give user 2 choice to confirm or cancel and refund
            //choice 1: confirm -> release the product continue ask user for theirs will
            Scanner sc = new Scanner(System.in);
            int choice = checkInput(sc);
            if (choice == 1) {
                balance -= productsPrice;
                consecutivePurchases(productName);
                System.out.println("Thanks for using our services!");
                System.out.println("Would you like to continue? Enter '1' for yes else then request will be cancel and give a refund!");
                sc = new Scanner(System.in);
                choice = checkInput(sc);
                if (choice == 1) {
                    System.out.println("Your product have released! Your balance now is " + balance);
                    System.out.println("Please continue to choose the product you want and make sure it's not over the limit");
                    return 2;
                }
            }
            //choice 2: cancel and refund
            System.out.println("Request cancelling! Your balance now is " + balance + "\nWe will give you a refund now");
            System.out.println("See you next times!");
            balance = 0;
            return 4;
        }
    }
    //end step 3, 4
    public static int checkInput(Scanner sc) {
        String str = sc.next();
        if(Pattern.compile("\\b\\d\\b").matcher(str).matches()) {
            return Integer.parseInt(str);
        }
        else return 0;
    }

    public static void scenario() {
        //user choices lead to different order of step
        int step = 1;
        int product = 0;
        do {
            if (step == 1) {
                receiveNotes();
                product = selectProduct();
                step = purchase(product);
            }
            else if (step == 2) {
                product = selectProduct();
                step = purchase(product);
            }
        }
        while (step >= 1 && step <= 2);
    }

    //2.
    public static void consecutivePurchases(String product) {
        if (product.equals(purchasedPdt)) {
            times += 1;
        }
        else {
            purchasedPdt = product;
            times = 1;
        }
        if (times == 3) {
            chance();
        }
    }
    public static void chance() {
        
        List<Integer> num = new ArrayList<Integer>();
        if (rate != 10) {
            Random generator = new Random();
            val = generator.nextInt(10);
            for (int i = 0; i < 10; i++) {
                num.add(i);
            }
            for (int i = 0; i < rate; i++) {
                int ind = generator.nextInt(num.size());
                luckyNum.add(num.get(ind));
                num.remove(ind);
                System.out.print(luckyNum.get(i) + " ");
            }
            for (int i = 0; i < luckyNum.size(); i++) {
                if (val == luckyNum.get(i)) {
                    System.out.println("Lucky you! You just won a free " + purchasedPdt);
                    System.out.println("Product released, enjoy.");
                    limit -= 1;
                    break;
                }
            }
            purchasedPdt = "";
            times = 0;
        }
        else {
            System.out.println("Lucky you! You just won a free " + purchasedPdt);
            System.out.println("Product released, enjoy.");
            purchasedPdt = "";
            times = 0;
            limit -= 1;
        }
    }
    
    public static void rateUp() {
        if (limit > 0) {
            if (rate == 0) {
                rate = 1;
            }
            else {
                rate += 5;
                if (rate > 10) {
                    rate = 10;
                }
            }
        }
        else {
            rate = 1;
        }
        limit = 5;
    }
    
    public static void main(String[] args)
	{
        int dayPeriod = 86400000; //24 hours
        int period = 5 * 60000; //15 minute
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                rateUp();
            }
        };
        timer.schedule(task, 0, period);
        while(true) {
            scenario();

        }
        
	}
}