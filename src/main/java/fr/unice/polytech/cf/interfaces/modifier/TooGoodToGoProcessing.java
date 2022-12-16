package fr.unice.polytech.cf.interfaces.modifier;

import fr.unice.polytech.cf.entities.Item;

import java.util.List;

public interface TooGoodToGoProcessing {
    void updateTooGoodToGo();

    List<Item> applyTooGoodToGoPolicy(List<Item> cartTooGoodToGo);
}
