package api;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/** Создание и проверка тикета */
public class DeleteUserTest extends BaseTest {

    /**
     * Создаем нового пользователя
     * Далее удаляем его
     * Потом проверяем что пользователь отсутствует в базе
     */
    @Test
    public void createUserTest() {
        User newUser = buildNewUser("userToDelete", 166);
        createUser(newUser);
       deleteUser(newUser.getUsername());
       getUser(newUser.getUsername());
    }

    /**
     * Удаляем пользователя по имени
     * @param username имя пользователя
     */
    protected ValidatableResponse deleteUser(String username) {
        return given()
                .pathParam("username", username)
                .when()
                .delete("user/{username}")
                .then().statusCode(200);

    }

    /**
     * Ищем пользователя по имени (ожидаем что запрос вернет 404)
     * @param username имя пользователя
     */
    protected ValidatableResponse getUser(String username) {
        // todo: отправить HTTP запрос на получение тикета по его id
        return given()
                .pathParam("username", username)
                .when()
                .get("user/{username}")
                .then()
                .statusCode(404);

    }
}
