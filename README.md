# Canada Food Guide Task Service

This project provides the CFG Task REST Services.
<!-- The service will use Drools Decision Table to implement the classification rules at the classification rules step. -->

## Components and Features

This project uses the following components and features:

<!-- * Drools Decision Table (Excel) -->
<!-- * Drools Eclipse Plugin -->
<!-- * Drools Runtime 6.5 -->
* Java 8
* Maven 3.3.9
* Tomcat 8.0
* Eclipse Neon
* MongoDB 3.4.2 LTS
* JAX-RS REST Service with Jersey and Jackson consuming and producing XML and JSON.

These need be setup for development.
<!-- For runtime, see below. -->

<!-- ## How to Set up Eclipse Plugins -->

<!-- * Install GEF [http://download.eclipse.org/tools/gef/updates/releases/](http://download.eclipse.org/tools/gef/updates/releases/) -->
<!-- * Install Drools: [http://download.jboss.org/drools/release/6.5.0.Final/org.drools.updatesite/](http://download.jboss.org/drools/release/6.5.0.Final/org.drools.updatesite/) -->

## Maven Build and Deployment

For a standalone deployment, do the following:

1. `cd ~/repositories`
2. `git clone https://github.com/hres/cfg-task-service.git`
3. `cd cfg-task-service`
4. `mvn clean install`
5. copy `target/cfg-task-service.war` to `webapps` directory of Tomcat 8.0

## How to Install MongoDB 3.4.2 LTS

Go to <https://docs.mongodb.com/manual/tutorial/install-mongodb-on-ubuntu/>

1. Import the public key used by the package management system.
2. Create a list file for MongoDB for Ubuntu 16.04
3. Reload local package database.
4. Install the MongoDB packages.

Start MongoDB as a service rather than manually.

<!--

## How to Test Service

The service speaks XML and JSON:
* To `GET` JSON, set the request header `Accept` to `application/json`.
* To `POST` JSON input, set the request header `Content-Type` to `application/json`.
* To retrieve an array of sample food items: (from [http://localhost:8080/cfg-task-service](http://localhost:8080/cfg-task-service))
	* request with a criteria object:

	```json
	{
		"data-source"                          : "both",
		"food-recipe-name"                     : "blah blah blah",
		"food-recipe-code"                     : 12345,
		"commit-date-begin"                    : "2017-03-09",
		"commit-date-end"                      : "2017-09-03",
		"cnf-code"                             : 54321,
		"subgroup-code"                        : 9876,
		"cfg-tier"                             : 0,
		"recipe"                               : 2,
		"additives"                            : 1,
		"sodium"                               : 1,
		"sugar"                                : 1,
		"fat"                                  : 1,
		"transfat"                             : 1,
		"caffeine"                             : 1,
		"free-sugars"                          : 1,
		"sugar-substitutes"                    : 1,
		"reference-amount-missing"             : "on",
		"cfg-serving-missing"                  : "on",
		"tier-4-serving-missing"               : "on",
		"energy-value-missing"                 : "on",
		"cnf-code-missing"                     : "on",
		"recipe-rolled-up-down-missing"        : "on",
		"sodium-value-missing"                 : "on",
		"sugar-value-missing"                  : "on",
		"fat-value-missing"                    : "on",
		"transfat-value-missing"               : "on",
		"satfat-value-missing"                 : "on",
		"select-all-missing"                   : "on",
		"added-sodium-missing"                 : "on",
		"added-sugar-missing"                  : "on",
		"added-transfat-missing"               : "on",
		"added-caffeine-missing"               : "on",
		"added-free-sugars-missing"            : "on",
		"added-sugar-substitutes-missing"      : "on",
		"comments"                             : "blah blah blah",
		"commit-date-from"                     : "2017-03-09",
		"commit-date-to"                       : "2017-09-03",
		"reference-amount-last-updated"        : "on",
		"cfg-serving-last-updated"             : "on",
		"tier-4-serving-last-updated"          : "on",
		"energy-value-last-updated"            : "on",
		"cnf-code-last-updated"                : "on",
		"recipe-rolled-up-down-last-updated"   : "on",
		"sodium-value-last-updated"            : "on",
		"sugar-value-last-updated"             : "on",
		"fat-value-last-updated"               : "on",
		"transfat-value-last-updated"          : "on",
		"satfat-value-last-updated"            : "on",
		"select-all-last-updated"              : "on",
		"added-sodium-last-updated"            : "on",
		"added-sugar-last-updated"             : "on",
		"added-transfat-last-updated"          : "on",
		"added-caffeine-last-updated"          : "on",
		"added-free-sugars-last-updated"       : "on",
		"added-sugar-substitutes-last-updated" : "on"
	}
	```

	* response results in food item objects:

	```json
	{
		"type"                               : "CNF",
		"code"                               : "2",
		"name"                               : "Cheese souffle",
		"cnfCode"                            : "22",
		"cfgCode"                            : "8000",
		"cfgCodeCommitDate"                  : null,
		"energyKcal"                         : 0.0,
		"sodiumAmountPer100g"                : 0.0,
		"sodiumImputationReference"          : null,
		"sodiumImputationDate"               : null,
		"sugarAmountPer100g"                 : 0.0,
		"sugarImputationReference"           : null,
		"sugarImputationDate"                : null,
		"transfatAmountPer100g"              : 0.0,
		"transfatImputationReference"        : null,
		"transfatImputationDate"             : null,
		"satfatAmoutPer100g"                 : 0.0,
		"satfatImputationReference"          : null,
		"satfatImputationDate"               : null,
		"totalfatAmoutPer100g"               : 0.0,
		"totalfatImputationReference"        : null,
		"totalfatImputationDate"             : null,
		"containsAddedSodium"                : true,
		"containsAddedSodiumCommitDate"      : null,
		"containsAddedSugar"                 : true,
		"containsAddedSugarCommitDate"       : null,
		"containsFreeSugars"                 : true,
		"containsFreeSugarsCommitDate"       : null,
		"containsAddedFat"                   : true,
		"containsAddedFatCommitDate"         : null,
		"containsAddedTransfat"              : true,
		"containsAddedTransfatCommitDate"    : null,
		"containsCaffeine"                   : true,
		"containsCaffeineCommitDate"         : null,
		"containsSugarSubstitutes"           : true,
		"containsSugarSubstitutesCommitDate" : null,
		"referenceAmountG"                   : 100.38,
		"referenceAmountMeasure"             : "250ml",
		"referenceAmountCommitDate"          : null,
		"foodGuideServingG"                  : 0.0,
		"foodGuideServingMeasure"            : "no serving specified",
		"foodGuideCommitDate"                : null,
		"tier4ServingG"                      : 0.0,
		"tier4ServingMeasure"                : "0",
		"tier4ServingCommitDate"             : null,
		"rolledUp"                           : true,
		"rolledUpCommitDate"                 : null,
		"applySmallRaAdjustment"             : true,
		"replacementCode"                    : null,
		"comments"                           : null
	}, ...
	```

	```json
	{
		"type"                               : "CNF",
		"code"                               : "2",
		"name"                               : "Cheese souffle",
		"cnfCode"                            : "22",
		"cfgCode"                            : "8000",
		"cfgCodeCommitDate"                  : null,
		"energyKcal"                         : 0.0,
		"sodiumAmountPer100g"                : 0.0,
		"sodiumImputationReference"          : null,
		"sodiumImputationDate"               : null,
		"sugarAmountPer100g"                 : 0.0,
		"sugarImputationReference"           : null,
		"sugarImputationDate"                : null,
		"transfatAmountPer100g"              : 0.0,
		"transfatImputationReference"        : null,
		"transfatImputationDate"             : null,
		"satfatAmoutPer100g"                 : 0.0,
		"satfatImputationReference"          : null,
		"satfatImputationDate"               : null,
		"totalfatAmoutPer100g"               : 0.0,
		"totalfatImputationReference"        : null,
		"totalfatImputationDate"             : null,
		"containsAddedSodium"                : true,
		"containsAddedSodiumCommitDate"      : null,
		"containsAddedSugar"                 : true,
		"containsAddedSugarCommitDate"       : null,
		"containsFreeSugars"                 : true,
		"containsFreeSugarsCommitDate"       : null,
		"containsAddedFat"                   : true,
		"containsAddedFatCommitDate"         : null,
		"containsAddedTransfat"              : true,
		"containsAddedTransfatCommitDate"    : null,
		"containsCaffeine"                   : true,
		"containsCaffeineCommitDate"         : null,
		"containsSugarSubstitutes"           : true,
		"containsSugarSubstitutesCommitDate" : null,
		"referenceAmountG"                   : 100.38,
		"referenceAmountMeasure"             : "250ml",
		"referenceAmountCommitDate"          : null,
		"foodGuideServingG"                  : 0.0,
		"foodGuideServingMeasure"            : "no serving specified",
		"foodGuideCommitDate"                : null,
		"tier4ServingG"                      : 0.0,
		"tier4ServingMeasure"                : "0",
		"tier4ServingCommitDate"             : null,
		"rolledUp"                           : true,
		"rolledUpCommitDate"                 : null,
		"applySmallRaAdjustment"             : true,
		"replacementCode"                    : null,
		"comments"                           : null
	}, ...
	```

---

-->

<!-- * Set Food Flags -->

<!-- Use the sample array of food items to issue a POST request to URL: [http://localhost:8080/food-classification-service-poc/service/flags](http://localhost:8080/food-classification-service-poc/service/flags) -->
<!-- Set the request body to the sample array.  The returned sample food array should have some food flags set such as 'SugarAdded': true. -->


<!-- * Adjust Food Tiers -->

<!-- Use the returned array of food items from the flags step to issue a POST request to URL: [http://localhost:8080/food-classification-service-poc/service/tier-adjustments](http://localhost:8080/food-classification-service-poc/service/tier-adjustments). -->
<!-- Set the request body to the sample array.  The returned sample food array should have food tiers adjusted. -->


