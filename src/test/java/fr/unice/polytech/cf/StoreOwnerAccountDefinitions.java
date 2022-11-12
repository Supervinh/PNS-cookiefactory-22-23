package fr.unice.polytech.cf;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalTime;

public class StoreOwnerAccountDefinitions {
    StoreOwnerAccount storeOwnerAccount = new StoreOwnerAccount("Cookie factory Biot");
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
}
