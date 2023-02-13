package steps;

import Utils.CompareUtil;
import Utils.CustomContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.bg.И;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import model.ApiRequest;
import model.Body;
import model.Pet;
import model.User;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ApiSteps {
    //TODO: вынести сложные методы в отдельные классы
    ApiRequest apiRequest;
    CustomContext context;
    @Before
    public void prepare() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("config.properties"));
        String baseUri = System.getProperty("base.uri");
        if (baseUri == null || baseUri.isEmpty()) {
            throw new RuntimeException("В файле \"config.properties\" отсутствует значение \"base.uri\"");
        }
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
        RestAssured.filters(new ResponseLoggingFilter());

        apiRequest = ApiRequest.getRequest();
        context = CustomContext.getContext();
    }


    @И("^создать (юзера|питомца)$")
    public void createBody(String bodyType, DataTable dataTable) {
        Body body = Objects.equals(bodyType, "юзера") ? new User() : new Pet();
        String name = "";
        User user = new User();
        for(List<String> columns : dataTable.asLists()){
            context.addData(columns.get(0), columns.get(1));
            user.setFields(columns.get(0), columns.get(1));
            body.setFields(columns.get(0), columns.get(1));
            name = (Objects.equals(name, "") &&
                    columns.get(0).equalsIgnoreCase("name")) ?
                    columns.get(1) : name;
        }
        ApiRequest.addBody(name, body);
    }

    @И("создать запрос")
    public void createRequest(DataTable dataTable){
        for(int i=0; i<dataTable.asLists(String.class).get(0).size(); i++){
            String field = dataTable.asLists(String.class).get(0).get(i);
            String value = dataTable.asLists(String.class).get(1).get(i);
            apiRequest.setFields(field, value);
        }
    }

    @И("^добавить (header|параметры)$")
    public void addHeaders(String valueType, DataTable dataTable) {
        for (List<String> columns : dataTable.asLists()) {
            switch (valueType){
                case "header" : apiRequest.setHeader(columns.get(0), columns.get(1));
                break;
                case "параметры" : apiRequest.setQueryParams(columns.get(0), columns.get(1));
                break;
            }
        }
    }

    @И("отправить запрос")
    public void sendRequest() {
        apiRequest.sendRequest();
    }

    @И("статус код {int}")
    public void assertStatusCode(int statusCode) {
        Assert.assertEquals(statusCode, apiRequest.getResponse().getStatusCode());
    }


    @И("извлечь данные")
    public void extractData(DataTable dataTable) {
        for(List<String> columns : dataTable.asLists()){
            String lineFromResponse = apiRequest.getResponse().path(columns.get(1)).toString();
            context.addData(columns.get(0), lineFromResponse);
            System.out.println("extracted " + columns.get(0) + " " + lineFromResponse);
        }
    }

    @И("сравнить значения")
    public void compareValue(DataTable dataTable) {
        for(List<String> columns : dataTable.asLists()){
            //TODO: Упростить логику, или перенести в CompareUtil
            Object expected = columns.get(0).startsWith("${") ? context.getData(columns.get(0).substring(columns.get(0).indexOf("{")+1, columns.get(0).length()-1)) : columns.get(0);
            System.out.println("expected " + expected + " " +  columns.get(0));
            Object actual = columns.get(2).startsWith("${") ? context.getData(columns.get(2).substring(columns.get(2).indexOf("{")+1, columns.get(2).length()-1)) : columns.get(2);
            System.out.println("actual " + actual + " " +  columns.get(2));
            System.out.println("COMPARE: " + expected  + " " + columns.get(1)  + " " + actual + " ?");
            boolean res = new CompareUtil().compareData(expected, columns.get(1), actual);
            System.out.println(res);
            Assert.assertTrue(res);
        }
    }

}
