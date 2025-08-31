public class Package {
    String id; //package's id
    int weight; //package's weight in kg
    int distance; //package's delivery distance in km
    String offerCode; //package's offer code
    float deliveryTime; //The actual time taken to deliver the package, considering vehicle availability

    public String getId() {
        return id;
    }

    public float getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(float deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Package(String id, int weight, int distance, String offerCode) {
        this.id = id;
        this.weight = weight;
        this.distance = distance;
        this.offerCode = offerCode;
    }

    @Override
    public String toString() {
        return "Package{" +
                "id='" + id + '\'' +
                ", weight=" + weight +
                ", distance=" + distance +
                ", offerCode='" + offerCode + '\'' +
                ", deliveryTime=" + deliveryTime +
                '}';
    }

    //Delivery cost without discount
    public int deliveryCost(int baseDeliveryCost, int weight, int distance){
        return (baseDeliveryCost + weight*10 + distance*5);
    }

    //Discount percent if eligible
    public int discountPercent(int weight, int distance, String offerCode){
        int discount;
        if(offerCode.equals("OFR001") && (weight>=70 && weight<=200) && (distance<200)){
            discount=10;
        }else if(offerCode.equals("OFR002") && (weight>=100 && weight<=250) && (distance>=50 && distance<=150)){
            discount=7;
        }else if(offerCode.equals("OFR003") && (weight>=10 && weight<=150) && (distance>=50 && distance<=250)){
            discount=5;
        }else{
            discount=0;
        }
        return discount;
    }

    //Discount amount calculation
    public int discountAmount(int deliveryCost, int discount){
       return (deliveryCost*discount)/100;
    }
}
