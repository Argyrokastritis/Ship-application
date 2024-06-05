// Main.java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Port> ports = new ArrayList<>();
        ArrayList<Ship> ships = new ArrayList<>();
        ArrayList<Container> containers = new ArrayList<>();  // Add this line

        // Read ports from ports.txt
        try (BufferedReader br = new BufferedReader(new FileReader("src/ports.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double lat = Double.parseDouble(parts[2]);
                double lon = Double.parseDouble(parts[3]);
                Port port = new Port(id, name, lat, lon);
                ports.add(port);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read ships from ships.txt
        try (BufferedReader br = new BufferedReader(new FileReader("src/ships.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double fuelPerKm = Double.parseDouble(parts[2]);
                int maxWeight = Integer.parseInt(parts[3]);
                int maxContainers = Integer.parseInt(parts[4]);
                int maxHeavyContainers = Integer.parseInt(parts[5]);
                int portId = Integer.parseInt(parts[6]);
                Port currentPort = ports.get(portId - 1);  // assuming port IDs are 1-based
                Ship ship = new Ship(id, name, fuelPerKm, maxWeight, maxContainers, maxHeavyContainers, currentPort);
                ships.add(ship);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Read containers from containers.txt
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/containers.txt"), StandardCharsets.UTF_8))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                System.out.println("Reading line " + lineNumber + ": " + line);
                String[] parts = line.split(", ");
                int id = lineNumber;  // generate ID automatically
                String type = parts[0];  // assuming the type of container is specified in the file
                int weight = Integer.parseInt(parts[1]);
                System.out.println("Parsed container: id=" + id + ", type=" + type + ", weight=" + weight);
                Container container;
                switch (type) {
                    case "απλό":  // "simple" in Greek
                        container = new SimpleContainer(id, weight);
                        break;
                    case "ψυγείο":  // "refrigerated" in Greek
                        container = new RefrigeratedContainer(id, weight);
                        break;
                    case "βυτίο":  // "tank" in Greek
                        container = new TankContainer(id, weight);
                        break;
                        default:
                        throw new IllegalArgumentException("Invalid container type: " + type);
                }
                    containers.add(container);
                System.out.println("Added container: " + container);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Load containers into ships
        for (Ship ship : ships) {
            for (Container container : containers) {
                ship.loadContainer(container);
            }
        }

        // Print the status of the ports and ships
        for (Port port : ports) {
            System.out.println(port.toString());
        }
        for (Ship ship : ships) {
            System.out.println(ship.toString());
        }

        // Calculate fuel, add fuel, move ships, and unload containers
        for (int i = 0; i < ships.size(); i++) {
            Ship ship = ships.get(i);
            Port destination = ports.get((i + 1) % ports.size());  // get the next port in the list, wrap around to the first port if at the end
            double fuelNeeded = ship.calculateFuel(destination);
            ship.addFuel(fuelNeeded);
            ship.moveTo(destination);
            ship.unloadContainers();
        }
    }
}
