package examples;

import beans.Person;
import mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GettingStarted {
    @Test
    void declarativeApproach() throws IOException {
        List<Person> personList = MockData.getPerson();
        List<Person> result = personList.stream()
                .filter(p-> p.getAge() <=18)
                .limit(30)
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .collect(Collectors.toList());
        result.forEach(System.out::println);
    }
}
