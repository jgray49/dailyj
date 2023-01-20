import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import java.net.URI;
import java.math.BigDecimal;

public class Main {
    public static void sendNotification() throws Exception{
        Weather.getWeather();
        String body = "Daily Jon\n\n" +
                "Good Morning Jonathan! :)" +
                "\n\nCurrently, it's " + Weather.currentTemp + "\u2109 and " + Weather.condition.toLowerCase() + ", " +
                "but it'll range from " + Weather.dailyLowTemp + "\u2109 to " + Weather.dailyHighTemp + "\u2109.\n\n" +
                Traffic.getTravelTime();
//        Twilio.init(Keys.twSID, Keys.twToken);
//        Message message = Message.creator(
//                        new com.twilio.type.PhoneNumber(Keys.cellPhoneNumber),
//                        Keys.twMsgSvcSID,
//                        body)
//                .create();
        //System.out.println(message.getSid());
        System.out.println(body);
    }
    public static void main(String[] args) throws Exception{
        Date date = new Date();
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            public void run() {
                try{
                    sendNotification();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }, date, 1000*60*60*24);

    }

}