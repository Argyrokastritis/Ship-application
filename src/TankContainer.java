// TankContainer.java
public class TankContainer extends Container {
    public TankContainer(int ID, int weight) {
        super(ID, weight);
    }

    @Override
    public double calculateFuel() {
        // calculate fuel for tank container
        return this.getWeight() * 27;  // assuming each tank container requires an additional 27 litres of fuel consumption per tonne per kilometre
    }
}
