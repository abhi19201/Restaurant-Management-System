package Controllers;

public class menuSchema {
    private int _id;
    private String dish;
    private String description;
    private String price;

    public menuSchema(int _id, String dish, String description, String price) {
        this._id = _id;
        this.dish = dish;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return _id;
    }

    public String getDish() {
        return dish;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() { return price; }
}
