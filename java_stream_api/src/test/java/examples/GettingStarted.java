package examples;

import beans.Car;
import beans.Person;
import mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GettingStarted {
    @Test
    void declarativeApproach() throws IOException {
        List<Person> personList = MockData.getPerson();
        List<Person> result = new ArrayList<>();
        int limit = 10;
        int count = 0;
        for (int i = 0; i < personList.size(); ++i) {
            if (personList.get(i).getAge() <= 18) {
                result.add(personList.get(i));
                ++count;
                if (count == limit) {
                    break;
                }
            }
        }
        result.forEach(System.out::println);

    }

    @Test
    void declarativeApproachUsingStreams() throws IOException {
        List<Person> personList = MockData.getPerson();
        List<Person> result = personList.stream()
                .filter(p -> p.getAge() <= 18)
                .limit(10)
                .collect(Collectors.toList());
        result.forEach(System.out::println);
    }

    @Test
    void searchPersonBy() throws IOException {
        String regex = ".*" + "A" + ".*";
        List<Person> personList = MockData.getPerson();

        //predicate is condition to filter when you search in stream.
        Predicate<Person> nameCondition = p -> p.getFirstName().matches(regex);
        Predicate<Person> ageCondition = p -> p.getAge() <= 18;
        Predicate<Person> genderCondition = p -> p.getGender().equals("Male");

        List<Person> result = personList.stream()
                .filter(nameCondition)
                .filter(ageCondition)
                .filter(genderCondition)
                .limit(20)
                .collect(Collectors.toList());

        result.forEach(System.out::println);

    }

    @Test
    void searchByCar() throws IOException {
        List<Car> carList = MockData.getCar();
        List<Car> result = carList.stream()
                .filter(c -> c.getColor().equalsIgnoreCase("blue"))
                .filter(c -> c.getYear() < 2016)
                .filter(c -> c.getPrice() >= 20000)
                .limit(30)
                .collect(Collectors.toList());

        result.forEach(System.out::println);

    }

}
