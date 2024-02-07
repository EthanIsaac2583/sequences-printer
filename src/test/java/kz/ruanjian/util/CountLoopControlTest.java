package kz.ruanjian.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountLoopControlTest {

    @Test
    void canExecute_shouldReturnTrueEveryTime_whenNegativeMaxLoopsPassed() {
        CountLoopControl countLoopControl = new CountLoopControl(-1);

        List<Boolean> expected = Arrays.asList(true, true, true);

        List<Boolean> actual = new ArrayList<>();
        actual.add(countLoopControl.canExecute());
        actual.add(countLoopControl.canExecute());
        actual.add(countLoopControl.canExecute());

        assertEquals(expected, actual);
    }

    @Test
    void canExecute_shouldReturnFalseEveryTime_whenZeroMaxLoopsPassed() {
        CountLoopControl countLoopControl = new CountLoopControl(0);

        List<Boolean> expected = Arrays.asList(false, false, false);

        List<Boolean> actual = new ArrayList<>();
        actual.add(countLoopControl.canExecute());
        actual.add(countLoopControl.canExecute());
        actual.add(countLoopControl.canExecute());

        assertEquals(expected, actual);
    }

    @Test
    void canExecute_shouldReturnTrueOnce_whenOneMaxLoopsPassed() {
        CountLoopControl countLoopControl = new CountLoopControl(1);

        List<Boolean> expected = Arrays.asList(true, false, false);

        List<Boolean> actual = new ArrayList<>();
        actual.add(countLoopControl.canExecute());
        actual.add(countLoopControl.canExecute());
        actual.add(countLoopControl.canExecute());

        assertEquals(expected, actual);
    }

    @Test
    void canExecute_shouldReturnTrueNTimes_whenNMaxLoopsPassed() {
        CountLoopControl countLoopControl = new CountLoopControl(3);

        List<Boolean> expected = Arrays.asList(true, true, true, false, false, false);

        List<Boolean> actual = new ArrayList<>();
        actual.add(countLoopControl.canExecute());
        actual.add(countLoopControl.canExecute());
        actual.add(countLoopControl.canExecute());
        actual.add(countLoopControl.canExecute());
        actual.add(countLoopControl.canExecute());
        actual.add(countLoopControl.canExecute());

        assertEquals(expected, actual);
    }
}