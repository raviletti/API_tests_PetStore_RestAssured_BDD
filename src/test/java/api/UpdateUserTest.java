package api;

import io.restassured.response.ValidatableResponse;
import model.User;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class UpdateUserTest extends BaseTest {

    /**
     * Создаем нового пользователя
     * Далее меняем поле у объекта пользователя
     * Потом обновляем пользователя в базе, передавая в метод объект с измененным полем
     */
    @Test
    public void updateTicketTest() {
        User newUser = buildNewUser("Qwerty123211", 190);
        createUser(newUser);
        newUser.setPassword("asdfgh");
       updateUser(newUser).statusCode(200);
    }

    /**
     * Обновляем пользователя в базе, передавая в метод объект с измененным полем
     * @param user обновленный объект, которым перезаписываем данные существующего пользователя
     */
    private ValidatableResponse updateUser(User user) {
           return   given()
                .body(user)
                .pathParam("username", user.getUsername())
                .when()
                .put("user/{username}").then().statusCode(200);
    }
}
