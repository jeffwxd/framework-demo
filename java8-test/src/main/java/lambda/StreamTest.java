package lambda;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author :wxd
 * @Description:
 * @Date: Created in 11:02 2019/4/23
 */
public class StreamTest {
    public static void main(String[] args) {
       /* Stream.of("d2", "a2", "b1", "b3", "c")
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
        }).collect(Collectors.toList());*/
        LocalDate now = LocalDate.now();
       // int month = now.getDayOfMonth();
       // now.plusDays(5);
        /*LocalDate localDate = now.plusMonths(1);
        System.out.println(localDate);
        System.out.println(LocalDate.now().plusMonths(1));*/

        List<String> list = Arrays.asList("a", "b", "c", "d", "a", "b", "f", "d","e");
        //List<String> list1 = list.stream().distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList());方式一
        /*List<String> list1 = new ArrayList<String>();
        list.stream().forEach(o->{
            if(!list1.contains(o)){
                list1.add(o);
            }
        });方式二*/
        Collection<String> set = new HashSet<>();
        list.stream().forEach(o->{
            set.add(o);
        });
       // set.stream().map(o->)
        System.out.println(new ArrayList<>(set));
        List<String> list1 = set.stream().collect(Collectors.toList());
        list1.removeIf(o->o.contains("a"));
        System.out.println(list1);

        String a = "↑";
        System.out.println(a);
    }


}
