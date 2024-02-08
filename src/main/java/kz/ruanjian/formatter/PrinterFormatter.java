package kz.ruanjian.formatter;

import kz.ruanjian.OnePerThread;
import kz.ruanjian.PrintEvent;

public class PrinterFormatter implements OnePerThread {

    private static final String SPACER = " ";

    private final int timeLength;
    private final int nameLength;

    public PrinterFormatter(int timeLength, int nameLength) {
        this.timeLength = timeLength;
        this.nameLength = nameLength;
    }

    public String format(PrintEvent event) {
        return toFixedLength(event.getDateTime().toString(), timeLength) +
                toFixedLength(event.getName(), nameLength) +
                event.getValue();
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
