package kz.ruanjian.formatter;

import kz.ruanjian.PrintEvent;
import kz.ruanjian.data.DataGenerator;
import kz.ruanjian.data.DataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PrinterFormatterTest {

    PrinterFormatter printerFormatter;

    DataGenerator dataGenerator;

    DataUtil dataUtil;

    @BeforeEach
    void setUp() {
        dataGenerator = new DataGenerator();
        dataUtil = new DataUtil();
    }

    @Test
    void format_should_when1() {
        printerFormatter = new PrinterFormatter(2, 2);
        PrintEvent event = PrintEvent.builder()
                .name(dataGenerator.randomWord(10))
                .value(dataGenerator.randomWord(1000))
                .build();

        assertThrows(NullPointerException.class, () -> printerFormatter.format(event));
    }

    @Test
    void format_should_when2() {
        printerFormatter = new PrinterFormatter(2, 2);
        PrintEvent event = PrintEvent.builder()
                .dateTime(LocalDateTime.now())
                .value(dataGenerator.randomWord(1000))
                .build();

        assertThrows(NullPointerException.class, () -> printerFormatter.format(event));
    }

    @Test
    void format_should_when3() {
        printerFormatter = new PrinterFormatter(4, 4);

        LocalDateTime dateTime = LocalDateTime.now();
        String name = dataGenerator.randomWord(10);
        String value = dataGenerator.randomWord(1000);

        PrintEvent event = PrintEvent.builder()
                .dateTime(dateTime)
                .name(name)
                .value(value)
                .build();

        String expected = dataUtil.substring(dateTime.toString(), 4) + dataUtil.substring(name, 4) + value;

        String actual = printerFormatter.format(event);

        assertEquals(expected, actual);
    }

    @Test
    void format_should_when4() {
        printerFormatter = new PrinterFormatter(100, 100);

        LocalDateTime dateTime = LocalDateTime.now();
        String name = dataGenerator.randomWord(20);
        String value = dataGenerator.randomWord(20);

        PrintEvent event = PrintEvent.builder()
                .dateTime(dateTime)
                .name(name)
                .value(value)
                .build();

        String expected = dataUtil.padToLength(dateTime.toString(), 100) + dataUtil.padToLength(name, 100) + value;

        String actual = printerFormatter.format(event);

        assertEquals(expected, actual);
    }
}