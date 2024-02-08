package kz.ruanjian.data;

import com.github.javafaker.Faker;

public class DataGenerator {

    private final Faker faker = new Faker();

    public int randomInt(int min, int max) {
        return faker.random().nextInt(min, max);
    }

    public String randomWord(int length) {
        return faker.lorem()
                .fixedString(length)
                .replaceAll(" ", "_");
    }
}
