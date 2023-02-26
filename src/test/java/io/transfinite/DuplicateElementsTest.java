package io.transfinite;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import io.transfinite.collectors.DuplicateElements;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DuplicateElementsTest {

    List<Character> characters1;
    List<Character> characters2;

    @BeforeEach
    public void setUp() {
        characters1 = List.of('a', 'b', 'c', 'c', 'c', 'd', 'd');
        characters2 = List.of('c', 'd', 'e', 'e', 'd');
    }

    @Test
    void shouldListDuplicateElements() {
        assertThat(List.of('c', 'd')).hasSameElementsAs(characters1.stream()
            .collect(new DuplicateElements<>()));
    }

    @Test
    void shouldListDuplicateElement() {
        assertThat(List.of('d', 'e')).hasSameElementsAs(characters2.stream()
            .collect(new DuplicateElements<>()));
    }
}
