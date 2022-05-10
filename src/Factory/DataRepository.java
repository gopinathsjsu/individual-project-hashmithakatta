package Factory;

import java.util.*;

import model.Items;

public class DataRepository {
    private static DataRepository repository;
    private  HashSet<String> cardsStorage = new HashSet<String>();
    private  HashMap<String, Items> itemStorage = new HashMap<String, Items>();
    private DataRepository() {
    }
    public static DataRepository getInstance() {
        if (repository == null) {
            repository = new DataRepository();
        }
        return repository;
    }

    public HashSet<String> getCards() {
        return this.cardsStorage;
    }

    public Items getItem(String itemName) {
        return this.itemStorage.get(itemName);
    }

    public HashMap<String, Items> getItemStorage() {
        return this.itemStorage;
    }
}

