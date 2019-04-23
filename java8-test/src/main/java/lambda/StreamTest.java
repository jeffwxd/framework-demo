package lambda;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author :wxd
 * @Description:
 * @Date: Created in 11:02 2019/4/23
 */
public class StreamTest {
    public static void main(String[] args) {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    //return true;
                    return s.startsWith("A");
                })
                .forEach(s -> System.out.println("forEach: " + s));


        Stream.of("1", "5", "3", "4", "2").sorted((s1, s2) -> {
            System.out.printf("sort: %s; %s\n", s1, s2);
            return s1.compareTo(s2);
        }).collect(Collectors.toList());
    }

}
