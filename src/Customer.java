public class Customer {
    private final int id;
    private int age;
    private final String name;
    private int talkingTime = 0;
    private int numMessages = 0;
    private double quantityMBs = 0;
    private Operator operator;
    private final Bill bill;

    static int bestTalkingTimeCustomer(Customer[] customers) {
        int maxTimeTalk = customers[0].getTalkingTime();
        int idBestCustomer = 0;

        for (int i = 1; i < customers.length; i++) {
            if (maxTimeTalk < customers[i].getTalkingTime()) {
                maxTimeTalk = customers[i].getTalkingTime();
                idBestCustomer = i;
            }
        }
        return idBestCustomer;
    }
    static int bestMessagesCustomer(Customer[] customers) {
        int maxMessages = customers[0].getNumMessages();
        int idBestCustomer = 0;

        for (int i = 1; i < customers.length; i++) {
            if (maxMessages < customers[i].getNumMessages()) {
                maxMessages = customers[i].getNumMessages();
                idBestCustomer = i;
            }
        }
        return idBestCustomer;
    }
    static int bestInternetCustomer(Customer[] customers) {
        double maxMBs = customers[0].getQuantityMBs();
        int idBestCustomer = 0;

        for (int i = 1; i < customers.length; i++) {
            if (maxMBs < customers[i].getQuantityMBs()) {
                maxMBs = customers[i].getQuantityMBs();
                idBestCustomer = i;
            }
        }
        return idBestCustomer;
    }

    public Customer(int id, String name, int age, Operator operator, double limitingAmount) {
        setAge(age);
        this.id = id;
        this.name = name;
        this.operator = operator;
        bill = new Bill(limitingAmount);
    }

    void talk(int minute, Customer other) {
        try {
            if (minute < 0) {
                throw new IllegalArgumentException("Минуты не могут быть отрицательными!");
            }
            if (getId() == other.getId()) {
                throw new IllegalArgumentException("Клиент не может звонит самому себе!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        double amount = operator.calculateTalkingCost(minute, this);
        if (bill.check(amount)) {
            bill.add(amount);
            this.talkingTime += minute;
            operator.addTalkingTime(minute);
            other.addTalkingTime(minute);
        }
    }

    void message(int quantity, Customer other){
        try {
            if (quantity < 0) {
                throw new IllegalArgumentException("Количество не может быть отрицательным!");
            }
            if (getId() == other.getId()) {
                throw new IllegalArgumentException("Клиент не может писать самому себе!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        double amount = operator.calculateMessageCost(quantity, this, other);
        if (bill.check(amount)) {
            bill.add(amount);
            this.numMessages += quantity;
            other.numMessages += quantity;
            operator.addQuantityMessage(quantity);
        }
    }

    void connection(double MBs) {
        try {
            if (MBs < 0) {
                throw new IllegalArgumentException("Количество интернета не может быть отрицательным");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        double amount = operator.calculateNetworkCost(MBs);
        if (bill.check(amount)) {
            bill.add(amount);
            this.quantityMBs += MBs;
            operator.addQuantityMBs(MBs);

        }
    }

    public double getQuantityMBs() {
        return quantityMBs;
    }

    public int getNumMessages() {
        return numMessages;
    }

    public int getTalkingTime() {
        return talkingTime;
    }

    void addTalkingTime(int minute) {
        talkingTime += minute;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {

        if (age < 1 || age > 150) {
            throw new IllegalArgumentException("Invalid age!");
        } else {
            this.age = age;
        }
    }

    public String getName() {
        return name;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        if (operator.getId() == this.operator.getId()) {
            System.out.println("This client is already using this operator!");
            return;
        }
        this.operator = operator;
    }

    public Bill getBill() {
        return bill;
    }

    @Override
    public String toString() {
        return String.format("Customer %d: %.1f %.1f", id, bill.getTotalMoneySpent(), bill.getCurrentDebt());
    }
}
