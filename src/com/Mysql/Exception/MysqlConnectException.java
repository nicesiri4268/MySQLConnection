package com.Mysql.Exception;

public class MysqlConnectException extends RuntimeException {
    /**
     * @program: MySql工具类
     * @description:MysqlConnectionException的异常类
     * @author: nicesiri
     * @create: 2020-05-15 00:10
     */
    public MysqlConnectException() {
        super();
    }

    public MysqlConnectException(String string) {
        super(string);
    }

}
