package fr.unice.polytech.cf.Exceptions;

public class EmptyCartException extends Throwable{
    public EmptyCartException() {
        super("The cart is empty");
    };
}
