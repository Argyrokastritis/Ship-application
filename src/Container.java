// Container.java
public abstract class Container {
    private int ID;
    private int weight;

    public Container(int ID, int weight) {
        this.ID = ID;
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }

    public abstract double calculateFuel();

    // getters, setters and toString methods...
}

