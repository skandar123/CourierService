import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryTimeEstimation {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int baseDeliveryCost = sc.nextInt(); //Base delivery cost
        int numOfPackages = sc.nextInt(); //Number of packages to deliver
        List<Package> packages = new ArrayList<>();
        for(int i=0; i<numOfPackages; i++){
            String id = sc.next(); //Id of the package
            int weight = sc.nextInt(); //Weight of the package
            int distance = sc.nextInt(); //Distance to cover to deliver the package
            String offerCode = sc.next(); //Offer code applied
            Package pkg=new Package(id, weight, distance, offerCode);
            packages.add(pkg);
        }
        int num_of_vehicles = sc.nextInt(); //Number of vehicles available to deliver the packages
        int speed = sc.nextInt(); //Speed of the vehicles
        int max_carriable_weight = sc.nextInt(); //Maximum weight a vehicle can carry

        CourierService cs = new CourierService();

        //List of shipments after adding the packages
        List<Shipment> shipments = cs.addPackagesToShipment(max_carriable_weight, packages);

        cs.calculateDeliveryTime(speed, shipments); //Calculating delivery time for packages
        List<Shipment> shipments2 = cs.calculateShippingTime(speed, shipments); //Calculating delivery time for shipments
        cs.assignVehicles(num_of_vehicles, shipments2); //Assigning vehicles to shipments

        for(Package p : packages){
            //Calculating Discount percent if applicable
            int discountPercent = p.discountPercent(p.weight, p.distance, p.offerCode);
            //Calculating delivery cost without discount
            int deliveryCost = p.deliveryCost(baseDeliveryCost, p.weight, p.distance);
            //Calculating discount amount
            int discountAmount = p.discountAmount(deliveryCost, discountPercent);
            //Calculating the final delivery cost after applying discount if applicable
            int finalDeliveryCost = deliveryCost - discountAmount;
            //Displaying package id, discount amount, final delivery cost and delivery time
            System.out.println(p.getId()+" "+discountAmount+" "+finalDeliveryCost+" "+p.getDeliveryTime());
        }
    }
}
