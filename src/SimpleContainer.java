// SimpleContainer.java
public class SimpleContainer extends Container {
    public SimpleContainer(int ID, int weight) {
        super(ID, weight);
    }

    @Override
    public double calculateFuel() {
        // calculate fuel for simple container
        return this.getWeight() * 20;  // assuming each simple container requires an additional 20 litres of fuel consumption per tonne per kilometre
    }
}





