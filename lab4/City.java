public class City {
    String name;
    boolean visited;
    int total_fee;
    int total_distance;
    String from_city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public City(String name) {
        this.name = name;
    }

    public int getTotal_distance() {
        return total_distance;
    }

    public void setTotal_distance(int total_distance) {
        this.total_distance = total_distance;
    }

    public String getFrom_city() {
        return from_city;
    }

    public void setFrom_city(String from_city) {
        this.from_city = from_city;
    }
}
