package io.weatherAPI.stepdefinition;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.weatherAPI.endPoint.postCodeEP;
import io.weatherAPI.endPoint.weatherBitActionsEP;
import io.weatherAPI.models.Result;
import io.weatherAPI.utils.Constants;
import jdk.nashorn.internal.parser.JSONParser;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.weatherAPI.utils.Constants.EMPTY_STR;
import static io.weatherAPI.utils.Constants.TEST_STATUS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class WeatherDataStep {

    @Steps
    weatherBitActionsEP weatherBitActionsEP;
    postCodeEP postCodeEP;

    String apiKey;
    String lat;
    String lang;
    private String sString;

    @Given("^The user have valid API key$")
    public void the_user_have_proper_API_key() throws Exception {
        apiKey = Constants.WEATHER_API_KEY;
    }


    @Then("^API should return status as (\\d+)$")
    public void api_should_return_status_as(int statusCode) throws Exception {
        assertThat("Verify Status code  ", weatherBitActionsEP.getStatusCode(), equalTo(statusCode));
    }

    @Then("^Response content type should be application json$")
    public void response_content_type_should_be_json() throws Exception {
        assertThat("Verify Content Type for Weather Api ", weatherBitActionsEP.getContentType(),
                equalTo("application/json; charset=utf-8"));
    }

    @Then("^Response should have status code$")
    public void response_should_have_status_code() throws Exception {
        assertThat("Verify Result for Weather Api ", weatherBitActionsEP.getStatusCode(),
                equalTo(200));
    }


    @When("^I submit GET request with the \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iSubmitGETRequestWithTheAnd(double lat, double lon) throws Throwable {
       try {
           String status = null;
           weatherBitActionsEP.requestWeatherWithGet(lat, lon);
           status = Serenity.sessionVariableCalled(TEST_STATUS);
           if (status.length() == 0) status = EMPTY_STR;
           Assert.assertEquals(EMPTY_STR, status);
       }catch (Exception e) {

           e.printStackTrace();
       }
    }


    @And("^Then verify response body is JSON with tuples$")
    public void thenVerifyResponseBodyIsJSONWithTuples(Map<String, String> tuples) throws Exception {
        //Response response = (Response) weatherBitActionsEP.getResultNode();

        List data = weatherBitActionsEP.getResultNode();

        verifyJSONBody(tuples, data);
    }

    public void verifyJSONBody(Map<String, String> tuples, List<Map<String, String>> data) {


            for (String key : tuples.keySet()) {
                String sourceValue = String.valueOf(tuples.get(key));
                HashMap<String, String> weatherMap = (HashMap) data.get(0);
                for (String weatherKey : weatherMap.keySet()) {

                    String testValue = String.valueOf(weatherMap.get(weatherKey));
                    //System.out.println("sourceValue=" + sourceValue +":testValue=" + testValue);

                    if (key.equals(weatherKey)) {
                        String strRegex = sourceValue.substring(6);
                        Pattern pattern = Pattern.compile(strRegex, Pattern.CASE_INSENSITIVE);
                        Matcher matcher = pattern.matcher(testValue);
                        boolean matchFound = matcher.find();
                        assertTrue(String.valueOf(matchFound), true);
                        System.out.println("Match found for " + matchFound + " " + weatherKey + " " + testValue);
                    }


                }


            }
        }



    @Then("^verify  response code is <responseCode>$")
    public void verifyResponseCodeIsResponseCode(String responseCode) throws Exception {
       assertEquals(responseCode,weatherBitActionsEP.getStatusCode());

    }


}
