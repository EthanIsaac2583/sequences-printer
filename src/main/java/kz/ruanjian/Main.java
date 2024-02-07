package kz.ruanjian;

import kz.ruanjian.logger.ConsoleLogger;
import kz.ruanjian.logger.Logger;

public class Main {

    public static void main(String[] args) {
        Logger logger = new ConsoleLogger();

        logger.log("Hi!");
    }
}
