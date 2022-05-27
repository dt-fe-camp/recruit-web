#!/bin/bash

if ([ -z $1 ] || [ -z $2 ] || [ -z $3 ]) then
  printf "%s" "请输入参数: action appDir"
fi;

# application jar's name
appDir=$2
secret=$3
application=$(find $appDir -type f -name "*.jar")

#if [ -z $application ];then
#	application=`ls -t |grep .jar$ |head -n1`
#fi

#Java runtime option
#JAVA_OPTIONS="-XX:+UseG1GC -XX:+HeapDumpOnOutOfMemoryError -Xms512M -Xmx4G"

## determine existence of processus of application
isExist() {
  ## check pid
  pid=$(ps -ef | grep ${application} | grep -v grep | awk '{print $2}')
  ## if pid not exist return 0 else 1
  if [ -z "${pid}" ]; then
    return 0
  else
    return 1
  fi
}

log=$appDir/application.log

## start application in background and generate appDir/application.log
function start() {
  ## deermine existence of processus
  isExist
  ## if not running
  if [ $? -eq "0" ]; then
    echo "Your ${application} is running, please check it..."
    #nohup java -jar $JAVA_OPTIONS ./$application  > $log 2>&1 &
    nohup java -jar $application --jasypt.encryptor.password="$secret" >$log 2>&1 &
    echo "${application} startup success"
  else
    echo "${application} is running, pid=${pid} "
  fi
}

function startx() {
  isExist
  if [ $? -eq "0" ]; then
    echo "Your ${application} is running, please check it..."
    java -jar $JAVA_OPTIONS ./$application --jasypt.encryptor.password="$secret"
    echo "${application} startup success"
  else
    echo "${application} is running, pid=${pid} "
  fi
}

## stop the processus application
function stop() {
  isExist
  ## if not exist - ok
  if [ $? -eq "0" ]; then
    echo "${application} is not running"
  else
    echo "${application} is running, pid=${pid}, prepare kill it "
    # if exist - kill the processus
    kill -9 ${pid}
    echo "${application} has been successfully killed"
  fi
}

## check status
function status() {
  appPid=$(ps -ef | grep java | grep $application | awk '{print $2}')
  if [ -z $appPid ]; then
    echo -e "Not running "
  else
    echo -e "Running [$appPid] "
  fi
}

## restart
function restart() {
  stop
  start
}

## arg
case "$1" in
"start")
  start
  ;;
"startx")
  startx
  ;;
"stop")
  stop
  ;;
"restart")
  restart
  ;;
"status")
  status
  ;;
*)
  echo "please enter the correct commands: "
  echo "such as : sh application.sh [ start | stop | restart |status ]"
  ;;
esac
