package kz.ruanjian.formatter;

import kz.ruanjian.PrintEvent;
import kz.ruanjian.data.DataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PrinterFormatterTest {

    PrinterFormatter printerFormatter;

    DataGenerator dataGenerator;

    @BeforeEach
    void setUp() {
        dataGenerator = new DataGenerator();
    }

    @Test
    void format_should_when1() {
        printerFormatter = new PrinterFormatter(26, 13);
        PrintEvent event = PrintEvent.builder()
                .name(dataGenerator.randomWord(10))
                .value(dataGenerator.randomWord(1000))
                .build();

        assertThrows(NullPointerException.class, () -> printerFormatter.format(event));
    }

    @Test
    void format_should_when2() {
        printerFormatter = new PrinterFormatter(26, 13);
        PrintEvent event = PrintEvent.builder()
                .dateTime(LocalDateTime.now())
                .value(dataGenerator.randomWord(1000))
                .build();

        assertThrows(NullPointerException.class, () -> printerFormatter.format(event));
    }

    @Test
    void format_should_when3() {
        printerFormatter = new PrinterFormatter(26, 13);
    }
}