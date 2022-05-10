package model;

public class Items {
    private String category;
    private String itemName;
    private double price;
    private int quantity;

    private Items(Item item){
        this.category = item.itemCategory;
        this.itemName = item.itemName;
        this.price = item.itemPrice;
        this.quantity =item.itemQuantity;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
    public int setQuantity(int quantity) {
        return quantity;
    }
    public String getItemName() {
        return itemName;
    }

    @Override
    public String toString() {
        return "category: "+this.category+", itemName: "+this.itemName+", quantity: "+this.quantity
                + ", price: "+this.price;
    }

    public static class Item{
        private String itemCategory;
        private String itemName;
        private double itemPrice;
        private int itemQuantity;
        public Item category(String category) {
            this.itemCategory = category;
            return this;
        }

        public Item itemName(String itemName) {
            this.itemName = itemName;
            return this;
        }

        public Item price(double price) {
            this.itemPrice = price;
            return this;
        }

        public Item quantity(int quantity) {
            this.itemQuantity = quantity;
            return this;
        }
        public Items process(){
            validateItem();
            return new Items(this);
        }
        private void validateItem(){
            if(this.itemCategory == null || this.itemName == null){
                throw new RuntimeException("Values can not be null");
            }
        }
    }
}
