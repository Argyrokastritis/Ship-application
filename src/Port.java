import java.util.ArrayList;

public class Port {
    private int ID;
    private String name;
    private double lat;
    private double lon;
    private ArrayList<Container> containers;
    private ArrayList<Ship> ships;

    public Port(int ID, String name, double lat, double lon) {
        this.ID = ID;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.containers = new ArrayList<>();
        this.ships = new ArrayList<>();
    }

    public double calculateDistance(Port other) {
        return Math.acos(Math.sin(Math.toRadians(this.lat)) * Math.sin(Math.toRadians(other.lat)) + Math.cos(Math.toRadians(this.lat)) * Math.cos(Math.toRadians(other.lat)) * Math.cos(Math.toRadians(other.lon) - Math.toRadians(this.lon))) * 6371;
    }

    public void addContainer(Container container) {
        this.containers.add(container);
    }

    public void unloadContainers() {
        this.containers.clear();
    }

    public void addShip(Ship ship) {
        this.ships.add(ship);
    }

    public void removeShip(Ship ship) {
        this.ships.remove(ship);
    }

    // getters, setters and toString methods...
}

