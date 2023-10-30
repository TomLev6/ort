import java.util.Objects;

public class Car implements Comparable<Car>
{
    protected String brand;
    protected Integer year;
    protected String lPlate;

    public Car(String brand, Integer year, String lPlate) {
        this.brand = brand;
        this.year = year;
        this.lPlate = lPlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getlPlate() {
        return lPlate;
    }

    public void setlPlate(String lPlate) {
        this.lPlate = lPlate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(brand, car.brand) && Objects.equals(year, car.year) && Objects.equals(lPlate, car.lPlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, year, lPlate);
    }
    public int compareTo(Car c){
        // return this.brand.compareTo(c.getBrand());
        return this.year - c.getYear();
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", year=" + year +
                ", lPlate='" + lPlate + '\'' +
                '}';
    }
}
