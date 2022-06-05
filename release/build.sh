#!/bin/bash

CURRENT_DIR="$(dirname "$(realpath $0)")";
cd ${CURRENT_DIR}/../

ROOT_DIR=$(pwd);
printf "%s\n" $ROOT_DIR;

SERVER_BUILDER="${ROOT_DIR}/build-server.sh";
printf "%s" $SERVER_BUILDER;

# static
cd "${ROOT_DIR}/static";
yarn build;

# server
cd "${ROOT_DIR}/server";
mvn clean package -Dmaven.test.skip=true;

# 必须同一级
cp -f $ROOT_DIR/release/server-manager.sh ./target
