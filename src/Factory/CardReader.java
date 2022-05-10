package Factory;

import java.io.*;

public class CardReader implements DataReader {
    private DataRepository repository = DataRepository.getInstance();
    @Override
    public void readFile(String filePath) {
        String cardNumber = "";
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));) {
            bufferedReader.readLine();
            while ((cardNumber = bufferedReader.readLine()) != null)
            {
                repository.getCards().add(cardNumber);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
