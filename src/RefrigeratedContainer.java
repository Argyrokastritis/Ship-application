// RefrigeratedContainer.java
public class RefrigeratedContainer extends Container {
    public RefrigeratedContainer(int ID, int weight) {
        super(ID, weight);
    }

    @Override
    public double calculateFuel() {
        // calculate fuel for refrigerated container
        return this.getWeight() * 35;  // assuming each refrigerated container requires an additional 35 litres of fuel consumption per tonne per kilometre
    }
}
