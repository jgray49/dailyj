import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
public class Weather {
    public static double currentTemp;
    public static double dailyHighTemp;
    public static double dailyLowTemp;
    public static String condition;
    public static void getWeather() throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://api.weatherapi.com/v1/forecast.json" +
                        "?key=" + Keys.weatherAPIKey +
                        "&q=Coppell")
                .build();
        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        JSONObject json = new JSONObject(jsonData);
        Weather.condition = json.getJSONObject("current").getJSONObject("condition").getString("text");
        Weather.currentTemp = json.getJSONObject("current").getDouble("temp_f");
        Weather.dailyHighTemp = json.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0).getJSONObject("day").getDouble("maxtemp_f");
        Weather.dailyLowTemp = json.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0).getJSONObject("day").getDouble("mintemp_f");

//            System.out.println("Today is " + Weather.condition + " with a temp of " + Weather.currentTemp);
//            System.out.println("high is " + Weather.dailyHighTemp);
//            System.out.println("low is " + Weather.dailyLowTemp);

    }
}
