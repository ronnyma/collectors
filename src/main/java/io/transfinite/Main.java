package io.transfinite;

import java.util.List;
import org.apache.commons.lang.StringUtils;

public class Main {

    public static void main(String[] args) {
        List<Character> characters1 = List.of('a', 'b', 'c', 'c', 'c', 'd', 'd');
        List<Character> characters2 = List.of('c', 'd', 'e', 'e', 'd');

        listConsecutiveDuplicates(characters1);
        listConsecutiveDuplicates(characters2);
        listDuplicates(characters1);
        listDuplicates(characters2);
    }

    private static <T> void listConsecutiveDuplicates(List<T> list) {
        List<T> o = list.stream()
            .collect(new DuplicateConsecutiveElements<>());

        System.out.println("Duplicate and consecutive element [" + StringUtils.join(list, ",") + "]: ");
        o.forEach(System.out::println);
    }

    private static <T> void listDuplicates(List<T> list) {
        List<T> o = list.stream()
            .collect(new DuplicateElements<>());

        System.out.println("Duplicate elements [" + StringUtils.join(list, ",") + "]: ");
        o.forEach(System.out::println);

    }
}
