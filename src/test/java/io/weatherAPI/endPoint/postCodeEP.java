package io.weatherAPI.endPoint;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.weatherAPI.utils.Constants;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import java.util.*;

public class postCodeEP {

    static public Response response;
    public  List<Map<String, String>> responseDataListP;

    @Step
    public static void requestWeatherPC(String postcode, String country, String include) throws Exception {

        String URL = (Constants.POSTCODE_ENDPOINTS) + "postal_code=" + postcode + "&country=" + country +"&include=" + include + "&key=" + Constants.WEATHER_API_KEY + "";

        response = SerenityRest.get(URL);

        List<Map<String, String>> responseDataListP = response.jsonPath().getList("data");

        ArrayList<Object> weather = new ArrayList<>();
        weather.add(responseDataListP.get(0).get("weather"));

    }

    @Step
    public static int getStatusCode() throws Exception {
        int status =response.statusCode();
        return response.statusCode();
    }

    @Step
    public String getContentType() throws Exception {
        return response.then().extract().contentType();
    }


    @Step
    public List getWeatherdata() throws Exception {
        System.out.println("responseDataList"+responseDataListP);
        responseDataListP = response.jsonPath().getList("data");
        System.out.println("responseDataList"+responseDataListP);

        return responseDataListP;

    }
        @Step
    public int getResultStatus() throws Exception {
        return response.statusCode();
    }


}
