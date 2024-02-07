package kz.ruanjian.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoopControlTest {

    @Test
    void canExecute_shouldReturnTrueEveryTime_whenNegativeMaxLoopsPassed() {
        LoopControl loopControl = new LoopControl(-1);

        List<Boolean> expected = Arrays.asList(true, true, true);

        List<Boolean> actual = new ArrayList<>();
        actual.add(loopControl.canExecute());
        actual.add(loopControl.canExecute());
        actual.add(loopControl.canExecute());

        assertEquals(expected, actual);
    }

    @Test
    void canExecute_shouldReturnFalseEveryTime_whenZeroMaxLoopsPassed() {
        LoopControl loopControl = new LoopControl(0);

        List<Boolean> expected = Arrays.asList(false, false, false);

        List<Boolean> actual = new ArrayList<>();
        actual.add(loopControl.canExecute());
        actual.add(loopControl.canExecute());
        actual.add(loopControl.canExecute());

        assertEquals(expected, actual);
    }

    @Test
    void canExecute_shouldReturnTrueOnce_whenOneMaxLoopsPassed() {
        LoopControl loopControl = new LoopControl(1);

        List<Boolean> expected = Arrays.asList(true, false, false);

        List<Boolean> actual = new ArrayList<>();
        actual.add(loopControl.canExecute());
        actual.add(loopControl.canExecute());
        actual.add(loopControl.canExecute());

        assertEquals(expected, actual);
    }

    @Test
    void canExecute_shouldReturnTrueNTimes_whenNMaxLoopsPassed() {
        LoopControl loopControl = new LoopControl(3);

        List<Boolean> expected = Arrays.asList(true, true, true, false, false, false);

        List<Boolean> actual = new ArrayList<>();
        actual.add(loopControl.canExecute());
        actual.add(loopControl.canExecute());
        actual.add(loopControl.canExecute());
        actual.add(loopControl.canExecute());
        actual.add(loopControl.canExecute());
        actual.add(loopControl.canExecute());

        assertEquals(expected, actual);
    }
}