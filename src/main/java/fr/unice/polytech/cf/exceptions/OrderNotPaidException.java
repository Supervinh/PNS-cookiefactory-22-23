package fr.unice.polytech.cf.exceptions;

public class OrderNotPaidException extends Throwable {

    public OrderNotPaidException() {
        super("The order is not paid yet or is being prepared!");
    }
}
