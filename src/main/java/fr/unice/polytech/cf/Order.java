package fr.unice.polytech.cf;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

public class Order {
    private final Cart cart;
    private int CommandNumber=1;
    private final LocalDateTime retrieve;

    private CommandState commandState;

    public Order(Cart cart){
        this.commandState=CommandState.UNPAID;
        this.cart=cart;
        retrieve = LocalDateTime.now().plusHours(1);
    }

    public Order(Cart cart, LocalDateTime retrieve){
        this.commandState=CommandState.UNPAID;
        this.cart=cart;
        this.retrieve = retrieve;
    }

    public CommandState getCommandState() {
        return commandState;
    }

    public int getCommandNumber() {
        if(commandState!=CommandState.UNPAID){
        return CommandNumber;}
        else{
            return 0;
        }
    }
    public String getreceipt(){
        if(commandState!=CommandState.UNPAID){
        String info=cart.getPrice()+"\n";
            Map<Cookie,Integer> allcookie=cart.getCookies();
            for (Map.Entry mapentry : allcookie.entrySet()){
                Cookie b=(Cookie)mapentry.getKey();
                info=info+b.getName()+"\n";
            }
        return info;
        }
        else{
            return("commande non validé");
        }

    }

    public void setCommandState(CommandState commandState) {
        this.commandState = commandState;
    }
    public void paycommand() {
        if (commandState == CommandState.UNPAID) {
            commandState = CommandState.PAID;
        }
    }

    public void Delivered(){
        if(commandState==CommandState.READY){
            commandState=CommandState.DELIVERED;
        }
    }

    public void cancelOrder(){
        if(commandState==CommandState.PAID || commandState==CommandState.UNPAID ){
            commandState=CommandState.CANCELLED;
        }
        else{
            System.out.println("your order can't be cancelled, it's already being prepared or ready");
        }
    }


    public LocalDateTime getRetrieveDate(){return retrieve;}
    public int getCookingTime(){return cart.getCookingTime();}
    public int getNbCookies(){return cart.getNbCookies();}
    public double getPrice(){return cart.getPrice();}

}
