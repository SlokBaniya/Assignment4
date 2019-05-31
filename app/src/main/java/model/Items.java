package model;

public class Items {
    private String itemName, image, itemDescription;
    private String itemPrice;
    private int image1;

    public int getImage1() {
        return image1;
    }

    public void setImage1(int image1) {
        this.image1 = image1;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Items(String itemName, String image, String itemDescription, String itemPrice) {
        this.itemName = itemName;
        this.image = image;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
    }
}
