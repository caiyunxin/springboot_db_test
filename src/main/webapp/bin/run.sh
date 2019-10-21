#!/bin/bash

cd $(dirname $0)
export BIN_DIR=$(pwd)
cd $BIN_DIR
cd ..
DEPLOY_DIR=`pwd`
LOG_DIR=$DEPLOY_DIR/logs
PROJECT_NAME="springBootDemo-0.0.1-SNAPSHOT"
PROJECT_JAR=$DEPLOY_DIR"/"$PROJECT_NAME".jar"
STDOUT_FILE=$LOG_DIR/springBootDemo.out
if [ -f $BIN_DIR/java_env.sh ]; then
	source $BIN_DIR/java_env.sh
else
	echo "failed cause not exist java environment file"
	return
fi

if [ ! -e $PROJECT_JAR ]; then
	echo "file not exost or filename is wrong"
	exit 0;
fi
RUNJAVA="$JAVA_HOME/bin/java"
EXEC_PID_FILE="$LOG_DIR/instance.pid"
#程序是否运行，0：未运行，1：运行
function is_process_exist(){
	local pid=$(ps axuf | grep -Pi "${RUNJAVA}.*${DEPLOY_DIR}" | grep -v grep | awk '{print $2}')
	if [ "$pid"x = x ]; then
		return 0
	else
        	return 1
    	fi
}
#文件重命名
function filemove(){
	if [ -f "$1" ]; then
		mv $1 $1.`date +%Y%m%d.%H%M`
	fi
}

function start_service(){
	filemove $STDOUT_FILE
	nohup $RUNJAVA "-jar" $PROJECT_JAR >> $STDOUT_FILE 2>&1 &	
	echo -n $! > $EXEC_PID_FILE
}

function start(){
	echo "Starting..."
	local sleep_time=5;
	local retry_time=0;
	local TOTAL_RETRY_TIME=5;

	is_process_exist
	if [ $? -eq 1 ]; then
		echo "already started!"
		exit 0;
	fi
	
	start_service
	while (( $retry_time < $TOTAL_RETRY_TIME ))
	do
		sleep $sleep_time
		is_process_exist
		if [ $? -ne 1 ]; then
			(( retry_time += 1 ))
			echo -ne "."
		else
			break
		fi	
	done
	echo
	
	if [ $retry_time -ge $TOTAL_RETRY_TIME ]; then
		echo "Error: process stop, failed!"
		exit 1
	else
		echo "Info: process start."
	fi
	#打印输出
}
#停止程序
function stop_service(){
	if [ -f "$EXEC_PID_FILE" ]; then
		EXEC_PID=$(cat $EXEC_PID_FILE)
	else
		EXEC_PID=$(ps axuf | grep ${DEPLOY_DIR} | grep -v grep | awk '{print $2}')
	fi
	kill $EXEC_PID > $LOG_DIR/kills 2>&1
	COUNT=`ps --no-heading -C java -f --width 2000 | grep "$DEPLOY_DIR" | awk '{print $2}' | wc -l`
	if [ $COUNT -gt 0 ]; then
		sleep 5
		kill -9 $EXEC_PID > $LOG_DIR/kills 2>&1
	fi
	rm -f $EXEC_PID_FILE

}

function stop(){
	echo "Stoping..."
	is_process_exist
	if [ $? -eq 0 ]; then
		echo "already stoped!"
		return
	fi
	stop_service
	if [ $? -eq 0 ]; then
		echo "Info: process stop!"
	fi
}

#
function restart(){
	echo "Info: process restarting..."
	local pid=$(ps axuf | grep ${DEPLOY_DIR} | grep -v grep | awk '{print $2}')
	if [ "$pid"x != x ]; then
		stop_service
		if [ $? -ne 0 ]; then
			echo "Info: process restart Error!"
			return
		fi
	fi
	start_service
	if [ $? -eq 0 ]; then
		echo "Info: process restarted!"
	fi
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
