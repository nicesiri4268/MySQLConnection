package com.Mysql;

public class AllMessage {
    //所有MySQLMessage信息的返回类
    String AllMessage = null;
    String ConnName = null;

    AllMessage(String DBName, String User, String Passwd, String ConnName) {
        AllMessage = DBName + User + Passwd;
        this.ConnName = ConnName;
    }

    public String getConnName() {
        return ConnName;
    }

    public String getAllMessage() {
        return AllMessage;
    }
}