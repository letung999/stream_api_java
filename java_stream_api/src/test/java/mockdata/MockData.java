package mockdata;

import beans.Car;
import beans.Employee;
import beans.Person;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import org.assertj.core.internal.bytebuddy.description.ByteCodeElement;
import org.assertj.core.internal.bytebuddy.description.method.MethodDescription;
import org.testng.internal.protocols.Input;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockData {
    public static List<Person> getPerson() throws IOException {
        InputStream inputStream = Resources.getResource("persons.json").openStream();
        String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        Type typeList = new TypeToken<ArrayList<Person>>() {
        }.getType();
        return new Gson().fromJson(json, typeList);
    }


    public static List<Car> getCar() throws IOException {
        InputStream inputStream = Resources.getResource("cars.json").openStream();
        String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        Type typeList = new TypeToken<ArrayList<Car>>() {
        }.getType();
        return new Gson().fromJson(json, typeList);

    }

    public static List<Employee> getEmployee() throws IOException {
        InputStream inputStream = Resources.getResource("employees.json").openStream();
        String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        Type typeList = new TypeToken<ArrayList<Employee>>(){

        }.getType();
        return new Gson().fromJson(json, typeList);
    }
    // generic Method
    public static <T> List<T> getData(String fileName, Class<T[]> object) throws IOException {
        InputStream inputStream = Resources.getResource(fileName).openStream();
        String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        T[] result = new Gson().fromJson(json, object);
        return Arrays.asList(result);
    }
}
