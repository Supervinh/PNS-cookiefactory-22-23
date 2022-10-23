package fr.unice.polytech.cf.Exceptions;

public class OrderNotReadyException extends Throwable{

    public OrderNotReadyException() {
        super("The order is not ready yet !");
    }
}
