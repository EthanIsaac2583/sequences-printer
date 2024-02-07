package kz.ruanjian.random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SafeRandomTest {

    SafeRandom random;

    @BeforeEach
    void setUp() {
        random = new SafeRandom();
    }

    @Test
    void get_shouldThrowIllegalArgumentException_whenMaxSmallerThanMin() {
        assertThrows(IllegalArgumentException.class, () -> random.get(-2, -10));
    }

    @Test
    void get_shouldThrowIllegalArgumentException_whenMinMaxEquals() {
        assertThrows(IllegalArgumentException.class, () -> random.get(0, 0));
    }

    @Test
    void get_shouldReturnMin_whenMaxIsGreaterTo1() {
        int expected = 13;
        int actual = random.get(13, 14);

        assertEquals(expected, actual);
    }

    @Test
    void get_shouldReturnValidNumber_whenValidNegativeMinMaxPassed() {
        int min = -10;
        int max = -2;

        int actual = random.get(min, max);

        assertTrue(exclusivelyBetween(min, max, actual));
    }

    @Test
    void get_shouldReturnValidNumber_whenValidPositiveMinMaxPassed() {
        int min = 0;
        int max = 10;

        int actual = random.get(min, max);

        assertTrue(exclusivelyBetween(min, max, actual));
    }

    private boolean exclusivelyBetween(int min, int max, int actual) {
        return (actual >= min) && (actual < max);
    }
}