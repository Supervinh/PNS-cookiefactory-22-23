package fr.unice.polytech.cf.cucumber;

import fr.unice.polytech.cf.entities.Store;
import fr.unice.polytech.cf.entities.StoreOwnerAccount;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalTime;

public class StoreOwnerAccountDefinitions {
    /*StoreOwnerAccount storeOwnerAccount = new StoreOwnerAccount("Cookie factory Biot");
    @Given("the store's opening time is {int}:{int}")
    public void theStoreOpeningTimeIs(int hours, int minutes ) {
        Store ownedStore = storeOwnerAccount.getOwnedStore();
        ownedStore.setOpeningTime(LocalTime.of(hours, minutes));
    }

    @When("I change the opening time to {int}:{int}")
    public void iChangeTheOpeningTimeTo(int hours, int minutes) {
        storeOwnerAccount.changeStoreOpeningTime(LocalTime.of(hours, minutes));
    }

    @Then("the store's opening time should be {int}:{int}")
    public void theStoreSOpeningTimeShouldBe(int hours, int minutes) {
        Store ownedStore = storeOwnerAccount.getOwnedStore();
        System.out.println(ownedStore.getOpeningTime());
        assert( ownedStore.getOpeningTime().equals(LocalTime.of(hours,minutes)));
    }

    @Given("the store's closing time is {int}:{int}")
    public void theStoreClosingTimeIs(int hours, int minutes ) {
        Store ownedStore = storeOwnerAccount.getOwnedStore();
        ownedStore.setClosingTime(LocalTime.of(hours, minutes));
    }

    @When("I change the closing time to {int}:{int}")
    public void iChangeTheClosingTimeTo(int hours, int minutes) {
        storeOwnerAccount.changeStoreClosingTime(LocalTime.of(hours, minutes));
    }

    @Then("the store's closing time should be {int}:{int}")
    public void theStoreSClosingTimeShouldBe(int hours, int minutes) {
        Store ownedStore = storeOwnerAccount.getOwnedStore();
        System.out.println(ownedStore.getClosingTime());
        assert( ownedStore.getClosingTime().equals(LocalTime.of(hours,minutes)));
    }

    @Given("the store's taxes are {int}%")
    public void theStoreSTaxesAre(int taxes) {
        Store ownedStore = storeOwnerAccount.getOwnedStore();
        ownedStore.setTaxes(taxes/100.0);
    }

    @When("I change the taxes to {int}%")
    public void iChangeTheTaxesTo(int taxes) {
        storeOwnerAccount.changeStoreTaxes(taxes/100.0);
    }

    @Then("the store's taxes should be {int}%")
    public void theStoreSTaxesShouldBe(int expectedTaxeRate) {
        Store ownedStore = storeOwnerAccount.getOwnedStore();
        assert( ownedStore.getTaxes() == expectedTaxeRate/100.0);
    }

    @And("the ingredients' prices should be taxed by {int}%")
    public void theIngredientsPricesShouldBeTaxedBy(int taxAppliedToIngredients) {
        Store ownedStore = storeOwnerAccount.getOwnedStore();
        for (Ingredient ingredient : ownedStore.getIngredients().keySet()) {
            double expectedPrice = Math.floor(ingredient.getBasePrice() * (1 + taxAppliedToIngredients/100.0) * 100) / 100;
            assert(ingredient.getPrice() == expectedPrice);
        }
    }*/
}
