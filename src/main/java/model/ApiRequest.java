package model;

import Utils.CustomContext;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiRequest {
    private static ApiRequest apiRequest;
    private static Map<String, Object> bodies = new HashMap<>();
    RequestSpecification requestSpecification;
    Response response;
    String path;
    String method;
    Map<String, String> queryParams = new HashMap<>();
    Object body;
    CustomContext context = CustomContext.getContext();


    public void createPostRequest(){
        response = requestSpecification.post();
    }

    public void createGetRequest(){
        response = requestSpecification.get();
    }

    public void createDeleteRequest(){
        response = requestSpecification.delete();
    }

    public void createPutRequest(){
        response = requestSpecification.put();
    }

    public void setHeader(String key, String value) {
        requestSpecification.header(new Header(key, value));
    }


    public void setPath(String path) {
        path = path.contains("{") ? path.substring(0, path.indexOf("{"))+context.getData(path.substring(path.indexOf("{")+1, path.indexOf("}"))) : path;
       requestSpecification = given().basePath(path);
    }

    public void setMethod(String method) {
        this.method = method;
    }


    public void setQueryParams(String query, String value) {
        this.queryParams.put(query, value);
       requestSpecification.queryParams(this.queryParams);
 }

    public void setBody(String value) {
        body = value.contains("${") ? bodies.get(context.getData(value.substring(value.indexOf("{")+1, value.indexOf("}")))) : bodies.get(value);
        requestSpecification.body(body);
    }

    public static void addBody(String name, Object obj){
        bodies.put(name, obj);
    }


    public Response getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "ApiRequest{" +
                "path='" + path + '\'' +
                ", method='" + method + '\'' +
                ", queryParams='" + queryParams + '\'' +
                ", body=" + body +
                '}';
    }

    public void setFields(String field, String value) {
        switch (field){
            case "path" : setPath(value);
                break;
            case "method" : setMethod(value);
                break;
            case "body" : setBody(value);
                break;
        }
    }

    public void sendRequest() {
        switch (method){
            case "GET" : createGetRequest();
                break;
            case "POST" : createPostRequest();
                break;
            case "PUT" : createPutRequest();
                break;
            case "DELETE" : createDeleteRequest();
                break;
        }

    }
    public static ApiRequest getRequest(){
        if(apiRequest == null){
            apiRequest = new ApiRequest();
        }
        return apiRequest;
    }

}
