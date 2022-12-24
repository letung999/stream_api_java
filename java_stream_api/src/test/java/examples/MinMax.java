package examples;

import beans.Person;
import mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MinMax {
    @Test
    void findMaxAgePerson() throws IOException {
        List<Person> personList = MockData.getPerson();
        List<Person> personHasMaxAgeList = new ArrayList<>();
        //max
        Integer maxAge = personList.stream()
                .max(Comparator.comparing(Person::getAge))
                .get().getAge();

        for (var person : personList) {
            if (person.getAge() == maxAge) {
                personHasMaxAgeList.add(person);
            }
        }
        personHasMaxAgeList.forEach(System.out::println);
    }

    /**
     * if you use min or max with List auto boxing you have to comparator.naturalOrder in min() or max() function
     * ex: min(Comparator.naturalOrder).get()
     * moreover you use min or max with List Object you have to Comparator.comparing(name_class::element_you_want_has_min_value)
     * ex:min(Comparator.comparing(Person::getAge))
     */

    @Test
    void minOfNumberInList() {
        List<Integer> numberList = List.of(1, 2, 3, 4, -9);
        int minNumber = numberList.stream().min(Comparator.naturalOrder()).get();
        System.out.println(minNumber);
    }

    /**
     * if you min of max with primitive data type you just need to min() or max().
     */
    @Test
    void maxOfNumberInArray() {
        int[] numberArray = {1, 2, 3, 4};
        int minValue = Arrays.stream(numberArray).max().getAsInt();
        System.out.println(minValue);
    }

    @Test
    void minAgeOfPerson() throws IOException {
        List<Person> personList = MockData.getPerson();
        Person person = personList.stream()
                .filter(p -> p.getGender().equalsIgnoreCase("male"))
                .min(Comparator.comparing(Person::getAge))
                .get();
        System.out.println(person);
    }
}
