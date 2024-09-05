
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherData {

    private static final String API_KEY = "enter your api key";  // Replace with your OpenWeatherMap API key
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=";

    public static String getWeather(String city) {
        try {
            String urlString = BASE_URL + city + "&appid=" + API_KEY + "&units=metric";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            // Manual parsing of JSON response
            String response = content.toString();
            String weatherDescription = response.split("\"description\":\"")[1].split("\"")[0];
            String temperature = response.split("\"temp\":")[1].split(",")[0];
            String windSpeed = response.split("\"speed\":")[1].split(",")[0];

            return String.format("Weather: %s, Temperature: %.2f°C, Wind Speed: %.2f m/s",
                    weatherDescription, Double.parseDouble(temperature), Double.parseDouble(windSpeed));
        } catch (Exception e) {
            return "Failed to fetch weather data: " + e.getMessage();
        }
    }
}
