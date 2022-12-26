package examples;

import beans.Car;
import beans.Person;
import mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Sorting {
    @Test
    void sortByFirstNameASC() throws IOException {
        List<Person> personList = MockData.getPerson();
        List<String> firstNameListHasSorted = personList.stream()
                .filter(person -> person.getGender().equalsIgnoreCase("male"))
                .limit(30)
                .map(Person::getFirstName)
                .sorted()
                .collect(Collectors.toList());

        firstNameListHasSorted.forEach(System.out::println);
    }

    @Test
    void topTenHasMostPrice() throws IOException {
        List<Car> carList = MockData.getCar();
        List<Car> topMostPriceList = carList.stream()
                .filter(car -> car.getColor().equalsIgnoreCase("blue"))
                .sorted(Comparator.comparing(Car::getPrice).reversed())
                .limit(10)
                .collect(Collectors.toList());
        topMostPriceList.forEach(System.out::println);
    }

    @Test
    void topTenMostExpensiveCarYellow() throws IOException {
        List<Car> carList = MockData.getCar();
        List<Car> topTenMostExYellowCar = carList.stream()
                .filter(car -> car.getColor().equalsIgnoreCase("yellow"))
                .sorted(Comparator.comparing(Car::getPrice).reversed())
                .limit(10)
                .collect(Collectors.toList());

        topTenMostExYellowCar.forEach(System.out::println);
    }

    @Test
    void sumTopMostExpensiveCarBlue() throws IOException {
        List<Car> carList = MockData.getCar();
        double totalPrice = carList.stream()
                .filter(car -> car.getColor().equalsIgnoreCase("blue"))
                .sorted(Comparator.comparing(Car::getPrice).reversed())
                .limit(10)
                .mapToDouble(Car::getPrice)
                .reduce((a, b) -> a + b).getAsDouble();
        System.out.println(totalPrice);
    }


}
