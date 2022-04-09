package io.weatherAPI.stepdefinition;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.weatherAPI.endPoint.postCodeEP;
import io.weatherAPI.models.Weather;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class postCodeSteps {
    postCodeEP postCodeEP;
    public WeatherDataStep WeatherDataStep;

    @When("^I submit GET request with the \"([^\"]*)\" ,\"([^\"]*)\" and \"([^\"]*)\"$")
    public void iSubmitGETRequestWithTheAnd(String postcode, String country, String Include) throws Throwable {
        postCodeEP.requestWeatherPC(postcode, country, Include);
    }

    @Then("^Postcode API should return status as (\\d+)$")
    public void postcodeAPIShouldReturnStatusAs(int statusCode) throws Exception {
        assertThat("Verify Status code", postCodeEP.getStatusCode(), equalTo(statusCode));
    }


//    private void verifyJSONBody(Map<String, String> tuples, List<Map<String, String>> data) {
//
//        for (String key : tuples.keySet()) {
//            String sourceValue = String.valueOf(tuples.get(key));
//            HashMap<String, String> weatherMap = (HashMap) data.get(0);
//            for (String weatherKey : weatherMap.keySet()) {
//
//                String testValue = String.valueOf(weatherMap.get(weatherKey));
//                //System.out.println("sourceValue=" + sourceValue +":testValue=" + testValue);
//
//                if (key.equals(weatherKey)) {
//                    String strRegex = sourceValue.substring(6);
//                    Pattern pattern = Pattern.compile(strRegex, Pattern.CASE_INSENSITIVE);
//                    Matcher matcher = pattern.matcher(testValue);
//                    boolean matchFound = matcher.find();
//                    assertTrue(String.valueOf(matchFound), true);
//                    System.out.println("Match found for " + matchFound + " " + weatherKey + " " + testValue);
//                }
//
//
//            }
//
//
//        }
//    }

    @And("^Then verify postcode API response body is JSON with tuples$")
    public void thenVerifyPostcodeAPIResponseBodyIsJSONWithTuples(Map<String, String> tuples) throws Exception {

        List data = postCodeEP.getWeatherdata();
        System.out.println("data"+data);
        WeatherDataStep.verifyJSONBody(tuples, data);
    }
}
