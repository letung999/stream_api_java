package examples;

import beans.Car;
import mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DistinctAndSet {
    @Test
    void distinct() {
        List<Integer> numberList = List.of(1, 2, 3, 1, 2, 3, 45, 56, 6, 3, 2, 1, 1, 1, 1, 1, 1);
        List<Integer> result = numberList.stream().distinct().sorted().collect(Collectors.toList());
        System.out.println(result);
    }

    @Test
    void distinctWithSet() {
        List<Integer> numberList = List.of(1, 1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 8, 8, 8, 8);
        Set<Integer> result = numberList.stream().collect(Collectors.toSet());
        System.out.println(result);
    }


    /**
     * you have to override equals method in Car class.
     */
    @Test
    void showDifferentCar() throws IOException {
        List<Car> carList = MockData.getCar();
        List<Car> resultList = carList.stream().distinct().collect(Collectors.toList());
        resultList.forEach(System.out::println);

    }
}
