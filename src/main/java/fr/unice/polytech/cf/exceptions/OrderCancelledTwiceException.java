package fr.unice.polytech.cf.exceptions;

public class OrderCancelledTwiceException extends Throwable{

    public OrderCancelledTwiceException() {
        super("You have cancelled too many orders, please wait before ordering again!");
    }
}
