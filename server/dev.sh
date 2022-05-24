#!/bin/bash

PWD="$1"

if [ -z "$PWD" ]
then
  printf "%s\n" "秘钥为空";
  exit 1;
fi;

mvn spring-boot:run -Dspring-boot.run.arguments=--jasypt.encryptor.password="$1"
