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
                .dateTime(LocalDateTime.now())
                .name(name)
                .value(value)
                .build();

        String expected = substring(dateTime.toString(), 4) + substring(name, 4) + value;

        String actual = printerFormatter.format(event);

        assertEquals(expected, actual);
    }

    private String substring(String target, int length) {
        return target.substring(0, length);
    }

    private String padToLength(String target, int length) {
        StringBuilder sb = new StringBuilder(target);
        while (sb.length() < length) {
            sb.append(" ");
        }
        return sb.toString();
    }
}