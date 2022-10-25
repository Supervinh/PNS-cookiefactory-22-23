package fr.unice.polytech.cf;

public class Order {
    private Cart cart;
    //private Store store;

    private CommandState commandState;

    /*public Order(Cart panier,Store store){
        this.commandState=CommandState.UNPAID;
        this.cart=panier;
        this.store=store;
    }*/
    public Order(Cart cart){
        this.commandState=CommandState.UNPAID;
        this.cart=cart;

    }

    public CommandState getCommandState() {
        return commandState;
    }

    public void setCommandState(CommandState commandState) {
        this.commandState = commandState;
    }
    public void paycommand(){
        if(commandState==CommandState.UNPAID){
            commandState=CommandState.PAID;
        }
    }
    public void StartCook(){
        if(commandState==CommandState.PAID){
            commandState=CommandState.WORKING_ON_IT;
        }
    }
    public void Ready(){
        if(commandState==CommandState.WORKING_ON_IT){
            commandState=CommandState.READY;
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






}
