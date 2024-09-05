
public class WeatherMonitor implements Runnable {

    private String city;

    public WeatherMonitor(String city) {
        this.city = city;
    }

    @Override
    public void run() {
        while (true) {
            String weatherInfo = WeatherData.getWeather(city);
            System.out.println("Current weather in " + city + ": " + weatherInfo);

            // Sleep for a while before fetching the weather data again
            try {
                Thread.sleep(60000); // Update every 60 seconds
            } catch (InterruptedException e) {
                System.out.println("Weather monitoring interrupted: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java WeatherMonitor <city>");
            return;
        }

        String city = args[0];
        WeatherMonitor monitor = new WeatherMonitor(city);
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();
    }
}
