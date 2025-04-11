package model;

public class Car {
    private final String price;
    private final String name;

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
