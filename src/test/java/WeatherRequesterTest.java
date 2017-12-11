import org.junit.Test;

import static org.junit.Assert.*;

public class WeatherRequesterTest {
    @Test
    public void addParamToURL() throws Exception {
        WeatherRequester weatherRequester = new WeatherRequester();

        String baseURL = "http://api.openweathermap.org/data/2.5/weather";
        baseURL += "?";
        String[] APPIDParam = {"APPID", "5496614f4ca95e6dcc0445c1e7f3916d"};
        baseURL = weatherRequester.addParamToURL(baseURL, APPIDParam);

        assertEquals(baseURL, "http://api.openweathermap.org/data/2.5/weather?APPID=5496614f4ca95e6dcc0445c1e7f3916d&");
    }

    @Test
    public void request() throws Exception {
        WeatherRequester weatherRequester = new WeatherRequester();
        assertTrue(weatherRequester.request());
    }
}