package kz.ruanjian.logger;

public class ConsoleLogger implements Logger {

    @Override
    public void log(String value) {
        synchronized (System.out) {
            System.out.println(value);
        }
    }
}
