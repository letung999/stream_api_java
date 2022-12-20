package examples;

import beans.Car;
import mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Filtering {
    @Test
    void findCarByCondition() throws IOException {
        Predicate<Car> colorCondition = p -> p.getColor().equalsIgnoreCase("yellow");
        Predicate<Car> priceCondition = p -> p.getPrice() > 20000;
        Predicate<Car> yearCondition = p -> p.getYear() < 2016;
        List<Car> carList = MockData.getCar();
        List<Car> resultList = carList.stream()
                .filter(colorCondition)
                .filter(priceCondition)
                .filter(yearCondition)
                .limit(30)
                .collect(Collectors.toList());
        resultList.forEach(System.out::println);
    }

    //dropWhile sẽ lấy tất cả các phần từ kể từ vị trí của một phần tử
    //có điều kiện ngược lại với điều kiện mình truyền vào
    @Test
    void testDropWhile() {
        List<Integer> numberList = List.of(3, 5, 2, 6, 8);
        List<Integer> result = numberList.stream().dropWhile(n -> n % 2 != 0).collect(Collectors.toList());
        result.forEach(System.out::println); //2,6,8
    }

    //takeWhile sẽ các phần tử từ vị trí có phần tử thõa mãn
    //đến vị trí có phần tử không thỏa mãn với điều kiện ta đưa vào. duyệt từ trái sang phải
    @Test
    void testTakeWhile() {
        List<Integer> numberList = List.of(3, 1, 4, 5, 6, 7);
        List<Integer> result = numberList.stream().takeWhile(n -> n % 2 != 0).collect(Collectors.toList());
        result.forEach(System.out::println); // 3, 1
    }

    @Test
    void getALlCarFromCarPositionHasNotBlue() throws IOException {
        List<Car> carList = MockData.getCar();
        List<Car> result = carList.stream().dropWhile(car -> car.getColor().equals("blue")).collect(Collectors.toList());
        result.forEach(System.out::println);
    }

    //tìm phần tử đầu tiên thỏa mãn điều kiện và kiểu giá trị trả về là một optional
    @Test
    void testFindFirst() {
        List<Integer> numberList = List.of(1, 2, 3, 4, 5);
        int result = numberList.stream()
                .filter(n -> n % 2 != 0)
                .findFirst()
                .orElse(-1);
        System.out.println(result);
    }

    //tìm phần tử bất kỳ thỏa mãn điều kiện, thông thường thì nó sẽ lấy phần tử đầu tiên thỏa mãn điều kiện
    //nhưng ko đảm bảo là ở lần chạy tiếp theo nó sẽ tra về giá trị đó, kiểu trả về của nó là một optional.
    @Test
    void testFindAny() {
        List<Integer> numberList = List.of(1, 2, 3, 4, 5);
        int result = numberList.stream()
                .filter(n -> n % 2 == 0)
                .findAny().orElse(-1);
        System.out.println(result);
    }
    // trả về giá trị true nếu một trong các phần tử thỏa mãn điều kiện
    // và ngược lại false nếu tất cả các điều kiện không đươc thõa mãn
    @Test
    void testAnyMatch(){
        int[]numberArray = {1, 2 ,3 ,4};
        boolean result = Arrays.stream(numberArray).anyMatch(n -> n % 2 == 0);
        System.out.println(result);
    }

    //trả về giá trị true nếu tất cả các phần tử thỏa mãn điều kiên và ngược lại
    @Test
    void testAllMatch(){
        int[] numberArray = {1, 2, 3, 4};
        boolean result = Arrays.stream(numberArray).allMatch(n -> n % 2 == 0);
        System.out.println(result);
    }

}
