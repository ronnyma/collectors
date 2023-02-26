package io.transfinite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class DuplicateConsecutiveElements<T> implements Collector<T, Set<T>, List<T>> {
    private final Set<T> duplicates = new HashSet<>();
    @Override
    public Supplier<Set<T>> supplier() {
        return TreeSet::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        return (acc, elem) -> {
            if(checkState(elem)) {
                acc.add(elem);
            }
        };
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        return (acc1, acc2) -> {
            acc1.addAll(acc2);
            return acc1;
        };
    }

    @Override
    public Function<Set<T>, List<T>> finisher() {
        return ArrayList::new;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }

    public  boolean checkState(T n) {
        if (!duplicates.contains(n)) {
            duplicates.clear();
        }
        return !duplicates.add(n);
    }
}
