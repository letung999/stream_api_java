package examples;

import beans.Car;
import mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingData {

    /**
     * simple grouping
     */
    @Test
    void simpleGrouping() throws IOException {
        List<Car> carList = MockData.getCar();
        Map<String, List<Car>> groupByManufacturer = carList.stream()
                .collect(Collectors.groupingBy(Car::getMake));

        groupByManufacturer.forEach((manufacturer, cars) -> {
            System.out.println("name Manufacturer: " + manufacturer);
            cars.forEach(System.out::println);
            System.out.println("-------------------------------------");
        });
    }

    @Test
    void groupCarByColor() throws IOException {
        List<Car> carList = MockData.getCar();
        Map<String, List<Car>> groupCarByColor = carList.stream()
                .collect(Collectors.groupingBy(Car::getColor));
        groupCarByColor.forEach((color, cars) -> {
            System.out.println("Color: " + color);
            cars.forEach(System.out::println);
            System.out.println("---------------------------------------");
        });
    }

    @Test
    void groupCarByYear() throws IOException {
        List<Car> carList = MockData.getCar();
        Map<Integer, List<Car>> groupCarByYear = carList.stream()
                .collect(Collectors.groupingBy(Car::getYear));
        groupCarByYear.forEach((year, cars) -> {
            System.out.println("Year: " + year);
            cars.forEach(System.out::println);
            System.out.println("-----------------------------------------");
        });

    }

    /**
     * simple grouping combine with counting
     */

    @Test
    void countNumOfCarColor() throws IOException {
        List<Car> carList = MockData.getCar();
        Map<String, Long> countingWithColor = carList.stream()
                .collect(Collectors.groupingBy(Car::getColor, Collectors.counting()));
        countingWithColor.forEach((color, numOfEachColor) -> {
            System.out.println("Color: " + color);
            System.out.println("NumOfEachColor: " + numOfEachColor);
            System.out.println("-------------------------------------------");

        });

    }

    @Test
    void numOfManufacturer() throws IOException {
        List<Car> carList = MockData.getCar();
        Map<String, Long> countManuOfCar = carList.stream()
                .collect(Collectors.groupingBy(Car::getMake, Collectors.counting()));
        countManuOfCar.forEach((manu, numOfManu) -> {
            System.out.println("Manufacturer: " + manu);
            System.out.println("NumOfManufacturer: " + numOfManu);
            System.out.println("--------------------------------------");
        });
    }

    @Test
    void numOfYear() throws IOException {
        List<Car> carList = MockData.getCar();
        Map<Integer, Long> countCarOfYear = carList.stream()
                .collect(Collectors.groupingBy(Car::getYear, Collectors.counting()));
        countCarOfYear.forEach((year, numOfYear) ->{
            System.out.println("Year: " + year);
            System.out.println("NumOfYear: " + numOfYear);
            System.out.println("----------------------------------------");
        });
    }

    /**
     * group and counting don't use stream api:\
     */

    @Test
    void countManufacturer() throws IOException {
        List<Car> carList = MockData.getCar();
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < carList.size(); ++i){
            int count = 0;
            for(int j = 0; j < carList.size(); ++j){
                if(carList.get(i).getMake().equals(carList.get(j).getMake())){
                    ++count;
                }
            }
            map.put(carList.get(i).getMake(), count);
        }

        map.forEach((manu, count) ->{
            System.out.println("Manufacturer: " + manu);
            System.out.println("NumOfCount: " + count);
            System.out.println("----------------------------------------");
        });
    }

}
