public class Operator {
    private final int id;
    private final double talkingCharge;
    private final double messageCost;
    private final double networkCharge;
    private final int discountRate;
    private int talkingTime = 0;
    private int quantityMessage = 0;
    private double quantityMBs = 0;

    Operator(int id, double talkingCharge, double messageCost, double networkCharge, int discountRate) {
        this.id = id;
        this.talkingCharge = talkingCharge;
        this.messageCost = messageCost;
        this.networkCharge = networkCharge;
        this.discountRate = discountRate;
    }

    double calculateTalkingCost(int minute, Customer customer) {
        if (customer.getAge() < 18 || customer.getAge() > 65) {
            return (1 - discountRate / 100.0) * talkingCharge * minute;
        }
        return talkingCharge * minute;
    }
    double calculateMessageCost(int quantity, Customer customer, Customer other) {
        if (customer.getOperator().getId() == other.getOperator().getId()) {
            return (1 - discountRate / 100.0) * messageCost * quantity;
        }
        return messageCost * quantity;
    }
    double calculateNetworkCost(double MBs) {
        return MBs * networkCharge;
    }
    void addTalkingTime(int minute) {
        talkingTime += minute;
    }
    void addQuantityMessage(int quantity) {
        quantityMessage += quantity;
    }
    void addQuantityMBs(double MBs) {
        quantityMBs += MBs;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return String.format("Operator %d: %d %d %.2f", id, talkingTime, quantityMessage, quantityMBs);
    }
}

