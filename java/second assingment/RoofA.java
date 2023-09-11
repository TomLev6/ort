public class RoofA extends Apartment {
    private boolean hasSwimmingPool;

    public RoofA(String street, int houseNumber, String city, double basePrice, boolean hasSwimmingPool) {
        super(street, houseNumber, city, basePrice);
        this.hasSwimmingPool = hasSwimmingPool;
    }

    public boolean hasSwimmingPool() {
        return hasSwimmingPool;
    }

    public void setSwimmingPool(boolean hasSwimmingPool) {
        this.hasSwimmingPool = hasSwimmingPool;
    }

    @Override
    public double cost() {
        double cost = super.cost() + (super.getFloor() * 0.10 * super.getBasePrice());
        if (hasSwimmingPool) {
            cost += 0.20 * super.getBasePrice();
        }
        return cost;
    }
}
