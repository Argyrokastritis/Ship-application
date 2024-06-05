import java.util.ArrayList;

public class Ship {
    private int ID;
    private String name;
    private double fuelPerKm;
    private int maxWeight;
    private int maxContainers;
    private int maxHeavyContainers;
    private Port currentPort;
    private double fuel;
    private ArrayList<Container> containers;

    public Ship(int ID, String name, double fuelPerKm, int maxWeight, int maxContainers, int maxHeavyContainers, Port currentPort) {
        this.ID = ID;
        this.name = name;
        this.fuelPerKm = fuelPerKm;
        this.maxWeight = maxWeight;
        this.maxContainers = maxContainers;
        this.maxHeavyContainers = maxHeavyContainers;
        this.currentPort = currentPort;
        this.fuel = 0;
        this.containers = new ArrayList<>();
    }

    // Ship.java

    // existing code...

    public void loadContainer(Container container) {
        if (this.containers.size() < this.maxContainers && this.getWeight() + container.getWeight() <= this.maxWeight) {
            this.containers.add(container);
        }
    }

    public double calculateFuel(Port destination) {
        double distance = this.currentPort.calculateDistance(destination);
        double weight = this.getWeight();
        return distance * weight * this.fuelPerKm;
    }

    public void addFuel(double fuel) {
        this.fuel += fuel;
    }

    public void moveTo(Port destination) {
        this.currentPort = destination;
    }

    public void unloadContainers() {
        this.containers.clear();
    }

    private double getWeight() {
        double weight = 0;
        for (Container container : this.containers) {
            weight += container.getWeight();
        }
        return weight;
    }

// getters, setters and toString methods...
}

