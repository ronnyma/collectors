package io.transfinite;

import java.util.List;
import org.apache.commons.lang.StringUtils;

public class Main {

    public static void main(String[] args) {
        List<Character> characters = List.of('a', 'b', 'c', 'c', 'c', 'd', 'd');
        List<Character> characters2 = List.of('c', 'd', 'e', 'e', 'd');

        listConsecutiveDuplicates(characters);
        listConsecutiveDuplicates(characters2);
    }

    private static <T> void listConsecutiveDuplicates(List<T> list) {
        List<T> o = list.stream()
            .collect(new ConsecutiveElements<>());

        System.out.println("Duplicate consecutive element [" + StringUtils.join(list, ",") + "]: ");
        o.forEach(System.out::println);
    }
}
