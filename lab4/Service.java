public class Service {
    String destination;
    int fee;
    int distance;

    public String getDestination() {
        return destination;
    }

    public Service(String destination, int fee, int distance) {
        this.destination = destination;
        this.fee = fee;
        this.distance = distance;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
