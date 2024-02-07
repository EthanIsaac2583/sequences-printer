package kz.ruanjian.random;

import kz.ruanjian.threaded.BadArgumentsException;
import kz.ruanjian.threaded.SafeRandom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SafeRandomTest {

    @Test
    void get_shouldThrowBadArguments_whenMaxSmallerThanMin() {
        SafeRandom random = new SafeRandom(-2, -10);
        assertThrows(BadArgumentsException.class, random::get);
    }

    @Test
    void get_shouldThrowBadArguments_whenMinMaxEquals() {
        SafeRandom random = new SafeRandom(0, 0);
        assertThrows(BadArgumentsException.class, random::get);
    }

    @Test
    void get_shouldReturnMin_whenMaxIsGreaterTo1() {
        SafeRandom random = new SafeRandom(13, 14);
        int expected = 13;
        int actual = random.get();

        assertEquals(expected, actual);
    }

    @Test
    void get_shouldReturnValidNumber_whenValidNegativeMinMaxPassed() {
        int min = -10;
        int max = -2;
        SafeRandom random = new SafeRandom(min, max);

        int actual = random.get();

        assertTrue(exclusivelyBetween(min, max, actual));
    }

    @Test
    void get_shouldReturnValidNumber_whenValidPositiveMinMaxPassed() {
        int min = 0;
        int max = 10;
        SafeRandom random = new SafeRandom(min, max);

        int actual = random.get();

        assertTrue(exclusivelyBetween(min, max, actual));
    }

    private boolean exclusivelyBetween(int min, int max, int actual) {
        return (actual >= min) && (actual < max);
    }
}