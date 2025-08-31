import java.util.Scanner;

public class DeliveryCostEstimation {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int baseDeliveryCost = sc.nextInt(); //Base delivery cost
        int numOfPackages = sc.nextInt(); //Number of packages to deliver
        Package[] arr = new Package[numOfPackages];
        for(int i=0; i<numOfPackages; i++){
            String id = sc.next(); //Id of the package
            int weight = sc.nextInt(); //Weight of the package
            int distance = sc.nextInt(); //Distance to cover to deliver the package
            String offerCode = sc.next(); //Offer code applied
            Package pkg=new Package(id, weight, distance, offerCode);
            arr[i] = pkg;
        }
        for(int i=0; i<numOfPackages; i++){
            //Calculating Discount percent if applicable
            int discountPercent = arr[i].discountPercent(arr[i].weight, arr[i].distance, arr[i].offerCode);
            //Calculating delivery cost without discount
            int deliveryCost = arr[i].deliveryCost(baseDeliveryCost, arr[i].weight, arr[i].distance);
            //Calculating discount amount
            int discountAmount = arr[i].discountAmount(deliveryCost, discountPercent);
            //Calculating the final delivery cost after applying discount if applicable
            int finalDeliveryCost = deliveryCost - discountAmount;
            //Displaying package id, discount amount and final delivery cost
            System.out.println(arr[i].id+" "+discountAmount+" "+finalDeliveryCost);
        }
    }

}