
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nakamura
 */
public class Main {

    public static void main(String[] args) {
        String content;
        GetJSON j = new GetJSON();
        content = j.openURL("http://api.openweathermap.org/data/2.5/weather?q=Sorocaba,BR&units=metric&appid=0be2840a846c68f703feb8a9bccd5137");
        JsonReader jsonReader = Json.createReader(new StringReader(content));
        JsonObject obj = jsonReader.readObject();
        jsonReader.close();

        String name = obj.getString("name");
        String description = null;
        int temp_max = 0;
        int temp_min = 0;
        int humidity = 0;

        JsonArray weather = obj.getJsonArray("weather");
        for (int i = 0; i < weather.size(); i++) {
            JsonObject p = weather.getJsonObject(i);

            description = p.getString("description");
        }

        JsonObject main = obj.getJsonObject("main");
        
        temp_max = main.getInt("temp_max");
        temp_min = main.getInt("temp_min");
        humidity = main.getInt("humidity");
        
        System.out.println(name + "\n" + description + "\nMax: " + temp_max
                + "\nMin: " + temp_min + "\nHumidity: " + humidity + "%");
    }
}
