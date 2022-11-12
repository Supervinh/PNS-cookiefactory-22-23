package fr.unice.polytech.cf;

import java.util.Map;

public class Order {
    private final Cart cart;
    //private Store store;
    private int CommandNumber=1;

    private CommandState commandState;

    /*public Order(Cart panier,Store store){
        this.commandState=CommandState.UNPAID;
        this.cart=panier;
        this.store=store;
    }*/
    public Order(Cart cart){
        this.commandState=CommandState.UNPAID;
        this.cart=cart;
        //this.CommandNumber= new Random().nextInt(1,2000000);

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

    public int getNbCookies(){return cart.getNbCookies();}
    public double getPrice(){return cart.getPrice();}

}
