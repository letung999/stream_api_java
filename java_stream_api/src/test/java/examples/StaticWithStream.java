package examples;

import beans.Car;
import mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class StaticWithStream {
    @Test
    void count() throws IOException {
        List<Car> carList = MockData.getCar();
        long count = carList.stream()
                .filter(car -> car.getMake().equalsIgnoreCase("Ford"))
                .filter(car -> car.getYear() > 2010)
                .count();
        System.out.println(count);
    }

    @Test
    void min() throws IOException {
        List<Car> carList = MockData.getCar();
        double minPrice = carList.stream()
                .mapToDouble(Car::getPrice)
                .min()
                .orElse(1);
        System.out.println(minPrice);
    }

    @Test
    void nameOneOfCarsHasMinPrice() throws IOException {
        List<Car> carList = MockData.getCar();
        Car car = carList.stream()
                .min(Comparator.comparing(Car::getPrice))
                .get();
        System.out.println(car.getModel());
    }

    @Test
    void maxPriceOfCars() throws IOException {
        List<Car> carList = MockData.getCar();
        double maxPrice = carList.stream()
                .mapToDouble(Car::getPrice)
                .max()
                .orElse(1);
        System.out.println(maxPrice);
    }

    @Test
    void averagePrice() throws IOException {
        List<Car> carList = MockData.getCar();
        double average = carList.stream()
                .mapToDouble(Car::getPrice)
                .average()
                .orElse(0);
        System.out.println(average);
    }

    @Test
    void statics() throws IOException {
        List<Car> carList = MockData.getCar();
        DoubleSummaryStatistics summaryStatistics = carList.stream()
                .mapToDouble(Car::getPrice)
                .summaryStatistics();
        System.out.println("count: " + summaryStatistics.getCount());
        System.out.println("min: " + summaryStatistics.getMin());
        System.out.println("sum: " + BigDecimal.valueOf(summaryStatistics.getSum()));
        System.out.println("average: " + summaryStatistics.getAverage());
        System.out.println("max: " + summaryStatistics.getMax());
    }

}
