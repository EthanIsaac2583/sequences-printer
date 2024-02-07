package kz.ruanjian.logger;

public class ConsoleLogger implements Logger {

    @Override
    public synchronized void log(String value) {
        System.out.println(value);
    }
}
