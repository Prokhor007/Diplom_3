package User;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClient extends Client {
        private static final String PATH="api/auth/";

        @Step("Создание пользователя")
        public static ValidatableResponse createUser(User user){
            return given()
                    .spec(spec())
                    .body(user)
                    .when()
                    .post(PATH + "register")
                    .then().log().all();
        }

        @Step("Логин пользователя")
        public static ValidatableResponse loginUser(User user){
            return given()
                    .spec(spec())
                    .body(user)
                    .when()
                    .post(PATH + "login")
                    .then().log().all();
        }

        @Step("Удаление пользователя")
        public static ValidatableResponse deleteUser(String accessToken){
            return given()
                    .spec(spec())
                    .header("Authorization", accessToken)
                    .when()
                    .delete(PATH + "user")
                    .then().log().all();
        }
}
