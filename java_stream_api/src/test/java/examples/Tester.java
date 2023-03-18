package examples;

import beans.Employee;
import mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Tester {

    //if primitive data type (long, int, double, float)
    boolean testEqualsTwoElement(int a, int b) {
        if (a == b) {
            return true;
        }
        return false;
    }

    // if object type: (class type, String, ...)
    //about class type:  if you extends a class and if you want to equal two object
    //(between object childClass and object supper class)
    // you have override 2 method equals and hashcode.

    boolean testEqualsTwoElement(String text1, String text2) {
        if (text1.equals(text2)) {
            return true;
        }
        return false;
    }


    boolean checkPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); ++i) {
            if (number % i == 0) {
                return false;
            }

        }
        return true;
    }

    @Test
    void testPrime() {
        System.out.println(checkPrime(7));
    }

    //with a creating Array
    List<String> reverse(List<String> lines) {
        List<String> newLine = new ArrayList<>();
        for (int i = lines.toArray().length - 1; i >= 0; i--) {
            newLine.add(lines.get(i));
        }
        return newLine;

    }
    //without creating a new array
//    List<String> reverse(List<String> lines){
//        return lines.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
//    }


    @Test
    void testReverse() {
        List<String> names = new ArrayList<>();
        names.add("A");
        names.add("B");
        names.add("C");
        System.out.println(reverse(names));
    }

    @Test
    void getListEmployee() throws IOException {
        List<Employee> employees = MockData.getEmployee();
        employees.forEach(System.out::println);
        List<Employee> employeeListIsTrue = employees.stream().filter(e -> e.isManager()).collect(Collectors.toList());
        employeeListIsTrue.forEach(System.out::println);
    }


    String checkRegex(String input) {
        String regex = "^[a-zA-Z0-9+_.\\-]+@[a-zA-Z0-9.\\-]+\\.[a-z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return (matcher.find()) ? "input match" : "not match";
    }

    @Test
    void checkRegex() {
        System.out.println(checkRegex("pâppapapapapapapapap"));
    }
}
