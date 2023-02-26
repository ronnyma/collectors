package org.example;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class Main {

    public static void main(String[] args) {
        List<Character> characters = List.of('a', 'b', 'c', 'c', 'c', 'd', 'd');
        List<Character> characters2 = List.of('c', 'd', 'e','e');

        listConsecutiveDuplicates(characters);
        listConsecutiveDuplicates(characters2);
    }

    private static <T> void listConsecutiveDuplicates(List<T> list) {
        List<T> o = list.parallelStream()
            .collect(new ConsecutiveElements<>());

        System.out.println("Duplicate consecutive element [" + StringUtils.join(list, ",") + "]: ");
        o.forEach(System.out::println);
    }
}
