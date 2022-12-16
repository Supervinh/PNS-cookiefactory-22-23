# Cookiefactory-22-23-Team-N

# User stories 

## Sprint 1

### Passer commander

Issue https://github.com/PNS-Conception/cookiefactory-22-23-n/issues/2

**As a** customer,
**I want** to confirm my order
**so that** It can be prepared.

_**Cucumber.feature:**_ Cart.feature

_**Scenario:**_ Cart is empty

_**Scenario:**_ Cart is not empty


### Récupérer commande

Issue https://github.com/PNS-Conception/cookiefactory-22-23-n/issues/4

**As a** customer,
**I want** to retrieve my order as it is ready
**so that** I can eat my cookies.

_**Cucumber.feature:**_ Order.feature

_**Scenario:**_ The order is ready

_**Scenario:**_ The order is not ready


## Sprint 2

### Temps de préparation des cookies

Issue https://github.com/PNS-Conception/cookiefactory-22-23-n/issues/12

**As a** customer,
**I want** to know how long it is to cook a given order
**so that** I can plan accordingly what and when to order.

**As a** cook,
**I want** to know how long to cook a given cookie
**so that** I can cook correctly in the given time.

_**Cucumber.feature:**_ Order.feature

_**Scenario:**_ The order had time to be cooked



## Sprint 3

### modification des stocks

Issue https://github.com/PNS-Conception/cookiefactory-22-23-n/issues/7

**As a** store owner,
**I want** to add ingredients to the stock
**so that** I can increase the amount of cookies possible.

**As a** store owner,
**I want** to remove ingredients to the stock
**so that** I can no longer offer receit containg this ingredient.

_**Cucumber.feature:**_ Stock.feature

_**Scenario:**_ The stock contains the ingredient to be removed

_**Scenario:**_ The stock contains the ingredient to be removed but not enough

_**Scenario:**_ The stock does not contain the ingredient to be removed

_**Scenario:**_ The stock does contain the ingredient to be added

_**Scenario:**_ The stock does not contain the ingredient to be added



### Modifier les horraires d'ouverture

Issue https://github.com/PNS-Conception/cookiefactory-22-23-n/issues/15

**As a** store owner,
**I want** to change the store's opening time
**so that** I can accept orders in thoses time widows.

_**Cucumber.feature:**_ StoreOwnerAccount.feature

_**Scenario:**_ The store's opening hour is after the new opening hour

_**Scenario:**_ The store's opening hour is before the new opening hour

_**Scenario:**_ The store's closing hour is after the new closing hour

_**Scenario:**_ The store's closing hour is before the new closing hour



### Loyalty programm

Issue https://github.com/PNS-Conception/cookiefactory-22-23-n/issues/16

**As a** customer,
**I want** to subscribe to the Loyalty Program
**so that** I can have discounts.

_**Cucumber.feature:**_ UserAccount.feature

_**Scenario:**_ subscribing correctly to VIP

_**Scenario:**_ not subscribing correctly to VIP

_**Scenario:**_ re-subscribing to VIP

_**Scenario:**_ checking price's reduction


### Annuler commande

Issue https://github.com/PNS-Conception/cookiefactory-22-23-n/issues/17

**As a** customer,
**I want** to be able to cancel orders 
**so that** I can change my mind if there's is a last minute change in my plans.

_**Cucumber.feature:**_ UserAccount.feature

_**Scenario:**_ The customer cancels a paid order

_**Scenario:**_ The customer cancels an order being prepared

_**Scenario:**_ The customer cancels a second order in 8 minutes


### Creation et suppression de recette

Issue https://github.com/PNS-Conception/cookiefactory-22-23-n/issues/18

**As a** brand's cook
**I want** to add recipes to the catalog
**so that** I can add a range of cookies.

_**Cucumber.feature:**_ Recipe.feature

_**Scenario:**_ adding a recipe to the catalog

_**Scenario:**_  not adding a recipe to the catalog

_**Scenario:**_ deleting a recipe off the catalog


## Sprint 4

### Party cookies

Issue https://github.com/PNS-Conception/cookiefactory-22-23-n/issues/19

**As a** a customer, **I want** to order XL cookies for special events, **so that** I can have amazing big cookies.

_**Cucumber.feature:**_ PartyCookie.feature

_**Scenario:**_ checking cookie's price

_**Scenario:**_ adding party cookies to cart when the store allows it

_**Scenario:**_ adding party cookies to cart when the store doesn't allows it


### Cook scheduler

Issue https://github.com/PNS-Conception/cookiefactory-22-23-n/issues/20

**As a** a cook
**I want** to have a schedukle
**so that** I can work properly.

_**Cucumber.feature:**_ CookScheduling.feature

_**Scenario:**_ A cook receives an order

_**Scenario:**_ A store had no cook available

_**Scenario:**_ A cook is available and another isn't

   
   
