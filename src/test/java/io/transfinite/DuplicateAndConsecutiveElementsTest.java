package io.transfinite;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import io.transfinite.collectors.DuplicateConsecutiveElements;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DuplicateAndConsecutiveElementsTest {

    Stream<Character> characters1;
    Stream<Character> characters2;

    @BeforeEach
    public void setUp() {
        characters1 = Stream.of('a', 'b', 'c', 'c', 'c', 'd');
        characters2 = Stream.of('c', 'd', 'e', 'e', 'd');
    }

    @Test
    void shouldListDuplicateElements() {
        assertThat(List.of('c')).hasSameElementsAs(characters1
            .collect(new DuplicateConsecutiveElements<>()));
    }

    @Test
    void shouldListDuplicateElement() {
        assertThat(List.of('e')).hasSameElementsAs(characters2
            .collect(new DuplicateConsecutiveElements<>()));
    }
}
