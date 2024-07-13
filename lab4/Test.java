import java.io.*;

public class Test {
    public static void main(String[] args) {
        RailSystem railSystem = new RailSystem("services.txt");

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input;
            while (true) {
                System.out.println("Enter a start and destination city: (type 'quit' to exit)");
                input = br.readLine();
                if ("quit".equals(input)) {
                    break;
                }
                String[] cities = input.split(" ");
                if (cities.length == 2) {
                    if (railSystem.is_valid_city(cities[0]) && railSystem.is_valid_city(cities[1])) {
                        railSystem.output_cheapest_route(cities[0], cities[1], System.out);
                    } else {
                        System.out.println("Invalid city names");
                    }
                } else {
                    System.out.println("Invalid input format");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

