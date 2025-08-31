public class Vehicle {
    int vehicleId; //Vehicle id to identify which vehicle got assigned
    float time; //After how much time the vehicle will be available

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public Vehicle(int vehicleId, float time) {
        this.vehicleId = vehicleId;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", time=" + time +
                '}';
    }
}
