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

        if (quantityCustomer < 1 || quantityOperator < 1) {
            System.out.println("Число клиентов или операторов не может быть отрицательным или равным 0!");
            return;
        }
        else  {
            customers = new Customer[quantityCustomer];
            operators = new Operator[quantityOperator];
        }

        int numCustomer = -1;
        int numOperator = -1;
        for (int i = 0; i < quantitySimulations; i++) {
            int numSimulation = Integer.parseInt(scanner.next());

            switch (numSimulation) {
                case 1:
                    String name = scanner.next();
                    int age = Integer.parseInt(scanner.next());
                    int idOperator = Integer.parseInt(scanner.next());
                    double limitingAmount = Double.parseDouble(scanner.next());

                    if (age < 0 || age > 150) {
                        System.out.println("Некорректный возраст!");
                        i--;
                        continue;
                    }
                    if (operators[idOperator] == null) {
                        System.out.println("Оператора с таким ID не существует!");
                        i--;
                        continue;
                    }
                    if (limitingAmount < 0) {
                        System.out.println("Лимит счета не может быть отрицательным!");
                        i--;
                        continue;
                    }

                    customers[++numCustomer] = new Customer(numCustomer, name, age, operators[idOperator], limitingAmount);
                    break;
                case 2:
                    double talkingCharge = Double.parseDouble(scanner.next());
                    double messageCost = Double.parseDouble(scanner.next());
                    double networkCharge = Double.parseDouble(scanner.next());
                    int discountRate = Integer.parseInt(scanner.next());

                    if (talkingCharge <= 0 || messageCost <= 0 || networkCharge <= 0) {
                        System.out.println("Некорректное значение цены!");
                        i--;
                        continue;
                    }

                    if (discountRate < 1 || discountRate > 100) {
                        System.out.println("Некорректное значение скидочной цены!");
                    }

                    operators[++numOperator] = new Operator(numOperator, talkingCharge, messageCost, networkCharge, discountRate);
                    break;
                case 3:
                    int idCallerCustomer = Integer.parseInt(scanner.next());
                    int idSecondCustomer = Integer.parseInt(scanner.next());
                    int timeCall = Integer.parseInt(scanner.next());

                    if (idCallerCustomer > quantityCustomer - 1 || idSecondCustomer > quantityCustomer - 1) {
                        System.out.println("Некорректный ID клиента");
                        continue;
                    }
                    if (idCallerCustomer > numCustomer || idSecondCustomer > numCustomer) {
                        System.out.println("Клиента с таким ID не существует!");
                        continue;
                    }

                    customers[idCallerCustomer].talk(timeCall, customers[idSecondCustomer]);
                    break;
                case 4:
                    int idSendingCustomer = Integer.parseInt(scanner.next());
                    int idAddresseeCustomer = Integer.parseInt(scanner.next());
                    int quantityMessage = Integer.parseInt(scanner.next());

                    if (idSendingCustomer > quantityCustomer - 1 || idAddresseeCustomer > quantityCustomer - 1) {
                        System.out.println("Некорректный ID клиента");
                        continue;
                    }
                    if (idSendingCustomer > numCustomer || idAddresseeCustomer > numCustomer) {
                        System.out.println("Клиента с таким ID не существует!");
                        continue;
                    }

                    customers[idSendingCustomer].message(quantityMessage, customers[idAddresseeCustomer]);
                    break;
                case 5:
                    int idUseInternetCustomer = Integer.parseInt(scanner.next());
                    double quantityInternet = Double.parseDouble(scanner.next());

                    if (idUseInternetCustomer > quantityCustomer - 1) {
                        System.out.println("Некорректный ID клиента");
                        continue;
                    }
                    if (idUseInternetCustomer > numCustomer) {
                        System.out.println("Клиента с таким ID не существует!");
                        continue;
                    }

                    customers[idUseInternetCustomer].connection(quantityInternet);
                    break;
                case 6:
                    int idPayingCustomer = Integer.parseInt(scanner.next());
                    double amountPay = Double.parseDouble(scanner.next());

                    if (idPayingCustomer > quantityCustomer - 1) {
                        System.out.println("Некорректный ID клиента");
                        continue;
                    }
                    if (idPayingCustomer > numCustomer) {
                        System.out.println("Клиента с таким ID не существует!");
                        continue;
                    }

                    customers[idPayingCustomer].getBill().pay(amountPay);
                    break;
                case 7:
                    int idCustomer = Integer.parseInt(scanner.next());
                    int newIdOperator = Integer.parseInt(scanner.next());

                    if (idCustomer > quantityCustomer - 1) {
                        System.out.println("Некорректный ID клиента");
                        continue;
                    }
                    if (idCustomer > numCustomer) {
                        System.out.println("Клиента с таким ID не существует!");
                        continue;
                    }

                    if (newIdOperator > quantityOperator - 1) {
                        System.out.println("Некорректный ID оператора");
                        continue;
                    }
                    if (newIdOperator > numOperator) {
                        System.out.println("Оператора с таким ID не существует!");
                        continue;
                    }

                    customers[idCustomer].setOperator(operators[newIdOperator]);
                    break;
                case 8:
                    int idCustomerWithNewLimit = Integer.parseInt(scanner.next());
                    double newLimit = Double.parseDouble(scanner.next());

                    if (idCustomerWithNewLimit > quantityCustomer - 1) {
                        System.out.println("Некорректный ID клиента");
                        continue;
                    }
                    if (idCustomerWithNewLimit > numCustomer) {
                        System.out.println("Клиента с таким ID не существует!");
                        continue;
                    }

                    customers[idCustomerWithNewLimit].getBill().changeTheLimit(newLimit);
                    break;
                default:
                    System.out.println("Номер симуляции должен быть от 1 до 8! Попробуйте снова");
                    i--;

            }
        }

        //Вывожу информацию о всех операторах
        for (int i = 0; i < quantityOperator; i++) {
            System.out.println(operators[i].toString());
        }

        //Вывожу информацию о всех клиентах
        for (int i = 0; i < quantityCustomer; i++) {
            System.out.println(customers[i].toString());
        }

        //Пользователь, который разговаривал больше остальных
        System.out.println(customers[Customer.bestTalkingTimeCustomer(customers)].getName() + ": " +
                customers[Customer.bestTalkingTimeCustomer(customers)].getTalkingTime());

        //Пользователь, который отправил сообщений больше остальных
        System.out.println(customers[Customer.bestMessagesCustomer(customers)].getName() + ": " +
                customers[Customer.bestMessagesCustomer(customers)].getNumMessages());

        //Пользователь, который тратил интернет больше остальных
        System.out.printf(customers[Customer.bestInternetCustomer(customers)].getName() + ": " +
                "%.2f", customers[Customer.bestInternetCustomer(customers)].getQuantityMBs());
    }
}
