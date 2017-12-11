import java.net.*;
import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class WeatherRequester {
    private String addParamToURL(String URL, String[] param) {
        return URL + param[0] + "=" + param[1] + "&";
    }

    public boolean request() throws Exception {
        String[] APPIDParam = {"APPID", "5496614f4ca95e6dcc0445c1e7f3916d"};
        String[] qParam = {"q", "Lviv"};

        String baseURL = "http://api.openweathermap.org/data/2.5/weather";
        baseURL += "?";
        baseURL = this.addParamToURL(baseURL, APPIDParam);
        baseURL = this.addParamToURL(baseURL, qParam);

        URL weatherURL = new URL(baseURL);
        BufferedReader in = new BufferedReader(new InputStreamReader(weatherURL.openStream()));
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(in);

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray weather = (JSONArray) jsonObject.get("weather");
            JSONObject weather0 = (JSONObject) weather.get(0);
            String description = (String) weather0.get("description");


            JSONObject mainData = (JSONObject) jsonObject.get("main");
            Object tempObj = mainData.get("temp");
            Double tempK = (Double) tempObj;
            Double tempC = tempK - 273.15;


            System.out.println("description: " + description);
            System.out.println("temperature in Kelvin: " + tempK.toString());
            System.out.println("temperature in Celsius: " + tempC.toString());

            in.close();
            return(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        WeatherRequester weatherRequester = new WeatherRequester();
        boolean result = weatherRequester.request();
        System.out.println(result);
    }
}
