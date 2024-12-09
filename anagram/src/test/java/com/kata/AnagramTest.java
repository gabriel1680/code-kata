package com.kata;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnagramTest {

    @Mock
    private FileReader reader;

    @InjectMocks
    private Anagram anagram;

    @Test
    void givenAWordListFile_whenRun_thenShouldReturnAllListOfWordsWhichAreAnagram() {
        final var filepath = "words-list.txt";
        when(reader.read(filepath)).thenReturn(wordsList);
        final List<List<String>> anagrams = anagram.getWords(filepath);
        assertThat(anagrams).containsExactly(
                List.of("boaster", "boaters", "borates"),
                List.of("enlist", "inlets", "listen"),
                List.of("fresher", "refresh"),
                List.of("kinship", "pinkish"),
                List.of("knits", "stink"),
                List.of("rots", "sort"),
                List.of("sinks", "skins"));
    }

    final String wordsList = """
            boaster
            boaters
            borates
            enlist
            fresher
            inlets
            kinship
            knits
            listen
            pinkish
            refresh
            rots
            sinks
            skins
            sort
            stink""";
}