package examples;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class JoinString {
    @Test
    void joinStringsNormalWay() {
        List<String> names = List.of(
                "nhung",
                "tung",
                "hoa",
                "vu"
        );
        StringBuilder joinString = new StringBuilder();
        for (var name : names) {
            joinString.append(name.substring(0, 1).toUpperCase())
                    .append(name.substring(1))
                    .append("|");
        }
        System.out.println(joinString.substring(0, joinString.length() - 1));

    }

    @Test
    void joinNameByStream() {
        List<String> names = List.of(
                "nguyen",
                "nhung",
                "hoa",
                "lan",
                "tran"
        );
//        String joinNameResult = names.stream()
//                .map(name ->(name.substring(0,1).toUpperCase() + name.substring(1)))
//                .collect(Collectors.joining("|"));
//        System.out.println(joinNameResult);

        String joinNameResult = names.stream()
                .map(name -> {
                    return name.substring(0, 1).toUpperCase() + name.substring(1);
                })
                .collect(Collectors.joining("|"));

        System.out.println(joinNameResult);
    }
}
