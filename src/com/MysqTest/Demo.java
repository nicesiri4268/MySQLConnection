package com.MysqTest;

import com.Mysql.DataTable;
import com.Mysql.MysqlConnect;
import com.Mysql.MysqlMessage;
import com.Mysql.MysqlResultManage;

import java.sql.Connection;
import java.util.Scanner;

public class Demo {
    /**
     * @program: MySql工具类
     * @description: MySQL8.0封装类测试
     * @author: nicesiri
     * @create: 2020-05-27 11:39
     */
    public Demo() {

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入用户名：");
        String user = input.nextLine();
        System.out.print("请输入密码： ");
        String passwd = input.nextLine();
        String searchSQL = "select * from student";
        MysqlMessage mysqlMessage = new MysqlMessage("test", user, passwd);
        MysqlConnect mysqlConnect = new MysqlConnect(mysqlMessage);
        mysqlConnect.setPstmtParam(searchSQL, null, null);
        MysqlResultManage mysqlResultManage = new MysqlResultManage(mysqlConnect.getResultType());
        DataTable dataTable = mysqlResultManage.manageResult();
        //String[] column = dataTable.getColumn();
        //System.out.println(column);


    }
}
