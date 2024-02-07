package kz.ruanjian.logger;

public class SafeConsoleLogger implements Logger {

    @Override
    public void log(String value) {
        synchronized (System.out) {
            System.out.println(value);
        }
    }
}
