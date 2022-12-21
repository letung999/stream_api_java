package beans;

public class CarDTO {
    private String make;
    private double price;
    private int year;

    public CarDTO(String make, Double price, Integer year) {
        this.make = make;
        this.price = price;
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static CarDTO map(Car car){
        return new CarDTO(car.getMake(), car.getPrice(),car.getYear());
    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "make='" + make + '\'' +
                ", price=" + price +
                ", year=" + year +
                '}';
    }
}
