public class Apartment {
    private String street;
    private int houseNumber;
    private String city;
    private double basePrice;

    public Apartment(String street, int houseNumber, String city, double basePrice) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.basePrice = basePrice;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public String toString() {
        return street + ", " + houseNumber + ", " + city + ", Base Price: $" + basePrice;
    }

    public double cost() {
        return basePrice;
    }


    protected int getFloor() {
        return 1;
    }
}
