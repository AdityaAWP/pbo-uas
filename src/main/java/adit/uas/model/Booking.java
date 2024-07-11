package adit.uas.model;

public class Booking {
    private int id;
    private String customerName;
    private int travelId;
    private String travelOrigin;
    private String travelDestination;
    private String travelSchedule;
    private int ticketCount;
    private double totalCost;

    public Booking(int id, String customerName, int travelId, String travelOrigin, String travelDestination,
            String travelSchedule,
            int ticketCount, double totalCost) {
        this.id = id;
        this.customerName = customerName;
        this.travelId = travelId;
        this.travelOrigin = travelOrigin;
        this.travelDestination = travelDestination;
        this.travelSchedule = travelSchedule;
        this.ticketCount = ticketCount;
        this.totalCost = totalCost;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getTravelId() {
        return travelId;
    }

    public void setTravelId(int travelId) {
        this.travelId = travelId;
    }

    public String getTravelOrigin() {
        return travelOrigin;
    }

    public void setTravelOrigin(String travelOrigin) {
        this.travelOrigin = travelOrigin;
    }

    public String getTravelDestination() {
        return travelDestination;
    }

    public void setTravelDestination(String travelDestination) {
        this.travelDestination = travelDestination;
    }

    public String getTravelSchedule() {
        return travelSchedule;
    }

    public void setTravelSchedule(String travelSchedule) {
        this.travelSchedule = travelSchedule;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
