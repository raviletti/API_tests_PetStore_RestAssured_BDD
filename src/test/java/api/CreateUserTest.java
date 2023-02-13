package api;

import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateUserTest extends BaseTest {

    /**
     * Создаем нового пользователя
     * Потом проверяем что данный пользователь присутствует в базе
     */
    @Test
    public void createUserTest() {
        User newUser = buildNewUser("newUser", 138);
        createUser(newUser);
        User createdUser = getUser(newUser.getUsername());
        Assert.assertEquals(newUser, createdUser);
    }

    /**
     * Получаем пользователя из базы по имени
     * @param username - имя по котрому ищем пользователя (предварительно создавая переменную в pathparam)
     */
    protected User getUser(String username) {
        User user = given()
                .pathParam("username", username)
                .when()
                .get("user/{username}")
                .then()
                .statusCode(200)
                .extract().body()
                .as(User.class);
        return user;
    }
}
