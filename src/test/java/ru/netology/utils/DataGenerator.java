package ru.netology.utils;

import com.github.javafaker.Faker;
import ru.netology.data.RegistrationUserInfo;
import lombok.experimental.UtilityClass;

public class DataGenerator {

    @UtilityClass
    public static class RegistrationInfoGenerator {
        private RegistrationUserInfo user;
        private Faker faker = new Faker();

        public static RegistrationUserInfo generateUser(String statusActiveOrBlocked) {

            String name = faker.name().username();
            String password = faker.internet().password();
            String status = statusActiveOrBlocked;
            return new RegistrationUserInfo(name, password, status);
        }
    }
}
