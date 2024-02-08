package kz.ruanjian;

import kz.ruanjian.data.DataGenerator;
import kz.ruanjian.formatter.PrinterFormatter;
import kz.ruanjian.logger.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PrinterTest {

    @Mock
    PrinterFormatter formatter;

    @Mock
    Logger logger;

    @InjectMocks
    Printer printer;

    DataGenerator dataGenerator;

    @BeforeEach
    void setUp() {
        dataGenerator = new DataGenerator();
    }

    @Test
    void print_shouldDoAppropriateActions_whenInvoked() {
        PrintEvent event = generatePrintEvent();
        String expected = dataGenerator.randomWord(150);
        doReturn(expected).when(formatter).format(event);

        printer.print(event);

        verify(logger).log(expected);
    }

    private PrintEvent generatePrintEvent() {
        return PrintEvent.builder()
                .dateTime(LocalDateTime.now())
                .name(dataGenerator.randomWord(20))
                .value(dataGenerator.randomWord(120))
                .build();
    }
}