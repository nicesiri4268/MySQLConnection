package com.MysqTest;

import com.Mysql.AllMessage;
import com.Mysql.MysqlMessage;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MysqlMessageTest {

    String DBName = "test";

    @Test
    void getConnName() {
        MysqlMessage mysqlMessage = new MysqlMessage("test", "siri", "zhu135335");
        AllMessage allmessage = mysqlMessage.getAllMessage();
        assertEquals("jdbc:mysql://localhost:3306/" + DBName + "?useSSL=false&serverTimezone=UTC", allmessage.getConnName());

    }
}