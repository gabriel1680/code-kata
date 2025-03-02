package com.kata;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CodeCrackerTest {

    private final CodeCracker codeCracker = CodeCracker.of("! ) \" ( £ * % & > < @ a b c d e f g h i j k l m n o");

    @Test
    void keyWithoutTheChar_shouldNotDecryptIt() {
        assertThatThrownBy(() -> codeCracker.decrypt("\\"));
    }

    @Test
    void shouldDecryptAChar() {
        assertThat(codeCracker.decrypt("!")).isEqualTo("a");
        assertThat(codeCracker.decrypt(")")).isEqualTo("b");
    }

    @Test
    void shouldDecryptAWord() {
        assertThat(codeCracker.decrypt("!)")).isEqualTo("ab");
    }

    @Test
    void shouldEncryptAChar() {
        assertThat(codeCracker.encrypt("a")).isEqualTo("!");
        assertThat(codeCracker.encrypt("b")).isEqualTo(")");
    }

    @Test
    void shouldEncryptAWord() {
        assertThat(codeCracker.encrypt("ab")).isEqualTo("!)");
    }
}