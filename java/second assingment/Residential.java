public class Residential extends Apartment {
    private int floor;

    public Residential(String street, int houseNumber, String city, double basePrice, int floor) {
        super(street, houseNumber, city, basePrice);
        this.floor = floor;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    @Override
    public double cost() {
        return super.cost() + (floor * 0.10 * super.getBasePrice());
    }
}
