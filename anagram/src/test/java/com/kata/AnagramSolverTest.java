package com.kata;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnagramSolverTest {

    @Test
    void emptyAnagrams() {
        var solver = new AnagramSolver();
        assertThat(solver.solve("a", Collections.emptyList())).isEmpty();
    }

    @Test
    void emptyWord() {
        var solver = new AnagramSolver();
        assertThat(solver.solve("", List.of("a"))).isEmpty();
    }

    @Test
    void noMatchesForAnagrams() {
        var solver = new AnagramSolver();
        assertThat(solver.solve("ab", List.of("cd"))).isEmpty();
    }

    @Test
    void oneMatchForAnagram_sameWords() {
        var solver = new AnagramSolver();
        assertThat(solver.solve("ab", List.of("ab")))
                .containsExactly("ab");
    }

    @Test
    void oneMatchForAnagram_differentWords() {
        var solver = new AnagramSolver();
        assertThat(solver.solve("ab", List.of("ba")))
                .containsExactly("ba");
    }

    @Test
    void manyMatchesForAnagram() {
        var solver = new AnagramSolver();
        assertThat(solver.solve("abc", List.of("bac", "cab", "cad")))
                .containsExactly("bac", "cab");
    }
}