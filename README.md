# Cookiefactory-22-23-Team-N

# doc
Contient le rapport final

# .github
   1. Contient sous workflows/maven.yml, une version d'un fichier d'actions qui est déclenché dès que vous poussez du code. 
Sur cette version initiale seule un test Junit5 est déclenché pour vérifier que tout fonctionne.
       - Github Actions (See in .github/workflows) to simply make a maven+test compilation
  2. Contient sous ISSUE_TEMPLATE, les modèles pour les issues user_story et bug. Vous pouvez le compléter à votre guise.

# src
 - pom.xml : 
       - Cucumber 7 et JUnit 5
       - Maven compatible
       - JDK 17


# User stories 
La liste des fonctionnalités livrées par user story.

## Sprint 1

### Passer commander

Issue https://github.com/PNS-Conception/cookiefactory-22-23-n/issues/2

**As a** customer,
**I want** to confirm my order
**so that** It can be prepared.

_**Scenario:**_ Cart is empty

_**Scenario:**_ Cart is not empty


### Récupérer commande

Issue https://github.com/PNS-Conception/cookiefactory-22-23-n/issues/4

**As a** customer,
**I want** to retrieve my order as it is ready
**so that** I can eat my cookies.



_**Scenario:**_ The order is ready

_**Scenario:**_ The order is not ready


## Sprint 2

## Sprint 3

### Modifier les horraires d'ouverture

Issue https://github.com/PNS-Conception/cookiefactory-22-23-n/issues/15

**As a** store owner,
**I want** to change the store's opening time
**so that** I can accept orders in thoses time widows.



_**Scenario:**_ The store's opening hour is after the new opening hour

_**Scenario:**_ The store's opening hour is before the new opening hour

_**Scenario:**_ The store's closing hour is after the new closing hour

_**Scenario:**_ The store's closing hour is before the new closing hour

_**Cucomber.feature:**_ StoreOwnerAccount.feature

### Loyalty programm

Issue https://github.com/PNS-Conception/cookiefactory-22-23-n/issues/16

**As a** customer,
**I want** to subscribe to the Loyalty Program
**so that** I can have discounts.

_**Cucomber.feature:**_ UserAccount.feature

_**Scenario:**_ subscribing correctly to VIP

_**Scenario:**_ not subscribing correctly to VIP

_**Scenario:**_ re-subscribing to VIP

_**Scenario:**_ checking price's reduction


### Annuler commande

Issue https://github.com/PNS-Conception/cookiefactory-22-23-n/issues/17

**As a** customer,
**I want** to be able to cancel orders 
**so that** I can change my mind if there's is a last minute change in my plans.

_**Cucomber.feature:**_ UserAccount.feature

_**Scenario:**_ The customer cancels a paid order

_**Scenario:**_ The customer cancels an order being prepared

_**Scenario:**_ The customer cancels a second order in 8 minutes


### Creation et suppression de recette

Issue https://github.com/PNS-Conception/cookiefactory-22-23-n/issues/18

**As a** brand's cook
**I want** to add recipes to the catalog
**so that** I can add a range of cookies.

_**Cucomber.feature:**_ Recipe.feature

_**Scenario:**_ adding a recipe to the catalog

_**Scenario:**_  not adding a recipe to the catalog

_**Scenario:**_ deleting a recipe off the catalog


## Sprint 4

### Party cookies

Issue https://github.com/PNS-Conception/cookiefactory-22-23-n/issues/19

**As a** a customer, **I want** to order XL cookies for special events, **so that** I can have amazing big cookies.

_**Cucomber.feature:**_ PartyCookie.feature

_**Scenario:**_ checking cookie's price

_**Scenario:**_ adding party cookies to cart when the store allows it

_**Scenario:**_ adding party cookies to cart when the store doesn't allows it


### Cook scheduler

Issue https://github.com/PNS-Conception/cookiefactory-22-23-n/issues/20

**As a** a cook
**I want** to have a schedukle
**so that** I can work properly.

_**Cucomber.feature:**_ CookScheduling.feature

_**Scenario:**_ A cook receives an order

_**Scenario:**_A store had no cook available

_**Scenario:**_ A cook is available and another isn't




### Milestone X

Chaque user story doit être décrite par 
   - son identifiant en tant que issue github (#), 
   - sa forme classique (As a… I want to… In order to…) (pour faciliter la lecture)
   - Le nom du fichier feature Cucumber et le nom des scénarios qui servent de tests d’acceptation pour la story.
   Les contenus détaillés sont dans l'issue elle-même.
   
   
