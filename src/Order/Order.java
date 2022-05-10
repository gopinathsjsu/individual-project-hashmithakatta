package Order;

import java.io.*;
import java.util.*;

import model.Items;

import Factory.DataRepository;

/*
In this we are Processing the input order by chekcing all the validations against.
*/

public class Order {
    private DataRepository repository = DataRepository.getInstance();
    private double totalAmount = 0;
    private List<String> errorMsg = new ArrayList<>();
    private Map<String, Integer> order = new HashMap<String, Integer>();
    private int essentialCap = 3;
    private int luxuryCap = 4;
    private int miscCap = 6;

    public void processOrder(String filePath, String outputFilePath) {
        int recordNumber = 1;
        String cardNumber = "";
        String orderRecord = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));) {
            bufferedReader.readLine();
            while ((orderRecord = bufferedReader.readLine()) != null) {
                String[] orderItem = orderRecord.split(",");
                String itemName = orderItem[0].toLowerCase();
                int requestedQuantity = Integer.parseInt(orderItem[1]);
                order.put(itemName, order.getOrDefault(itemName, 0) + requestedQuantity);
                if (recordNumber == 1) {
                    cardNumber = orderItem[2];
                    recordNumber++;
                }
            }
            validateOrder();
            placeFinalOrder(cardNumber, outputFilePath);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void validateOrder() {
        for (Map.Entry<String, Integer> record : order.entrySet()) {
            String itemName = record.getKey();
            int requestedQuantity = record.getValue();
            Items item = repository.getItem(itemName);
            if (item == null) {
                errorMsg.add("Requested Item::" + itemName + "is not Available");
                break;
            }
            if (!checkStock(requestedQuantity, item)) {
                break;
            }
            if (!checkCapLimit(requestedQuantity, item)) {
                break;
            }
        }
    }

    private boolean checkCapLimit(int requestedQuantity, Items item) {
        String category = item.getCategory();
        boolean res = true;
        switch (category) {
            case "Essentials":
                if (requestedQuantity > essentialCap) {
                    res = false;
                } else {
                    essentialCap = essentialCap - requestedQuantity;
                }
                break;
            case "Luxury":
                if (requestedQuantity > luxuryCap) {
                    res = false;
                } else {
                    luxuryCap = luxuryCap - requestedQuantity;
                }
                break;
            case "Misc":
                if (requestedQuantity > miscCap) {
                    res = false;
                } else {
                    miscCap = miscCap - requestedQuantity;
                }
                break;
        }
        if (!res) {
            String error = "ItemName : " + item.getItemName() + "  category: " + category
                    + " is more than the Max Cap";
            errorMsg.add(error);
        }
        return res;
    }

    private boolean checkStock(int requestedQuantity, Items item) {
        if (item.getQuantity() < requestedQuantity) {
            String error = "ItemName :" + item.getItemName()
                    + "=== stock not available";
            errorMsg.add(error);
            return false;
        }
        return true;
    }

    private void placeFinalOrder(String cardNumber,
            String outputFilePath) {
        String outPutFile = errorMsg.size() == 0 ? "output.csv" : "error.txt";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFilePath + outPutFile))) {
            if (errorMsg.size() == 0) {
                if (!repository.getCards().contains(cardNumber)) {
                    repository.getCards().add(cardNumber);
                    String addCard = "CardNumber not present in database Added Card to database CardNumber:"
                            + cardNumber;
                    System.out.println(addCard);
                    System.out.println("Available Cards in DataBase");
                    for (String cardNo : repository.getCards()) {
                        System.out.println(cardNo);
                    }
                }
                bufferedWriter.write("itemName,requestedQuantity,total\n");
                for (Map.Entry<String, Integer> record : order.entrySet()) {
                    String itemName = record.getKey();
                    int requestedQuantity = record.getValue();
                    Items item = repository.getItem(itemName);
                    totalAmount = totalAmount + item.getPrice() * requestedQuantity;
                    item.setQuantity(item.getQuantity() - requestedQuantity);
                    bufferedWriter.write(
                            itemName + "," + requestedQuantity + "," + item.getPrice() * requestedQuantity + "\n");
                }
                bufferedWriter.write("Amount Paid\n");
                bufferedWriter.write("" + totalAmount);
                System.out.println("Amount Paid");
                System.out.println(totalAmount);
            } else {

                bufferedWriter.write("Please correct the following Item\n");
                for (String errorMessage : errorMsg) {
                    bufferedWriter.write(errorMessage + "\n");
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}
