import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Customer[] customers;
        Operator[] operators;

        System.out.println("""
                1 - Creating a new Customer
                2 - Creating a new Operator
                3 - A customer can talk to another customer
                4 - A customer can send a message to another customer
                5 - A customer can connect to the internet
                6 - A customer can pay his/her bills.
                7 - A customer can change his/her operator
                8 - A customer can change his/her Bill limit
                """);

        int quantityCustomer = Integer.parseInt(scanner.nextLine());
        int quantityOperator = Integer.parseInt(scanner.nextLine());
        int quantitySimulations = Integer.parseInt(scanner.nextLine());

        try {
            if (quantityCustomer < 1 || quantityOperator < 1) {
                throw new IllegalArgumentException("Quantity customer or operator cannot be negative or equal to 0!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        customers = new Customer[quantityCustomer];
        operators = new Operator[quantityOperator];

        int numCustomer = -1;
        int numOperator = -1;
        for (int i = 0; i < quantitySimulations; i++) {
            int numSimulation = Integer.parseInt(scanner.next());
            try {
                switch (numSimulation) {
                    case 1:
                        String name = scanner.next();
                        int age = Integer.parseInt(scanner.next());
                        int idOperator = Integer.parseInt(scanner.next());
                        double limitingAmount = Double.parseDouble(scanner.next());

                        if (numCustomer >= quantityCustomer - 1) {
                            throw new ArrayIndexOutOfBoundsException("Quantity customer is max!");
                        }
                        if (operators[idOperator] == null) {
                            throw new NullPointerException("There is no operator with this ID!");
                        }

                        customers[++numCustomer] = new Customer(numCustomer, name, age, operators[idOperator], limitingAmount);
                        break;
                    case 2:
                        double talkingCharge = Double.parseDouble(scanner.next());
                        double messageCost = Double.parseDouble(scanner.next());
                        double networkCharge = Double.parseDouble(scanner.next());
                        int discountRate = Integer.parseInt(scanner.next());

                        if (numOperator >= quantityOperator - 1) {
                            throw new ArrayIndexOutOfBoundsException("Quantity operator is max!");
                        }

                        operators[++numOperator] = new Operator(numOperator, talkingCharge, messageCost, networkCharge, discountRate);
                        break;
                    case 3:
                        int idCallerCustomer = Integer.parseInt(scanner.next());
                        int idSecondCustomer = Integer.parseInt(scanner.next());
                        int timeCall = Integer.parseInt(scanner.next());

                        if (idCallerCustomer > quantityCustomer - 1 || idSecondCustomer > quantityCustomer - 1) {
                            throw new IndexOutOfBoundsException("Invalid ID!");
                        }
                        if (idCallerCustomer > numCustomer || idSecondCustomer > numCustomer) {
                            throw new IndexOutOfBoundsException("There is no customer with this ID!");
                        }

                        customers[idCallerCustomer].talk(timeCall, customers[idSecondCustomer]);
                        break;
                    case 4:
                        int idSendingCustomer = Integer.parseInt(scanner.next());
                        int idAddresseeCustomer = Integer.parseInt(scanner.next());
                        int quantityMessage = Integer.parseInt(scanner.next());

                        if (idSendingCustomer > quantityCustomer - 1 || idAddresseeCustomer > quantityCustomer - 1) {
                            throw new IndexOutOfBoundsException("Invalid ID!");
                        }
                        if (idSendingCustomer > numCustomer || idAddresseeCustomer > numCustomer) {
                            throw new IndexOutOfBoundsException("There is no customer with this ID!");
                        }

                        customers[idSendingCustomer].message(quantityMessage, customers[idAddresseeCustomer]);
                        break;
                    case 5:
                        int idUseInternetCustomer = Integer.parseInt(scanner.next());
                        double quantityInternet = Double.parseDouble(scanner.next());

                        if (idUseInternetCustomer > quantityCustomer - 1) {
                            throw new IndexOutOfBoundsException("Invalid ID!");
                        }
                        if (idUseInternetCustomer > numCustomer) {
                            throw new IndexOutOfBoundsException("There is no customer with this ID!");
                        }

                        customers[idUseInternetCustomer].connection(quantityInternet);
                        break;
                    case 6:
                        int idPayingCustomer = Integer.parseInt(scanner.next());
                        double amountPay = Double.parseDouble(scanner.next());

                        if (idPayingCustomer > quantityCustomer - 1) {
                            throw new IndexOutOfBoundsException("Invalid ID!");
                        }
                        if (idPayingCustomer > numCustomer) {
                            throw new IndexOutOfBoundsException("There is no customer with this ID!");
                        }

                        customers[idPayingCustomer].getBill().pay(amountPay);
                        break;
                    case 7:
                        int idCustomer = Integer.parseInt(scanner.next());
                        int newIdOperator = Integer.parseInt(scanner.next());

                        if (idCustomer > quantityCustomer - 1) {
                            throw new IndexOutOfBoundsException("Invalid ID!");
                        }
                        if (idCustomer > numCustomer) {
                            throw new IndexOutOfBoundsException("There is no customer with this ID!");
                        }

                        if (newIdOperator > quantityOperator - 1) {
                            throw new IndexOutOfBoundsException("Invalid ID!");
                        }
                        if (newIdOperator > numOperator) {
                            throw new IndexOutOfBoundsException("There is no operator with this ID!");
                        }

                        customers[idCustomer].setOperator(operators[newIdOperator]);
                        break;
                    case 8:
                        int idCustomerWithNewLimit = Integer.parseInt(scanner.next());
                        double newLimit = Double.parseDouble(scanner.next());

                        if (idCustomerWithNewLimit > quantityCustomer - 1) {
                            throw new IndexOutOfBoundsException("Invalid ID!");
                        }
                        if (idCustomerWithNewLimit > numCustomer) {
                            throw new IndexOutOfBoundsException("There is no customer with this ID!");
                        }

                        customers[idCustomerWithNewLimit].getBill().changeTheLimit(newLimit);
                        break;
                    default:
                        System.out.println("The number simulation must be from 1 to 8! Try again!");
                        i--;
                }
            } catch (IllegalArgumentException | IndexOutOfBoundsException | NullPointerException e) {
                System.out.println(e.getMessage() + " Try again!");
                i--;
            }
        }

        //Вывожу информацию о всех операторах
        for (int i = 0; i < quantityOperator; i++) {
            if (operators[i] == null) {
                break;
            }
            System.out.println(operators[i]);
        }

        //Вывожу информацию о всех клиентах
        for (int i = 0; i < quantityCustomer; i++) {
            if (customers[i] == null) {
                break;
            }
            System.out.println(customers[i]);
        }

        //Пользователь, который разговаривал больше остальных
        System.out.println(customers[Customer.bestTalkingTimeCustomer(customers)].getName() + ": " +
                customers[Customer.bestTalkingTimeCustomer(customers)].getTalkingTime());

        //Пользователь, который отправил сообщений больше остальных
        System.out.println(customers[Customer.bestMessagesCustomer(customers)].getName() + ": " +
                customers[Customer.bestMessagesCustomer(customers)].getNumMessages());

        //Пользователь, который тратил интернет больше остальных
        System.out.printf(customers[Customer.bestInternetCustomer(customers)].getName() + ": " +
                "%.1f", customers[Customer.bestInternetCustomer(customers)].getQuantityMBs());
    }
}
