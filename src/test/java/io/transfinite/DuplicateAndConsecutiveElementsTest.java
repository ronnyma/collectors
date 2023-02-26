package io.transfinite;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import io.transfinite.collectors.DuplicateConsecutiveElements;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DuplicateAndConsecutiveElementsTest {

    List<Character> characters1;
    List<Character> characters2;

    @BeforeEach
    public void setUp() {
        characters1 = List.of('a', 'b', 'c', 'c', 'c', 'd');
        characters2 = List.of('c', 'd', 'e', 'e', 'd');
    }

    @Test
    void shouldListDuplicateElements() {
        assertThat(List.of('c')).hasSameElementsAs(characters1.stream()
            .collect(new DuplicateConsecutiveElements<>()));
    }

    @Test
    void shouldListDuplicateElement() {
        assertThat(List.of('e')).hasSameElementsAs(characters2.stream()
            .collect(new DuplicateConsecutiveElements<>()));
    }
}
