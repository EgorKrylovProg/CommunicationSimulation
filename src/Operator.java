public class Operator {
    private final int id;
    private double talkingCharge;
    private double messageCost;
    private double networkCharge;
    private int discountRate;
    private int talkingTime = 0;
    private int quantityMessage = 0;
    private double quantityMBs = 0;

    Operator(int id, double talkingCharge, double messageCost, double networkCharge, int discountRate) {
        this.id = id;
        setTalkingCharge(talkingCharge);
        setMessageCost(messageCost);
        setNetworkCharge(networkCharge);
        setDiscountRate(discountRate);
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

    @Override
    public String toString() {
        return String.format("Operator %d: %d %d %.1f", id, talkingTime, quantityMessage, quantityMBs);
    }

    private void setTalkingCharge(double talkingCharge) {
        if (talkingCharge <= 0) {
            throw new IllegalArgumentException("Invalid cost!");
        }
        this.talkingCharge = talkingCharge;
    }

    private void setMessageCost(double messageCost) {
        if (messageCost <= 0) {
            throw new IllegalArgumentException("Invalid cost!");
        }
        this.messageCost = messageCost;
    }

    private void setNetworkCharge(double networkCharge) {
        if (networkCharge <= 0) {
            throw new IllegalArgumentException("Invalid cost!");
        }
        this.networkCharge = networkCharge;
    }

    private void setDiscountRate(int discountRate) {
        if (discountRate < 1 || discountRate > 100) {
            throw new IllegalArgumentException("Invalid discount rate!");
        }
        this.discountRate = discountRate;
    }
}

