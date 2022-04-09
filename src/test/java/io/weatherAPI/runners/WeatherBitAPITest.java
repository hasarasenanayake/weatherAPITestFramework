package io.weatherAPI.runners;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = {"src/test/resources/features/api"}, glue = {
        "io.weatherAPI.stepdefinition"})
public class WeatherBitAPITest {
}
