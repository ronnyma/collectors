package io.transfinite.collectors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Factory methods for custom collectors, similar to {@link java.util.stream.Collectors}.
 */
public final class CustomCollectors {

    private CustomCollectors() {
        // utility class
    }

    /**
     * Collector that collects all elements that occur more than once in the input stream.
     * <p>
     * Usage:
     * <pre>
     *     List<String> duplicates = stream.collect(CustomCollectors.duplicateElements());
     * </pre>
     */
    public static <T> Collector<T, ?, List<T>> duplicateElements() {
        return new DuplicateElementsCollector<>();
    }

    /**
     * Collector that collects elements that appear consecutively at least twice in the input stream.
     * <p>
     * Usage:
     * <pre>
     *     List<String> consecutiveDuplicates = stream.collect(CustomCollectors.duplicateConsecutiveElements());
     * </pre>
     */
    public static <T> Collector<T, ?, List<T>> duplicateConsecutiveElements() {
        return new DuplicateConsecutiveElementsCollector<>();
    }

    public static class DuplicateConsecutiveElementsCollector<T> implements Collector<T, Set<T>, List<T>> {

        private final Set<T> duplicates = new HashSet<>();
        private final Predicate<T> checkAndInsert = elem -> {
            if (!duplicates.contains(elem)) {
                duplicates.clear();
            }
            return !duplicates.add(elem);
        };

        @Override
        public Supplier<Set<T>> supplier() {
            return TreeSet::new;
        }

        @Override
        public BiConsumer<Set<T>, T> accumulator() {
            return (acc, elem) -> {
                if (checkAndInsert.test(elem)) {
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
            final HashSet<Characteristics> characteristics = new HashSet<>();
            characteristics.add(Characteristics.CONCURRENT);
            return characteristics;
        }
    }

    public static class DuplicateElementsCollector<T> implements Collector<T, Set<T>, List<T>> {

        private final Set<T> duplicates = new HashSet<>();
        private final Predicate<T> checkAndInsert = elem -> !duplicates.add(elem);

        @Override
        public Supplier<Set<T>> supplier() {
            return TreeSet::new;
        }

        @Override
        public BiConsumer<Set<T>, T> accumulator() {
            return (acc, elem) -> {
                if (checkAndInsert.test(elem)) {
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
    }
}
