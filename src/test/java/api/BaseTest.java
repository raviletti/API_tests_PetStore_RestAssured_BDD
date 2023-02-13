package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import model.User;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import static io.restassured.RestAssured.given;

/** Абстрактный класс, содержащий общие для всех тестов методы */
public abstract class BaseTest {

    /**
     * Служебный метод для подготовки запроса
     * Устанавливаем конфиг файл, URI, из конфига,
     * передаем URI, хедеры в RequestSpecification (объект для спецификации запросов)
     */
    @BeforeClass
    public void prepare() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("config.properties"));
        String baseUri = System.getProperty("base.uri");
        if (baseUri == null || baseUri.isEmpty()) {
            throw new RuntimeException("В файле \"config.properties\" отсутствует значение \"base.uri\"");
        }

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        RestAssured.filters(new ResponseLoggingFilter());
    }

    /**
     * Служебный метод для создания юзера, возвращает новый объект
     * @param username Юзернейм создаваемого объекта
     * @param id ИД создаваемого объекта
     */
    protected User buildNewUser(String username, int id) {
        // todo: создать объект с тестовыми данными
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setFirstName("Valeriy");
        user.setLastName("Leontiev");
        user.setEmail("restassured@gmail.com");
        user.setPassword("qwerty");
        user.setPhone("88005553535");
        return user;
    }

    /**
     * Служебный метод для передачи пост запроса на создание нового пользователя
     * @param user представление создаваемого пользователя в виде объекта
     */
    protected User createUser(User user) {
        // todo: отправить HTTP запрос для создания тикета
        return given()
                .body(user)
                .when()
                .post("user")
                .then()
                .statusCode(200)
                .extract().body()
                .as(User.class);
    }


}
