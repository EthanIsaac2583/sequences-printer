package kz.ruanjian.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class NonTestedFileLogger implements Logger {

    private String filePath;

    public NonTestedFileLogger(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void log(String value) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.println(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
