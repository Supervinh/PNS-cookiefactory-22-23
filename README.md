# Cookiefactory-22-23-Team-#template
_Template for classroom SI4-COO_

## doc
Contient le rapport final

## .github
   1. Contient sous workflows/maven.yml, une version d'un fichier d'actions qui est déclenché dès que vous poussez du code. 
Sur cette version initiale seule un test Junit5 est déclenché pour vérifier que tout fonctionne.
       - Github Actions (See in .github/workflows) to simply make a maven+test compilation
  2. Contient sous ISSUE_TEMPLATE, les modèles pour les issues user_story et bug. Vous pouvez le compléter à votre guise.

## src
 - pom.xml : 
       - Cucumber 7 et JUnit 5
       - Maven compatible
       - JDK 17



## User stories 
La liste des fonctionnalités livrées par user story.

## Sprint 1

Passer commander

Récupérer commande

## Sprint 2

## Sprint 3

## Loyalty programm

Issue #16

**As a** customer,
**I want** to subscribe to the Loyalty Program
**so that** I can have discounts.

_**Scenario:**_ subscribing correctly to VIP

_**Scenario:**_ not subscribing correctly to VIP

_**Scenario:**_ checking price's reduction


## Annuler commande

Issue #17

**As a** customer,
**I want** to be able to cancel orders 
**so that** I can change my mind if there's is a last minute change in my plans.

_**Scenario:**_ The customer cancels a paid order

_**Scenario:**_ The customer cancels an order being prepared

_**Scenario:**_ The customer cancels a second order in 8 minutes


## Creation et suppression de recette

Issue #18

**As a** brand's cook
**I want** to add recipes to the catalog
**so that** I can add a range of cookies.

_**Scenario:**_ adding a recipe to the catalog

_**Scenario:**_  not adding a recipe to the catalog

_**Scenario:**_ The stock does not  contain the ingredient to be removed

## Modifier les horraires d'ouverture

Issue #15

**As a** store owner,
**I want** to change the store's opening time
**so that** I can accept orders in thoses time widows.

_**Scenario:**_ The store's opening hour is after the new opening hour

_**Scenario:**_ The store's opening hour is before the new opening hour

_**Scenario:**_ The store's closing hour is after the new closing hour

_**Scenario:**_ The store's closing hour is before the new closing hour

## Sprint 4

## Cook scheduler

Issue #20

**As a** a cook
**I want** to have a schedukle
**so that** I can work properly.

_**Scenario:**_ A cook receives an order

_**Scenario:**_A store had no cook available

_**Scenario:**_ A cook is available and another isn't



## Party cookies

Issue #19

**As a** a customer, **I want** to order XL cookies for special events, **so that** I can have amazing big cookies.

_**Scenario:**_ checking cookie's price

_**Scenario:**_ adding party cookies to cart when the store allows it

_**Scenario:**_ adding party cookies to cart when the store doesn't allows it



### Milestone X

Chaque user story doit être décrite par 
   - son identifiant en tant que issue github (#), 
   - sa forme classique (As a… I want to… In order to…) (pour faciliter la lecture)
   - Le nom du fichier feature Cucumber et le nom des scénarios qui servent de tests d’acceptation pour la story.
   Les contenus détaillés sont dans l'issue elle-même.
   
   
