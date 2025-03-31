package model;

public class Bike {
    private String name;
    private String price;
    private String launchDate;

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
