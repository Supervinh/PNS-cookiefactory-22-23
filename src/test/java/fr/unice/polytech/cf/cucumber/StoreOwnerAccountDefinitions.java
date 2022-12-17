package fr.unice.polytech.cf.cucumber;

import fr.unice.polytech.cf.entities.Store;
import fr.unice.polytech.cf.entities.StoreOwnerAccount;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.interfaces.explorer.StoreFinder;
import fr.unice.polytech.cf.interfaces.modifier.StoreModifier;
import fr.unice.polytech.cf.repositories.StoreRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;

public class StoreOwnerAccountDefinitions {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreModifier storeModifier;

    @Autowired
    private StoreFinder storeFinder;

    @Before
    public void settingUpContext() {
        storeRepository.deleteAll();
    }

    @Given("the store {word} opens at {int}:{int}")
    public void theStoreOpeningTimeIs(String storeName, int hours, int minutes ) {
        LocalTime openingTime = LocalTime.of(hours, minutes);
        storeModifier.addStore(storeName, openingTime, openingTime.plusHours(8));
    }

    @When("I change {word}'s opening time to {int}:{int}")
    public void iChangeTheOpeningTimeTo(String storeName, int hours, int minutes) {
        storeModifier.changeStoreOpeningTime(storeFinder.findByName(storeName).get(), LocalTime.of(hours, minutes));
    }

    @Then("{word}'s opening time should be {int}:{int}")
    public void theStoreSOpeningTimeShouldBe(String storeName, int hours, int minutes) {
        assert(storeFinder.findByName(storeName).get().getOpeningTime().equals(LocalTime.of(hours,minutes)));
    }

    @Given("the store {word} closes at {int}:{int}")
    public void theStoreClosingTimeIs(String storeName, int hours, int minutes ) {
        LocalTime closingTime = LocalTime.of(hours, minutes);
        storeModifier.addStore(storeName, closingTime.minusHours(8), closingTime);
    }

    @When("I change {word}'s closing time to {int}:{int}")
    public void iChangeTheClosingTimeTo(String storeName, int hours, int minutes) {
        storeModifier.changeStoreClosingTime(storeFinder.findByName(storeName).get(), LocalTime.of(hours, minutes));
    }

    @Then("{word}'s closing time should be {int}:{int}")
    public void theStoreSClosingTimeShouldBe(String storeName, int hours, int minutes) {
        assert(storeFinder.findByName(storeName).get().getClosingTime().equals(LocalTime.of(hours,minutes)));
    }

    @Given("the store {word} with a tax rate at {int}%")
    public void theStoreSTaxesAre(String storeName, int taxes) {
        LocalTime openingTime = LocalTime.now();
        storeModifier.addStore(storeName, openingTime, openingTime.plusHours(8));
        storeModifier.changeStoreTaxes(storeFinder.findByName(storeName).get(), taxes/100.0);
    }

    @When("I change {word}'s tax rate to {int}%")
    public void iChangeTheTaxesTo(String storeName, int taxes) {
        storeModifier.changeStoreTaxes(storeFinder.findByName(storeName).get(), taxes/100.0);
    }

    @Then("{word}'s tax rate should be {int}%")
    public void theStoreSTaxesShouldBe(String storeName, int expectedTaxRate) {
        assert( storeFinder.findByName(storeName).get().getTaxes() == expectedTaxRate/100.0);
    }

}
