#!/bin/bash

# 项目名称
SERVER_NAME="${project.artifactId}"

# JAR名称
JAR_NAME="${project.build.finalName}.jar"

# 进入bin目录
cd `dirname $0`

# bin目录绝对路径
BIN_DIR=`pwd`

# 返回到上一级项目根目录路径
cd ..

# 打印项目根目录绝对路径
# `pwd` 执行系统命令并获得结果
DEPLOY_DIR=`pwd`

# 外部配置文件绝对目录,如果是目录需要/结尾，也可以直接指定文件
# 如果指定的是目录,spring则会读取目录中的所有配置文件
CONF_DIR=$DEPLOY_DIR/conf

# 加载外部log4j2文件的配置
LOG_BACK_FILE=logback.xml

echo "The command is as follow..."
echo "ps -ef | grep java | grep $JAR_NAME | grep -v grep | awk '{print \$2}'"

# 如果输入参数不对，给出提示
usage() {
	echo "WARNING......"
	echo "Tips, please use command: sh servicex-test.sh [start|stop|restart|status]. "
	echo "For example: sh servicex-test.sh start "
	exit 1
}

# 启动
start() {
    PIDS=`ps -ef | grep java | grep "$JAR_NAME" | grep -v grep | awk '{print $2}'`
    if [ -n "$PIDS" ]; then
        echo "ERROR: The $SERVER_NAME already started!"
        echo "THE PID IS: $PIDS"
        exit 1
    fi

    # JVM Configuration
    JAVA_OPTS=" -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true "

    JAVA_MEM_OPTS=""
    BITS=`java -version 2>&1 | grep -i 64-bit`
    if [ -n "$BITS" ]; then
        # 64位
        JAVA_MEM_OPTS=" -server -Xmx512m -Xms512m -Xmn256m -XX:PermSize=128m -Xss256k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 "
    else
        JAVA_MEM_OPTS=" -server -Xms512m -Xmx512m -XX:PermSize=128m -XX:SurvivorRatio=2 -XX:+UseParallelGC "
    fi

    # 日志配置文件
    LOG_BACK_CONFIG=""
    if [ -f "$CONF_DIR/$LOG_BACK_FILE" ]
    then
        LOG_BACK_CONFIG="-Dlogging.config=$CONF_DIR/$LOG_BACK_FILE"
    fi

    CONFIG_FILES=" $LOG_BACK_CONFIG -Dspring.config.location=$CONF_DIR/ "
    echo -e "Starting the $SERVER_NAME ..."
    nohup java $JAVA_OPTS $JAVA_MEM_OPTS $CONFIG_FILES -jar $DEPLOY_DIR/$JAR_NAME > /dev/null 2>&1 &

    echo "The $SERVER_NAME started successfully..."
    PIDS=`ps -ef | grep java | grep "$JAR_NAME" | grep -v grep | awk '{print $2}'`
    echo "THE PID IS: $PIDS"
    exit 0
}

# 停止
stop() {
    PIDS=`ps -ef | grep java | grep "$JAR_NAME" | grep -v grep | awk '{print $2}'`
    if [[ -z "$PIDS" ]]
    then
        echo "The $SERVER_NAME is stopped..."
    else
        echo -e "Stopping the $SERVER_NAME ..."
        echo kill  ${PIDS}
        kill -9 ${PIDS}
        echo "The $SERVER_NAME stopped successfully..."
        exit 0
    fi
}

# 状态
status() {
    PIDS=`ps -ef | grep java | grep "$JAR_NAME" | grep -v grep | awk '{print $2}'`
    if [ -n "$PIDS" ]; then
        echo "The $SERVER_NAME is running..."
        echo "THE PID IS: $PIDS"
        exit 0
    else
        echo "The $SERVER_NAME is stopped..."
        exit 0
    fi
}

# 重启
restart() {
	stop
	start
}

# 根据输入参数执行对应方法，不输入则执行tips提示方法
case "$1" in
   "start")
     start
     ;;
   "stop")
     stop
     ;;
   "status")
     status
     ;;
   "restart")
     restart
     ;;
   *)
     usage
     ;;
esac
