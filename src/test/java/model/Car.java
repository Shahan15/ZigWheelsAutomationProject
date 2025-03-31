package model;

public class Car {
    private String price;
    private String name;

    public Car(String price,String name) {
        this.price = price;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
