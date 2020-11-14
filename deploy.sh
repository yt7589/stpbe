# v0.0.1
#!/bin/bash

HOME_DIR="/home/ps/www/dev/vehicle/jar/";
API_JAR=$2;
API_LOG_NAME=$3;

function start()
{
    count=`ps -ef |grep java|grep $API_JAR|grep -v grep|wc -l`
    if [ $count != 0 ];then
        echo "$API_JAR is running..."
    else
        echo "start $API_JAR ..."
        nohup java -jar -Dspring.profiles.active=prod $HOME_DIR$API_JAR > $HOME_DIR$API_LOG_NAME 2>&1 &
	      echo "start $API_JAR success..."
    fi
}

function stop()
{
    boot_id=`ps -ef |grep java|grep $API_JAR|grep -v grep|awk '{print $2}'`
    count=`ps -ef |grep java|grep $API_JAR|grep -v grep|wc -l`
    if [ $count != 0 ];then
        echo "stop $API_JAR..."
        kill -9 $boot_id
        echo "stop $API_JAR success..."
    fi
}

function restart()
{
    stop
    sleep 2
    start
}

function status()
{
    count=`ps -ef |grep java|grep $API_JAR|grep -v grep|wc -l`
    if [ $count != 0 ];then
        echo "$API_JAR is running..."
    else
        echo "$API_JAR is not running..."
    fi
}

case $1 in
    start)
    start;;
    stop)
    stop;;
    restart)
    restart;;
    status)
    status;;
    *)
    echo -e "\033[0;31m Usage: \033[0m  \033[0;34m sh  $0  {start|stop|restart|status}  {SpringBootJarName} \033[0m
\033[0;31m Example: \033[0m
      \033[0;33m sh  $0  start test.jar test.log \033[0m"
esac