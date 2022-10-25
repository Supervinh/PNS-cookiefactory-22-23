package fr.unice.polytech.cf.exceptions;

public class EmptyCartException extends Throwable{
    public EmptyCartException() {
        super("The cart is empty");
    }
}
