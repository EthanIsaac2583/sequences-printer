package kz.ruanjian.formatter;

import kz.ruanjian.OnePerThread;

import java.time.LocalDateTime;

public class PrinterFormatter implements OnePerThread {

    private static final String SPACER = " ";

    private final int timeLength;
    private final int prefixLength;

    public PrinterFormatter(int timeLength, int prefixLength) {
        this.timeLength = timeLength;
        this.prefixLength = prefixLength;
    }

    public String timedMessage(String prefix, Object value) {
        return toFixedLength(LocalDateTime.now().toString(), timeLength) + toFixedLength(prefix, prefixLength) + value;
    }

    private String toFixedLength(String value, int length) {
        if (value.length() >= length) {
            return value.substring(0, length);
        } else {
            StringBuilder sb = new StringBuilder(value);
            while (sb.length() < length) {
                sb.append(SPACER);
            }
            return sb.toString();
        }
    }
}
