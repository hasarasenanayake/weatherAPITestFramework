package io.weatherAPI.endPoint;

import io.weatherAPI.models.Weather;
import io.weatherAPI.utils.Constants;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import net.minidev.json.JSONObject;


public class weatherBitActionsEP {

    static public Response response;
    public Weather weather;
    public  List<Map<String, String>> responseDataList;

    @Step
    public void requestWeatherWithGet(double lat,double lon) throws Exception {

        String URL = (Constants.WEATHER_ENDPOINTS) + "lat=" + lat + "&lon=" + lon + "&key=" + Constants.WEATHER_API_KEY + "";
        response = SerenityRest.get(URL);

        responseDataList = response.jsonPath().getList("data");
        String state_code = responseDataList.get(0).get("state_code");
        System.out.println(" state_code :: state_code ---> " + state_code);
        ArrayList<Object> weather = new ArrayList<>();
        weather.add(responseDataList.get(0).get("weather"));

    }

    @Step
    public int getStatusCode() throws Exception {
        int status =response.statusCode();
        System.out.println("status >>>>>" + status);
        return response.statusCode();
    }

    @Step
    public String getContentType() throws Exception {
        return response.then().extract().contentType();
    }


    @Step
    public List getResultNode() throws Exception {
        responseDataList = response.jsonPath().getList("data");


        return  responseDataList;

    }


//    @Step
//    public List<Object> getResultNode() throws Exception {
//        ArrayList<Object> weather = new ArrayList<>();
//
//        return response.jsonPath().getList("data");
//    }


    @Step
    public String getResultStatus() throws Exception {
        return weather.getStatus();
    }


}
