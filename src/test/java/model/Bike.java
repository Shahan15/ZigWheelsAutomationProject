package model;

public class Bike {
    private final String name;
    private final String price;
    private final String launchDate;

    public Bike(String name, String price, String launchDate) {
        this.name = name;
        this.price = price;
        this.launchDate = launchDate;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getLaunchDate() {
        return launchDate;
    }
}
