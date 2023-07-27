#!/bin/bash

# 数据库连接信息
db_host="127.0.0.1"
db_user="ln"
db_password="123456"
db_name="ln"

# SQL脚本文件路径
sql_file="db_init.sql"

# 使用数据库客户端工具执行SQL脚本
mysqld -h "$db_host" -u "$db_user" -p"$db_password" "$db_name" < "$sql_file"
