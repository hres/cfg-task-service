# Canada Food Guide Task Service

This project provides the CFG Task REST Services.
The service will use Drools Decision Table to implement the classification rules at the classification rules step.

## Components and Features

This project uses the following components and features:

* Drools Decision Table (Excel)
* Java 8
* Maven 3.3.9
* Tomcat 8.0
* Eclipse Neon
* Drools Eclipse Plugin
* Drools Runtime 6.5
* MongoDB 3.4.2 LTS
* JAX-RS REST Service with Jersey and Jackson consuming and producing XML and JSON.

<!-- ## How to Set up Eclipse Plugins -->

<!-- * Install GEF [http://download.eclipse.org/tools/gef/updates/releases/](http://download.eclipse.org/tools/gef/updates/releases/) -->
<!-- * Install Drools: [http://download.jboss.org/drools/release/6.5.0.Final/org.drools.updatesite/](http://download.jboss.org/drools/release/6.5.0.Final/org.drools.updatesite/) -->

## Maven Build and Deployment

1. mvn clean install
2. rename target/*.war to cfg-task-service.war
3. move cfg-task-service.war to webapps directory of tomcat 8

## How to Test Service

The service speaks XML and JSON:
* To get JSON, set the request header `Accept` to `application/json`.
* To post JSON input, set the request header `Content-Type` to `application/json`.

---

* Retrieve an array of sample food items from [http://localhost:8080/cfg-task-service/service/foods](http://localhost:8080/cfg-task-service/service/foods)

```json
{
  "foodRecipeType" : true,
  "code" : "1712",
  "name" : "Fruit salad (peach, pear, apricot, pineapple, cherry), canned, juice pack, solids and liquid",
  "cnfCode" : "9",
  "cfgCode" : "9",
  "cfgCodeCommitDate" : null,
  "energyKcal" : 0.0,
  "sodiumAmountPer100g" : 0.0,
  "sodiumImputationReference" : null,
  "sodiumImputationDate" : null,
  "sugarAmountPer100g" : 0.0,
  "sugarImputationReference" : null,
  "sugarImputationDate" : null,
  "transfatAmountPer100g" : 0.0,
  "transfatImputationReference" : null,
  "transfatImputationDate" : null,
  "satfatAmoutPer100g" : 0.0,
  "satfatImputationReference" : null,
  "satfatImputationDate" : null,
  "totalfatAmoutPer100g" : 0.0,
  "totalfatImputationReference" : null,
  "totalfatImputationDate" : null,
  "containsAddedSodium" : true,
  "containsAddedSodiumCommitDate" : null,
  "containsAddedSugar" : true,
  "containsAddedSugarCommitDate" : null,

  ...

```

---

```json

  ...

  "containsFreeSugars" : true,
  "containsFreeSugarsCommitDate" : null,
  "containsAddedFat" : true,
  "containsAddedFatCommitDate" : null,
  "containsAddedTransfat" : true,
  "containsAddedTransfatCommitDate" : null,
  "containsCaffeine" : true,
  "containsCaffeineCommitDate" : null,
  "containsSugarSubstitutes" : true,
  "containsSugarSubstitutesCommitDate" : null,
  "referenceAmountG" : 0.0,
  "referenceAmountMeasure" : null,
  "referenceAmountCommitDate" : null,
  "foodGuideServingG" : 0.0,
  "foodGuideServingMeasure" : null,
  "foodGuideCommitDate" : null,
  "tier4ServingG" : 0.0,
  "tier4ServingMeasure" : null,
  "tier4ServingCommitDate" : null,
  "rolledUp" : true,
  "rolledUpCommitDate" : null,
  "applySmallRaAdjustment" : true,
  "comments" : null
}, ...
```

---

<!-- * Set Food Flags -->

<!-- Use the sample array of food items to issue a POST request to URL: [http://localhost:8080/food-classification-service-poc/service/flags](http://localhost:8080/food-classification-service-poc/service/flags) -->
<!-- Set the request body to the sample array.  The returned sample food array should have some food flags set such as 'SugarAdded': true. -->


<!-- * Adjust Food Tiers -->

<!-- Use the returned array of food items from the flags step to issue a POST request to URL: [http://localhost:8080/food-classification-service-poc/service/tier-adjustments](http://localhost:8080/food-classification-service-poc/service/tier-adjustments). -->
<!-- Set the request body to the sample array.  The returned sample food array should have food tiers adjusted. -->


