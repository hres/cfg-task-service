# Canada Food Guide Task Service

This project provides the CFG Task REST Services

## Components and Features

This project uses the following components and features:

* Java       8
* Maven      3.3.9
* Tomcat     8.0
* MongoDB    3.4.2 LTS
* PostgreSQL 9.5

These need be setup in order for the application/services to function on [Tomcat 8.0 on HRES]

---

## How to Install MongoDB 3.4.2 LTS

Go to [install-mongodb-on-ubuntu]

1. Import the public key used by the package management system
2. Create a list file for MongoDB for Ubuntu 16.04
3. Reload local package database
4. Install the MongoDB packages

Start MongoDB as a service rather than manually

Now that MongoDB is up-and-running, copy and rename [mongodb.properties.template] to mongodb.properties (cp mongdb.properties.template mongdb.properties).  Change the `host` and `port` properties in the mongodb.properties file in order to allow the Java API to connect to it.

---

## How to Install/Update PostgreSQL schema/data to the latest and greatest

1. On the command-line run `sudo apt-get install postgresql` to install PostgreSQL
2. On the command-line run `psql` and login to postgres and ensure you are **not** connected to the `cnfadm` database:
	1. `DROP DATABASE cnfadm;`
	2. `CREATE DATABASE cnfadm;`
	3. `\c cnfadm`
	4. `\i create_canada_food_guide_dataset.sql`
	5. `\i insert_canada_food_guide_dataset.sql`
3. Copy and rename [db.properties.template] to db.properties (cp db.properties.template db.properties
4. Change the relevant properties in the db.properties file order to establish connectivity from within Java.

---


## Maven Build and Deployment

To deploy the [cfg-task-services], do the following:

1. `cd ~/repositories`
2. `git clone https://github.com/hres/cfg-task-service.git`
3. `cd cfg-task-service`
4. `mvn clean install`
5. copy `target/cfg-task-service.war` to `webapps` directory of [Tomcat 8.0 on HRES]

Similarly for the [cfg-classification-services]:

1. `cd ~/repositories`
2. `git clone https://github.com/hres/cfg-classification-service.git`
3. `cd cfg-classification-service`
4. `mvn clean install`
5. copy `target/cfg-classification-service.war` to `webapps` directory of [Tomcat 8.0 on HRES]

## Confirm Service is Running and Connecting to PostgreSQL Database

Run [Test]

[//]: # (These are the references links used in the body of this note and get stripped out when the markdown processor does its thing.  There is no need to format nicely because it should not be seen.)

[install-mongodb-on-ubuntu]:        <https://docs.mongodb.com/manual/tutorial/install-mongodb-on-ubuntu/>
[cfg-task-services]:                <https://github.com/hres/cfg-task-service.git>
[cfg-classification-services]:      <https://github.com/hres/cfg-classification-service.git>
[Tomcat 8.0 on HRES]:               <https://java-dev.hres.ca>
[Test]:                             <https://java-dev.hres.ca/cfg-task-service/service/datasets/status>
[mongodb.properties.template]:      <https://github.com/hres/cfg-task-service/blob/master/src/main/java/ca/gc/ip346/util/mongodb.properties>
[db.properties.template]:           <https://github.com/hres/cfg-task-service/blob/master/src/main/java/ca/gc/ip346/util/db.properties.template>
