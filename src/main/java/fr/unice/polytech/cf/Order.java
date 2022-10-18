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
    public void AdvanceOrder(){
        switch (commandState){
            case UNPAID:
                commandState=CommandState.PAID;
                break;
            case PAID:
                commandState=CommandState.WORKING_ON_IT;
                break;
            case WORKING_ON_IT:
                commandState=CommandState.READY;

                break;
            case READY:
                commandState=CommandState.DELIVERED;
                break;
        }

    }
    public void cancelOrder(){
        if(commandState!=CommandState.WORKING_ON_IT || commandState!=CommandState.DELIVERED || commandState!=CommandState.READY ){
            commandState=CommandState.CANCELED;
        }
        else{
            System.out.println("your order can't be cancelled, it's already being prepared or ready");
        }
    }






}
