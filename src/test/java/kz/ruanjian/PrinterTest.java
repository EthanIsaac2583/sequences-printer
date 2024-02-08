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
        String prefix = dataGenerator.randomWord(10);
        String message = dataGenerator.randomWord(200);

        String expected = dataGenerator.randomWord(210);
        doReturn(expected).when(formatter).timedMessage(prefix, message);

        printer.print(prefix, message);

        verify(logger).log(expected);
    }
}