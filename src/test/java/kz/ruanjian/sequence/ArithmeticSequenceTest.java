package kz.ruanjian.sequence;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArithmeticSequenceTest {

    @Test
    void generate_shouldReturnArithmeticSequence_whenInvokedMultipleTimesOnTwos() {
        ArithmeticSequence sequence = new ArithmeticSequence(2, 2);

        List<Integer> expected = Arrays.asList(2, 4, 6, 8, 10);

        List<Integer> actual = new ArrayList<>();
        actual.add(sequence.generate());
        actual.add(sequence.generate());
        actual.add(sequence.generate());
        actual.add(sequence.generate());
        actual.add(sequence.generate());

        assertEquals(expected, actual);
    }

    @Test
    void generate_shouldReturnArithmeticSequence_whenInvokedMultipleTimesOnSevens() {
        ArithmeticSequence sequence = new ArithmeticSequence(7, 7);

        List<Integer> expected = Arrays.asList(7, 14, 21, 28, 35);

        List<Integer> actual = new ArrayList<>();
        actual.add(sequence.generate());
        actual.add(sequence.generate());
        actual.add(sequence.generate());
        actual.add(sequence.generate());
        actual.add(sequence.generate());

        assertEquals(expected, actual);
    }
}