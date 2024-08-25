package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class CoinChangerTest {

    private static int[] arrayOf(int ...n) {
        return Arrays.stream(n).toArray();
    }

    private CoinChanger sut;

    @BeforeEach
    void setUp() {
        sut = new CoinChanger();
    }

    @Test
    void change0() {
        assertArrayEquals(arrayOf(0, 0, 0, 0, 0), sut.exchange(0));
        assertArrayEquals(arrayOf(1, 0, 0, 0, 0), sut.exchange(1));
    }

    @Test
    void change5() {
        assertArrayEquals(arrayOf(0, 1, 0, 0, 0), sut.exchange(5));
        assertArrayEquals(arrayOf(1, 1, 0, 0, 0), sut.exchange(6));
        assertArrayEquals(arrayOf(2, 1, 0, 0, 0), sut.exchange(7));
    }

    @Test
    void change10() {
        assertArrayEquals(arrayOf(0, 0, 1, 0, 0), sut.exchange(10));
        assertArrayEquals(arrayOf(0, 1, 1, 0, 0), sut.exchange(15));
        assertArrayEquals(arrayOf(3, 1, 1, 0, 0), sut.exchange(18));
    }

    @Test
    void change25() {
        assertArrayEquals(arrayOf(0, 0, 0, 1, 0), sut.exchange(25));
        assertArrayEquals(arrayOf(0, 1, 0, 1, 0), sut.exchange(30));
        assertArrayEquals(arrayOf(3, 1, 1, 1, 0), sut.exchange(43));
    }

    @Test
    void change100() {
        assertArrayEquals(arrayOf(0, 0, 0, 0, 1), sut.exchange(100));
        assertArrayEquals(arrayOf(0, 0, 0, 1, 1), sut.exchange(125));
        assertArrayEquals(arrayOf(1, 1, 1, 1, 1), sut.exchange(100 + 25 + 10 + 5 + 1));
    }
}