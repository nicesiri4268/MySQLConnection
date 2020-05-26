package com.Mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlMessage {
    private String DBName = "";
    private String DBUser = "";
    private String DBUserserPasswd = "";
    private String connName = "";
    private String allMessage = "";
    private Connection connection = null;


    public MysqlMessage(String DBName, String DBUser, String DBUserserPasswd) {
        this.DBName = DBName;
        this.DBUser = DBUser;
        this.DBUserserPasswd = DBUserserPasswd;//为了安全这里可以更改
        this.connName = setConnName();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(this.connName,
                    this.DBUser,
                    this.DBUserserPasswd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("无法找到类名");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            //希望下一步可以把异常放置到一个dialog文件里面
            System.out.println("数据库连接失败");
        }
    }

    public MysqlMessage() {
        /*测试用例*/
        setDBName("test");
        setDBUser("siri");
        setDBUserserPasswd("zhu135335");
    }

    //为了重设连接名字需要更改时使用
    public void setDBName(String DBName) {
        this.DBName = DBName;
    }

    public void setDBUser(String DBUser) {
        this.DBUser = DBUser;
    }

    public void setDBUserserPasswd(String DBUserserPasswd) {
        this.DBUserserPasswd = DBUserserPasswd;
    }

    private String setConnName() {
        return "jdbc:mysql://localhost:3306/" + DBName + "?useSSL=false&serverTimezone=UTC";
    }

    public Connection getConnection() {
        return connection;
    }

    public String getAllMessage() {
        allMessage = "数据库名称：" + this.DBName + "/n用户名：" + this.DBUser + "JDBC连接：" + this.connName;
        return allMessage;
    }
}

