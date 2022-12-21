package examples;

import beans.Car;
import beans.CarDTO;
import beans.Person;
import beans.PersonDTO;
import mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransformationMapAndReduce {
    @Test
    void firstTransformationPersonWithMap() throws IOException {
        List<Person> personList = MockData.getPerson();

        //first way:
        /**List<PersonDTO> listTransfer = personList.stream()
         .filter(person -> person.getGender().equalsIgnoreCase("male"))
         .map(person ->{
         PersonDTO personDTO = new PersonDTO(person.getId(), person.getFirstName(), person.getAge());
         return personDTO;
         })
         .limit(30)
         .collect(Collectors.toList());

         listTransfer.forEach(System.out::println);*/

        //second way:
        /**Function<Person, PersonDTO> map = person -> new PersonDTO(person.getId(), person.getFirstName(), person.getAge());
         List<PersonDTO> listTransfer = personList.stream()
         .filter(person -> person.getGender().equalsIgnoreCase("male"))
         .map(map)
         .limit(30)
         .collect(Collectors.toList());
         listTransfer.forEach(System.out::println);*/

        //third way:
        List<PersonDTO> personDTOList = personList.stream()
                .filter(person -> person.getAge() <= 10
                        && person.getGender().equalsIgnoreCase("male"))
                .map(PersonDTO::map)
                .limit(30)
                .collect(Collectors.toList());
        personDTOList.forEach(System.out::println);

    }

    @Test
    void findAverageAgePerson() throws IOException {
        List<Person> personList = MockData.getPerson();
        double averageAge = personList.stream()
                .mapToDouble(person-> person.getAge())
                .average()
                .getAsDouble();
        System.out.println(averageAge);
    }

    @Test
    void mapToDoubleAndTotalCarPrice() throws IOException {
        List<Car> carList = MockData.getCar();
        double totalPrice = carList.stream()
                .filter(car -> car.getYear() < 2016 && car.getColor().equalsIgnoreCase("yellow"))
                .map(CarDTO::map)
                .mapToDouble(CarDTO::getPrice)
                .reduce((a, b) -> {
                    return a + b;
                }).getAsDouble();

        System.out.println(totalPrice);
    }

    //if don't use stream
    @Test
    void findTotalCarPrice() throws IOException {
        double total = 0;
        List<Car> carList = MockData.getCar();
        List<CarDTO> carDTOList = new ArrayList<>();
        for (int i = 0; i < carList.size(); ++i) {
            if (carList.get(i).getColor().equalsIgnoreCase("yellow")
                    && carList.get(i).getYear() < 2016) {
                CarDTO carDTO = CarDTO.map(carList.get(i));
                carDTOList.add(carDTO);
            }
        }
        for (int i = 0; i < carDTOList.size(); ++i) {
            total += carDTOList.get(i).getPrice();
        }

        System.out.println(total);
    }

    @Test
    void testReduce() {
        int[] arr = {1, 2, 3, 4, 5};
        int sum = Arrays.stream(arr).reduce(Integer::sum).getAsInt();
        System.out.println(sum);

        int sub = Arrays.stream(arr).reduce((a, b) -> a - b).getAsInt();
        System.out.println(sub);
    }

}
