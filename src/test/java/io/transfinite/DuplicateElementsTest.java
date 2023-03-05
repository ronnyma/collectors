package io.transfinite;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import io.transfinite.collectors.DuplicateElements;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DuplicateElementsTest {

    Stream<Character> characters1;
    Stream<Character> characters2;

    @BeforeEach
    public void setUp() {
        characters1 = Stream.of('a', 'b', 'c', 'c', 'c', 'd', 'd');
        characters2 = Stream.of('c', 'd', 'e', 'e', 'd');
    }

    @Test
    void shouldListDuplicateElements() {
        assertThat(characters1
            .collect(new DuplicateElements<>())).hasSameElementsAs(List.of('c', 'd'));
    }

    @Test
    void shouldListDuplicateElement() {
        assertThat(characters2
            .collect(new DuplicateElements<>())).hasSameElementsAs(List.of('d', 'e'));
    }
}
