package io.weatherAPI.stepdefinition;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.weatherAPI.endPoint.postCodeActioEP;
import io.weatherAPI.endPoint.weatherBitActionsEP;
import io.weatherAPI.utils.Constants;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static io.weatherAPI.utils.Constants.EMPTY_STR;
import static io.weatherAPI.utils.Constants.TEST_STATUS;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class postcodeAPISteps {
    @Steps

    public postCodeActioEP postCodeActioEP;


    String apiKey;

    @Given("^The user have valid API key for PostcodeAPI$")
    public void theUserHaveValidAPIKeyForPostcodeAPI() {
         apiKey = Constants.WEATHER_API_KEY;
    }

    @When("^I submit postcode GET request with the \"([^\"]*)\" ,\"([^\"]*)\" and \"([^\"]*)\"$")
    public void iSubmitPostcodeGETRequestWithTheAnd(String postcode, String country, String Include) throws Throwable {
        try {
            String status = null;
            postCodeActioEP.requestWeatherPC(postcode,country,Include);
            status = Serenity.sessionVariableCalled(TEST_STATUS);
            if (status.length() == 0) status = EMPTY_STR;
            Assert.assertEquals(EMPTY_STR, status);
        }catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Then("^Postcode API should return status as (\\d+)$")
    public void postcodeAPIShouldReturnStatusAs(int statusCode) throws Exception {
        assertThat("Verify Status code", postCodeActioEP.getStatusCode(), equalTo(statusCode));
    }

    @And("^Then verify postcode API response body is JSON with tuples$")
    public void thenVerifyPostcodeAPIResponseBodyIsJSONWithTuples(Map<String, String> tuples) {
        List data = postCodeActioEP.getWeatherdata();
        System.out.println("data"+data);
        WeatherDataStep.verifyJSONBody(tuples, data);
    }
}
