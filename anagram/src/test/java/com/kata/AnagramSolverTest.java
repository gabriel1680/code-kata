package com.kata;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnagramSolverTest {

    @Test
    void solveShouldReturnAllPossibleAnagrams() {
        final var solver = new AnagramSolver();
        assertThat(solver.solve("a", new String[]{})).isEmpty();
        assertThat(solver.solve("ab", new String[]{"ab"})).containsExactly("ab");
        assertThat(solver.solve("cd", new String[]{"cd"})).containsExactly("cd");
        assertThat(solver.solve("ab", new String[]{"ba"})).containsExactly("ba");
        assertThat(solver.solve("a", new String[]{"ab"})).isEmpty();
        assertThat(solver.solve("ab", new String[]{"ab", "ba"})).containsExactly("ab", "ba");
        assertThat(solver.solve("ab", new String[]{"cd", "ad"})).isEmpty();
        assertThat(solver.solve("xq", new String[]{"qx", "ad"})).containsExactly("qx");
    }
}