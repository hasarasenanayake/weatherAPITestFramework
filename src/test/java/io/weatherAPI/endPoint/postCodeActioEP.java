package io.weatherAPI.endPoint;
import io.weatherAPI.models.Weather;
import io.weatherAPI.utils.Constants;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class postCodeActioEP {
    static public Response response;
    public Weather weather;
    public  List<Map<String, String>> responseDataListP;

    @Step
    public void requestWeatherPC(String postcode, String country, String include) throws Exception {

        String URL = (Constants.WEATHER_ENDPOINTS) + "postal_code=" + postcode + "&country=" + country +"&include=" + include + "&key=" + Constants.WEATHER_API_KEY + "";
        response = SerenityRest.get(URL);
        List<Map<String, String>> responseDataListP = response.jsonPath().getList("data");
        ArrayList<Object> weather = new ArrayList<>();
        weather.add(responseDataListP.get(0).get("weather"));

    }
    @Step
    public int getStatusCode() throws Exception {
        int status =response.statusCode();
        return response.statusCode();
    }

    @Step
    public String getContentType() throws Exception {
        return response.then().extract().contentType();
    }



    @Step
    public String getResultStatus() throws Exception {
        return weather.getStatus();
    }


    public List getWeatherdata() {
        responseDataListP = response.jsonPath().getList("data");
        return  responseDataListP;
    }
}
