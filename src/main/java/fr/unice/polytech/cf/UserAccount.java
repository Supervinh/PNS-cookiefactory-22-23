package fr.unice.polytech.cf;

import fr.unice.polytech.cf.Exceptions.OrderNotReadyException;

import java.util.ArrayList;
import java.util.List;

public class UserAccount {
    private String name;
    private boolean isVIP;
    private String surname;
    private String mail;
    private final List<Order> currentOrders;

    public UserAccount(){
        currentOrders = new ArrayList<>();
    }

    public List<Order> getCurrentOrders(){
        return new ArrayList<>(currentOrders);
    }

    public void addOrder(Order newOrder){
        currentOrders.add(newOrder);
    }
    public void retrieveOrder() throws OrderNotReadyException {    // Pour le moment nous récupérons la première commande de la liste
        if(currentOrders.get(0).getCommandState() != CommandState.READY){
            throw new OrderNotReadyException();
        }else{
            currentOrders.get(0).AdvanceOrder();    // TODO: rajouter la commande terminer à l'historique des commandes et la supprimer des commandes actuelles
        }
    }
    public void utiliseReduction(){
        //TODO
    }
}
