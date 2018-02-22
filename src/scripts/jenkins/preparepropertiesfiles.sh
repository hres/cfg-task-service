#!/bin/bash
printerbanner exec
cp src/main/java/ca/gc/ip346/util/mongodb.properties.template src/main/java/ca/gc/ip346/util/mongodb.properties
cp src/main/java/ca/gc/ip346/util/db.properties.template      src/main/java/ca/gc/ip346/util/db.properties
sed -i 's/username=root/username=cfg_db_user/'                src/main/java/ca/gc/ip346/util/db.properties
