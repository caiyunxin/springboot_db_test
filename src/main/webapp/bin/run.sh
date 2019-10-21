#!/bin/bash
# @Author: caiyunxin@pinglian.hk
# @Date: 2019/10/17
# Usage: sh run.sh start|stop
#进入当前脚本的目录，$0:当前shell脚本的文件名，dirname $0:获取当前shell的路径
cd $(dirname $0)
#定义赋值变量BIN_DIR,$(pwd):当前所在全路径
export BIN_DIR=$(pwd)
cd $BIN_DIR
cd..
DEPLOY_DIR=`pwd`
PROJECT_NAME="springBootDemo-0.0.1-SNAPSHOT"
APPLICATION_JAR = ${DEPLOY_DIR}/${PROJECT_NAME}.jar

echo "DEPLOY_DIR... :"$DEPLOY_DIR 
if [ -f $APPLICATION_JAR ]; then
	echo "APPLICATION_JAR... :"$APPLICATION_JAR 
fi

# loading system environment
if [ -f $BIN_DIR/java_env.sh ]; then
	source $BIN_DIR/java_env.sh
fi

RUNJAVA="$JAVA_HOME/bin/java"
#判断当前应用程序是否已经执行0：未执行，1：执行
function is_process_exist() {
    local pid=$(ps axuf | grep -Pi "${RUNJAVA}.*${DEPLOY_DIR}" | grep -v grep | awk '{print $2}')

    if [ "$pid"x = x ]; then
        return 0
    else
        return 1
    fi
}

function start_service(){
	nohup ${RUNJAVA} -jar ${APPLICATION_JAR} &
}

#启动条件判断
function start() {
	echo "Starting..."
	is_process_exist
	if [ $? -eq 1 ]; then
		echo "already started!"
		exit 0;
	fi
	start_service
	
}

function stop_service(){
	PROJECT_PID=$(ps axuf | grep -Pi "${RUNJAVA}.*${DEPLOY_DIR}" | grep -v grep | awk '{print $2}')
	 kill -9 $PROJECT_PID
}

#停止条件判断
function stop(){
	echo "Stoping..."
	is_process_exist
	if [ $? -eq 0 ]; then
		echo "already stoped!"
        return
    fi
	stop_service
}
#重启程序
function restart(){
	local pid=$(ps axuf | grep -Pi "${RUNJAVA}.*${DEPLOY_DIR}" | grep -v grep | awk '{print $2}')
	if [ "$pid"x != x ]; then
		# thread dump
		kill -9 $pid
	fi
	start_service
}

case $1 in
start)
    start;;
stop)
    stop;;
restart)
    restart;;
*)
    echo "Usage: sh $0 {start|stop|restart}"
    exit 1
    ;;
esac