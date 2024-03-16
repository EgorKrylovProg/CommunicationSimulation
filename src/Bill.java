public class Bill {
    private double limitingAmount;
    private double currentDebt = 0;
    private double totalMoneySpent = 0;

    Bill(double limitingAmount) {
        setLimitingAmount(limitingAmount);
    }

    boolean check(double amount) {
        if (amount + currentDebt >= limitingAmount) {
            return false;
        }
        return true;
    }

    void add(double amount) {
        currentDebt += amount;
    }

    void pay(double amount) {
        try {
            if (amount < 0) {
                throw new IllegalArgumentException("Сумма платежа не может быть отрицательной!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        currentDebt -= amount;
        totalMoneySpent += amount;
    }

    void changeTheLimit(double amount) {
        try {
            if (limitingAmount < 0) {
                throw new IllegalArgumentException("Лимит счета не может быть отрицательным!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        limitingAmount = amount;
    }

    public double getCurrentDebt() {
        return currentDebt;
    }

    public double getTotalMoneySpent() {
        return totalMoneySpent;
    }

    private void setLimitingAmount(double limitingAmount) {

        if (limitingAmount < 0) {
            throw new IllegalArgumentException("Limit Bill cannot be negative!");
        }
        this.limitingAmount = limitingAmount;
    }
}
