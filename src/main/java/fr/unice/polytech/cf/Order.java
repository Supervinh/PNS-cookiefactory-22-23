package fr.unice.polytech.cf;

public class Order {
    private Cart panier;
    private Store store;
    private CommandState commandState;
    public Order(Cart panier,Store store){
        this.commandState=CommandState.UNPAID;
        this.panier=panier;
        this.store=store;
    }
    public Order(Cart panier){
        this.commandState=CommandState.UNPAID;
        this.panier=panier;

    }
    public CommandState getCommandState() {
        return commandState;
    }

    public void setCommandState(CommandState commandState) {
        this.commandState = commandState;
    }
    public void commandpaid(){
        if(commandState==commandState.UNPAID){
            commandState=commandState.PAID;
        }
    }

    public void commandcanceled(){
        if(commandState==commandState.WORKING_ON_IT || commandState==commandState.READY || commandState==commandState.DELIVERED){

        }
        else{
            commandState=commandState.CANCELED;
        }
    }
    public void preparingCommand() {
        if (commandState == CommandState.PAID){
            commandState = commandState.WORKING_ON_IT;
    }
    }
    public void commandReady() {
        if (commandState == CommandState.WORKING_ON_IT){
            commandState = commandState.READY;
    }
    }
    public void delivered(){
        if(commandState==CommandState.READY) {
            commandState = commandState.DELIVERED;
        }

    }



}
