import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.MediaType;
import org.json.JSONObject;
public class Traffic {
    public static String getTravelTime() throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.mapbox.com/directions-matrix/v1/mapbox/driving-traffic/" +
                        Keys.homeLong + "," + Keys.homeLat + ";" +
                        Keys.workLong + "," + Keys.workLat +
                        "?sources=0&annotations=duration&access_token=" + Keys.mapBoxKey)
                .build();
        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        JSONObject json = new JSONObject(jsonData);
        double seconds = json.getJSONArray("durations").getJSONArray(0).getDouble(1);

        String travelTime = "Travel to work will take about ";
        int hours = (int) (seconds / 3600);
        int minutes = (int) ((seconds % 3600) / 60);
        if (hours < 1){
            travelTime = travelTime + minutes + " minutes.";
        }
        else if (minutes < 1){
            travelTime = travelTime + hours + " hours.";
        }
        else{
            travelTime = travelTime + hours + " hours and " +
                    minutes + " minutes.";
        }
        //System.out.println(seconds);
        //System.out.println(travelTime);
        return travelTime;
    }
}
