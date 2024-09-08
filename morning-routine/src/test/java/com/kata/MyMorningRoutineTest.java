package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MyMorningRoutineTest {

    @Mock
    private Clock clock;

    @InjectMocks
    private MyMorningRoutine sut;

    private void givenATimeOf(String timeString) {
        given(clock.getHour()).willReturn(getHour(timeString));
    }

    private static int getHour(String timeString) {
        var time = Arrays.stream(timeString.split(":")).mapToInt(Integer::parseInt).toArray();
        return time[0];
    }

    @ParameterizedTest
    @ValueSource(strings = {"06:00", "06:59"})
    void doExercise(String timeString) {
        givenATimeOf(timeString);
        assertEquals("Do Exercise", sut.whatShouldIDoNow());
    }

    @ParameterizedTest
    @ValueSource(strings = {"07:00", "07:59"})
    void readAndStudy(String timeString) {
        givenATimeOf(timeString);
        assertEquals("Read and study", sut.whatShouldIDoNow());
    }

    @ParameterizedTest
    @ValueSource(strings = {"08:00", "08:59"})
    void haveBreakfast(String timeString) {
        givenATimeOf(timeString);
        assertEquals("Have breakfast", sut.whatShouldIDoNow());
    }

    @ParameterizedTest
    @ValueSource(strings = {"05:59", "09:00"})
    void noActivity(String timeString) {
        givenATimeOf(timeString);
        assertEquals("No activity", sut.whatShouldIDoNow());
    }
}