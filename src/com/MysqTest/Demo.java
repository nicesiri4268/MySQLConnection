package com.MysqTest;

import com.Mysql.DataTable;
import com.Mysql.MysqlConnect;
import com.Mysql.MysqlMessage;
import com.Mysql.MysqlResultManage;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.Types;
import java.util.Scanner;

public class Demo {
    /**
     * @program: MySql工具类
     * @description: MySQL8.0封装类测试
     * @author: nicesiri
     * @create: 2020-05-27 11:39
     */
    public Demo() {
        super();
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        //demo.SearchSQL();
        demo.insertSQL();

    }

    private static void showRowValues(DataTable dataTable) {
        int m = dataTable.getColumnCount();
        int n = dataTable.getRowCount();
        String[][] row = dataTable.getRow();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(row[i][j] + "  ");
                if (j == m - 1) {
                    System.out.print("\n");
                }
            }
        }
    }

    private static void showColumnName(DataTable dataTable) {
        int n = dataTable.getColumnCount();
        String[] column = dataTable.getColumn();
        for (int i = 0; i < n; i++) {
            System.out.print(column[i] + "  ");
            if (i == n - 1) {
                System.out.println("");
            }
        }
    }

    public void SearchSQL() {
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
        showColumnName(dataTable);
        showRowValues(dataTable);
    }

    public void insertSQL() {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入用户名：");
        String user = input.nextLine();
        System.out.print("请输入密码： ");
        String passwd = input.nextLine();
        String searchSQL = "insert into studenttest(Sno, Sname, Ssex, Sage, Sdept)values(?,?,?,?,?)";
        MysqlMessage mysqlMessage = new MysqlMessage("test", user, passwd);
        MysqlConnect mysqlConnect = new MysqlConnect(mysqlMessage);
        mysqlConnect.setPstmtParam(searchSQL,
                new int[]{Types.CHAR, Types.CHAR, Types.CHAR, Types.SMALLINT, Types.CHAR},
                new String[]{"201215127", "王五", "男", "20", "MS"});
        MysqlResultManage mysqlResultManage = new MysqlResultManage(mysqlConnect.getResultType());
        System.out.println(mysqlResultManage.getResultCount());


    }
}
