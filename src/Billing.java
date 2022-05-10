import java.util.*;

import Factory.DataReader;
import Factory.Reader;
import Order.Order;

public class Billing {

    private static final String dataSet = "/Users/hashmithakatta/Desktop/CMPE202/individual-project-hashmithakatta/src/resources/dataset.csv";
    private static final String cards = "/Users/hashmithakatta/Desktop/CMPE202/individual-project-hashmithakatta/src/resources/Cards.csv";
    private static final String output = "/Users/hashmithakatta/Desktop/CMPE202/individual-project-hashmithakatta/src/resources/";

    public static void main(String[] args) {
        Reader reader = new Reader();
        DataReader itemReader = reader.getInstance("item");
        DataReader cardReader = reader.getInstance("card");
        itemReader.readFile(dataSet);
        cardReader.readFile(cards);
        Scanner in = new Scanner(System.in);
        System.out.println("Please Enter Input File Path");
        String inputFile = in.nextLine();
        Order helper = new Order();
        helper.processOrder(inputFile, output);
    }

}