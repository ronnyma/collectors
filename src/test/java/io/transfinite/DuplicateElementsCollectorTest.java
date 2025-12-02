package io.transfinite;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import io.transfinite.collectors.CustomCollectors;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DuplicateElementsCollectorTest {

    Stream<Character> characters1;
    Stream<Character> characters2;

    @BeforeEach
    void setUp() {
        characters1 = Stream.of('a', 'b', 'c', 'c', 'c', 'd', 'd');
        characters2 = Stream.of('c', 'd', 'e', 'e', 'd');
    }

    @Test
    void shouldListDuplicateElements() {
        assertThat(characters1
            .collect(CustomCollectors.duplicateElements())).hasSameElementsAs(List.of('c', 'd'));
    }

    @Test
    void shouldListDuplicateElement() {
        assertThat(characters2
            .collect(CustomCollectors.duplicateElements())).hasSameElementsAs(List.of('d', 'e'));
    }
}
