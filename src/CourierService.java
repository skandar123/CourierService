import java.util.*;

public class CourierService {
    //Adding packages to shipment
    public List<Shipment> addPackagesToShipment(int max_carriable_weight, List<Package> packages) {
        //Sorting packages in decreasing order to give preference to heavier packages
        List<Package> reversedList = packages.stream()
                .sorted(Comparator.comparingInt((Package p) -> p.weight).reversed())
                .toList();

        List<Shipment> shipments = new ArrayList<>();
        Shipment currentPacking = new Shipment(new ArrayList<Package>(), 0, 0, 0);
        List<Package> addedPackages = new ArrayList<>();
        int currentWeight = 0, n=reversedList.size();

        while(addedPackages.size() < n) {
            for (Package p : reversedList) {
                if(addedPackages.contains(p)) continue;
                //Adding packages to shipment only if they don't exceed the maximum carriable weight
                if (p.weight > max_carriable_weight) {
                    throw new IllegalArgumentException("Item weight " + p.weight + " exceeds maximum carriable weight!");
                }

                //Adding packages to shipment ensuring that the total weight of packages is below the maximum carriable weight
                if (currentWeight + p.weight <= max_carriable_weight) {
                    currentPacking.addPackages(p, p.weight);
                    addedPackages.add(p);
                    currentWeight += p.weight;
                }
            }
            shipments.add(currentPacking);
            currentPacking = new Shipment(new ArrayList<Package>(),0, 0, 0);
            currentWeight = 0;
        }
        //Sorting shipments in decreasing order by total weight so that heavier shipments are delivered first
        shipments.sort(Comparator.comparingInt((Shipment s) -> s.totalWeight).reversed());
        return shipments;
    }

    //Calculating delivery time of shipments
    public void calculateShippingTime(int speed, List<Shipment> shipments){
        for(Shipment s: shipments){
            int n=s.packages.size(), max_distance = 0;

            for (int i = 0; i < n; i++) {
                //Selecting the maximum distance amongst all the package distances,
                //for calculating the delivery time of shipments as all packages will be delivered in the same route
                if (s.packages.get(i).distance > max_distance) max_distance = s.packages.get(i).distance;
            }
            float timeForDelivery = ((float)max_distance / speed); //Time for one-way delivery
            timeForDelivery = (int)(timeForDelivery * 100) / 100f; //Time after truncating to 2 decimal places
            timeForDelivery *= 2; //Time for round trip
            s.setTime(timeForDelivery);
        }
    }

    //Calculating delivery time for individual packages
    public void calculateDeliveryTime(int speed, List<Shipment> shipments){
        for(Shipment s: shipments){
            int n=s.packages.size();

            for (int i = 0; i < n; i++) {
                Package pkg = s.packages.get(i);
                int distance = pkg.distance;
                float timeForDelivery = ((float)distance / speed); //Time for delivering the package
                timeForDelivery = (int)(timeForDelivery * 100) / 100f; //Time after truncating to 2 decimal places
                pkg.setDeliveryTime(timeForDelivery);
            }
        }
    }

    //Assigning vehicles to shipments
    public void assignVehicles(int num_of_vehicles, List<Shipment> shipments){
        //Creating a priority queue of vehicles based on time, to select the vehicle that comes back first
        PriorityQueue<Vehicle> queue = new PriorityQueue<>(Comparator.comparingDouble(v -> v.time));

        //Adding vehicles to the priority queue
        for(int i=1; i<=num_of_vehicles; i++){
            Vehicle v = new Vehicle(i, 0);
            queue.add(v);
        }

        for(Shipment s : shipments){
            int vehiclesInQueue = queue.size();
            Vehicle vehicle = queue.remove(); //Remove a vehicle that is available
            s.setVehicle(vehicle.getVehicleId()); //Setting the vehicle id that gets assigned to a shipment

            int n=s.packages.size();

            for (int i = 0; i < n; i++) {
                Package pkg = s.packages.get(i);
                pkg.setDeliveryTime(pkg.getDeliveryTime()+vehicle.getTime()); //Setting the actual delivery time of a package
            }

            vehicle.setTime(vehicle.getTime()+s.getTime()); //Calculating after how much time a vehicle will be available
            queue.add(vehicle); //Adding the vehicle back so that it can be assigned again
        }
    }
}
