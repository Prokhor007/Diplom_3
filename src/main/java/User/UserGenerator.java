package User;

import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {
        /** Генерация данных для пользователя **/
        public static User generateUser() {
            String email = RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru";
            String password = RandomStringUtils.randomAlphanumeric(10);
            String firstName = RandomStringUtils.randomAlphabetic(10);
            return new User(email, password, firstName);
        }

        /** Генерация некорректного пароля **/
        public static String generateWrongPassword() {
        return RandomStringUtils.randomAlphabetic(5);
    }
}
