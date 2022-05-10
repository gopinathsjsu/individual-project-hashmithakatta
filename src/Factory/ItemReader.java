package Factory;


import java.io.*;

import model.Items;

public class ItemReader implements DataReader {
    private DataRepository repository = DataRepository.getInstance();
    private static final String DELIMITER = ",";

    @Override
    public void readFile(String filePath) {
        String currentLine = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            bufferedReader.readLine();
            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] itemData = currentLine.split(DELIMITER);
                String itemName = itemData[0].toLowerCase();
                Items item = new Items.Item()
                        .category(itemData[1]).itemName(itemName)
                        .quantity(Integer.parseInt(itemData[2]))
                        .price(Double.parseDouble(itemData[3])).process();
                repository.getItemStorage().put(itemName, item);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
