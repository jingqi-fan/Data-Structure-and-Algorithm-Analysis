import java.io.*;
import java.util.*;

public class RailSystem {
    private Map<String, City> cities = new HashMap<>();
    private Map<String, List<Service>> outgoing_services = new HashMap<>();

    public void reset() {
        for (City city : cities.values()) {
            city.visited = false;
            city.total_fee = Integer.MAX_VALUE;
            city.total_distance = Integer.MAX_VALUE;
            city.from_city = "";
        }
    }

    public RailSystem(String filename) {
        load_services(filename);
    }

    public void load_services(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String from = parts[0];
                String to = parts[1];
                int fee = Integer.parseInt(parts[2]);
                int distance = Integer.parseInt(parts[3]);

                Service service = new Service(to, fee, distance);

                if (!cities.containsKey(from)) {
                    cities.put(from, new City(from));
                    outgoing_services.put(from, new ArrayList<>());
                }
                if (!cities.containsKey(to)) {
                    cities.put(to, new City(to));
                    outgoing_services.put(to, new ArrayList<>());
                }
                outgoing_services.get(from).add(service);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void output_cheapest_route(String from, String to, OutputStream out) {
        reset();
        Pair<Integer, Integer> totals = calc_route(from, to);
        if (totals.getFirst() == Integer.MAX_VALUE) {
            System.out.println("There is no route from " + from + " to " + to);
        } else {
            System.out.println("The cheapest route from " + from + " to " + to);
            System.out.println("costs " + totals.getFirst() + " euros and spans " + totals.getSecond() + " kilometers");
            System.out.println(recover_route(to) + "\n");
        }
    }

    public boolean is_valid_city(String name) {
        return cities.containsKey(name);
    }

    public Pair<Integer, Integer> calc_route(String from, String to) {
        PriorityQueue<City> candidates = new PriorityQueue<>(Comparator.comparingInt(c -> c.total_fee));
        City start_city = cities.get(from);
        start_city.total_fee = 0;
        start_city.total_distance = 0;
        candidates.add(start_city);

        while (!candidates.isEmpty()) {
            City visiting_city = candidates.poll();
            if (!visiting_city.visited) {
                visiting_city.visited = true;
                for (Service service : outgoing_services.get(visiting_city.name)) {
                    City next_city = cities.get(service.destination);
                    int next_fee = service.fee + visiting_city.total_fee;
                    if (next_fee < next_city.total_fee && !next_city.name.equals(from)) {
                        next_city.total_fee = next_fee;
                        next_city.total_distance = service.distance + visiting_city.total_distance;
                        next_city.from_city = visiting_city.name;
                        candidates.add(next_city);
                    }
                }
            }
        }

        if (cities.get(to).visited) {
            return new Pair<>(cities.get(to).total_fee, cities.get(to).total_distance);
        } else {
            return new Pair<>(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }
    }

    public String recover_route(String city) {
        StringBuilder route = new StringBuilder();
        String current = city;
        while (!current.equals("")) {
            route.insert(0, current);
            String prev = cities.get(current).from_city;
            if (!prev.equals("")) {
                route.insert(0, " to ");
            }
            current = prev;
        }
        return route.toString();
    }

    private static class Pair<A, B> {
        private final A first;
        private final B second;

        Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        A getFirst() {
            return first;
        }

        B getSecond() {
            return second;
        }
    }
}

