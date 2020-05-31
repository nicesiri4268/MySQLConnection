package com.MysqTest;

import com.MysqlTools.DataTable;
import com.MysqlTools.MysqlConnect;
import com.MysqlTools.MysqlMessage;
import com.MysqlTools.MysqlResultManage;

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
        demo.insertSQL();
        demo.SearchSQL();
        demo.updateSQL();
        demo.SearchSQL();
        demo.deleteSQL();
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
        String searchSQL = "select * from studenttest";
        MysqlMessage mysqlMessage = new MysqlMessage("test", user, passwd);
        //设置访问的数据库，用户名，密码
        MysqlConnect mysqlConnect = new MysqlConnect(mysqlMessage);
        //构建连接类
        mysqlConnect.setPstmtParam(searchSQL, null, null);
        //预编译
        MysqlResultManage mysqlResultManage = new MysqlResultManage(mysqlConnect.getResultType());
        //构建结果类
        DataTable dataTable = mysqlResultManage.manageResult();
        //返回赋好值的结果表
        showColumnName(dataTable);
        showRowValues(dataTable);
    }

    public void insertSQL() {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入用户名：");
        String user = input.nextLine();
        System.out.print("请输入密码： ");
        String passwd = input.nextLine();
        String insertSQL = "insert into studenttest(Sno, Sname, Ssex, Sage, Sdept)values(?,?,?,?,?)";
        MysqlMessage mysqlMessage = new MysqlMessage("test", user, passwd);
        MysqlConnect mysqlConnect = new MysqlConnect(mysqlMessage);
        mysqlConnect.setPstmtParam(insertSQL,
                new int[]{Types.CHAR, Types.CHAR, Types.CHAR, Types.SMALLINT, Types.CHAR},
                new String[]{"201215128", "张三", "男", "20", "MS"});
        MysqlResultManage mysqlResultManage = new MysqlResultManage(mysqlConnect.getResultType());
        //调用结果集的getresultCount方法返回修改的行数
        System.out.println(mysqlResultManage.getResultCount());


    }

    public void deleteSQL() {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入用户名：");
        String user = input.nextLine();
        System.out.print("请输入密码： ");
        String passwd = input.nextLine();
        String deleteSQL = "delete from Studenttest where Sno = ?";
        MysqlMessage mysqlMessage = new MysqlMessage("test", user, passwd);
        MysqlConnect mysqlConnect = new MysqlConnect(mysqlMessage);
        mysqlConnect.setPstmtParam(deleteSQL,
                new int[]{Types.CHAR},
                new String[]{"201215127"});
        MysqlResultManage mysqlResultManage = new MysqlResultManage(mysqlConnect.getResultType());
        System.out.println(mysqlResultManage.getResultCount());
    }

    public void updateSQL() {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入用户名：");
        String user = input.nextLine();
        System.out.print("请输入密码： ");
        String passwd = input.nextLine();
        String updateSQL = "update Studenttest set Sname = ? where Sno = ?";
        MysqlMessage mysqlMessage = new MysqlMessage("test", user, passwd);
        MysqlConnect mysqlConnect = new MysqlConnect(mysqlMessage);
        mysqlConnect.setPstmtParam(updateSQL,
                new int[]{Types.CHAR, Types.CHAR},
                new String[]{"张三", "201215127"});
        MysqlResultManage mysqlResultManage = new MysqlResultManage(mysqlConnect.getResultType());
        System.out.println(mysqlResultManage.getResultCount());
    }
}
