# Food Classification Service

This project provides the Food Classification REST Services.
The service uses Drools Decision Table to implement the classification rules.

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

## How to Set up Eclipse Plugins

* Install GEF [http://download.eclipse.org/tools/gef/updates/releases/](http://download.eclipse.org/tools/gef/updates/releases/)
* Install Drools: [http://download.jboss.org/drools/release/6.5.0.Final/org.drools.updatesite/](http://download.jboss.org/drools/release/6.5.0.Final/org.drools.updatesite/)

## Maven Build and Deployment

1. mvn clean install
2. rename target/*.war to food-classification-service-poc.war
3. move food-classification-service-poc.war to webapps directory of tomcat 8

## How to Test Service

The service speaks XML and JSON.  To get JSON, set the request header `Accept` to `application/json`.  To post JSON input, set the request header `Content-Type` to `application/json`.

---

* Retrieve an array of sample food items from [http://localhost:8080/food-classification-service-poc/service/flags](http://localhost:8080/food-classification-service-poc/service/flags)

<pre>
    &lt;?xml version="1.0" encoding="UTF-8" standalone="yes"?&gt;
    &lt;tieredFoods&gt;
        &lt;tieredFood&gt;
            &lt;adjustedTier&gt;1&lt;/adjustedTier&gt;      &lt;exclusion1&gt;&lt;/exclusion1&gt;
            &lt;fatAdded&gt;false&lt;/fatAdded&gt;          &lt;label&gt;dried, sweetened&lt;/label&gt;
            &lt;lowFat&gt;false&lt;/lowFat&gt;              &lt;lowSugar&gt;false&lt;/lowSugar&gt;
            &lt;name&gt;Crystalized Pineapple&lt;/name&gt;  &lt;subGroup&gt;112&lt;/subGroup&gt;
            &lt;sugarAdded&gt;true&lt;/sugarAdded&gt;       &lt;tsatTier&gt;1&lt;/tsatTier&gt;
        &lt;/tieredFood&gt;
        &lt;tieredFood&gt;
            &lt;adjustedTier&gt;1&lt;/adjustedTier&gt;      &lt;exclusion1&gt;&lt;/exclusion1&gt;
            &lt;fatAdded&gt;false&lt;/fatAdded&gt;          &lt;label&gt;canned, sweetened&lt;/label&gt;
            &lt;lowFat&gt;false&lt;/lowFat&gt;              &lt;lowSugar&gt;false&lt;/lowSugar&gt;
            &lt;name&gt;Canned Peach&lt;/name&gt;           &lt;subGroup&gt;112&lt;/subGroup&gt;
            &lt;sugarAdded&gt;true&lt;/sugarAdded&gt;       &lt;tsatTier&gt;1&lt;/tsatTier&gt;
        &lt;/tieredFood&gt;
        &lt;tieredFood&gt;
            &lt;adjustedTier&gt;1&lt;/adjustedTier&gt;      &lt;exclusion1&gt;&lt;/exclusion1&gt;
            &lt;fatAdded&gt;false&lt;/fatAdded&gt;          &lt;label&gt;canned, juice pack&lt;/label&gt;
            &lt;lowFat&gt;false&lt;/lowFat&gt;              &lt;lowSugar&gt;false&lt;/lowSugar&gt;
            &lt;name&gt;Canned Orange Juice&lt;/name&gt;    &lt;subGroup&gt;112&lt;/subGroup&gt;
            &lt;sugarAdded&gt;false&lt;/sugarAdded&gt;      &lt;tsatTier&gt;1&lt;/tsatTier&gt;
        &lt;/tieredFood&gt;
        &lt;tieredFood&gt;
            &lt;adjustedTier&gt;1&lt;/adjustedTier&gt;      &lt;exclusion1&gt;&lt;/exclusion1&gt;
            &lt;fatAdded&gt;false&lt;/fatAdded&gt;          &lt;label&gt;canned, heavy syrup&lt;/label&gt;
            &lt;lowFat&gt;false&lt;/lowFat&gt;              &lt;lowSugar&gt;false&lt;/lowSugar&gt;
            &lt;name&gt;Heavy Syrup Pack&lt;/name&gt;       &lt;subGroup&gt;112&lt;/subGroup&gt;
            &lt;sugarAdded&gt;true&lt;/sugarAdded&gt;       &lt;tsatTier&gt;1&lt;/tsatTier&gt;
        &lt;/tieredFood&gt;
    &lt;/tieredFoods&gt;
</pre>

---

* Set Food Flags

Use the sample array of food items to issue a POST request to URL: [http://localhost:8080/food-classification-service-poc/service/flags](http://localhost:8080/food-classification-service-poc/service/flags)
Set the request body to the sample array.  The returned sample food array should have some food flags set such as 'SugarAdded': true.


* Adjust Food Tiers

Use the returned array of food items from the flags step to issue a POST request to URL: [http://localhost:8080/food-classification-service-poc/service/tier-adjustments](http://localhost:8080/food-classification-service-poc/service/tier-adjustments).
Set the request body to the sample array.  The returned sample food array should have food tiers adjusted.

