import java.util.List;

public class Shipment {
    List<Package> packages; //Packages in the shipment
    int totalWeight; //Total weight of the shipment
    float time; //Time taken to deliver the shipment without considering vehicle availability
    int vehicle; //Vehicle assigned for shipment

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void setVehicle(int vehicle) {
        this.vehicle = vehicle;
    }

    public Shipment(List<Package> packages, int totalWeight, float time, int vehicle) {
        this.packages = packages;
        this.totalWeight = totalWeight;
        this.time = time;
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "packages=" + packages +
                ", totalWeight=" + totalWeight +
                ", time=" + time +
                ", vehicle=" + vehicle +
                '}';
    }

    public void addPackages(Package pkg, int weight){
        packages.add(pkg);
        totalWeight+=weight;
    }
}
