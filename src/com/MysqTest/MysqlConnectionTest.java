package com.MysqTest;

import com.Mysql.DataTable;
import com.Mysql.MysqlConnect;
import com.Mysql.MysqlMessage;
import com.Mysql.MysqlResultManage;

import java.sql.*;

public class MysqlConnectionTest {
    public MysqlConnectionTest() {
    }

    public static void main(String[] args) throws SQLException {
        MysqlMessage mysqlMessage = new MysqlMessage("test", "siri", "zhu135335");
        //String sql = "select Sno,Sname from student where Sno=?&&Sname=?";
        //int [] columns= {Types.INTEGER, Types.VARCHAR};
        //String[] valuse ={"201215122","刘晨"};
        String sql = "select Sno,Sname,Ssex from student ";
        int[] columns = null;
        String[] valuse = null;
        MysqlConnect connect = new MysqlConnect(mysqlMessage);
        connect.setPstmtParam(sql, columns, valuse);

        /*
        //该短过程测试resultSet是否正常
        ResultSet resultSet = connect.getResultSet();
        while (resultSet.next()){
            String id = resultSet.getString("Sno");
            String name = resultSet.getString("Sname");
            System.out.println("id= "+id+"  name= "+name);
        }*/

        System.out.println("新方式");
        MysqlResultManage mysqlResultManage =
                new MysqlResultManage(connect.getResultType());
        DataTable dataTable = mysqlResultManage.manageResult();//返回一个DataTable对象
        System.out.println("程序结束");
        String[][] row = dataTable.getRow();
        System.out.println(row[0][0]);

        /*
         * ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
         * System.out.println(resultSetMetaData.getColumnName(1));
         * 生成ResultSetMetaData
         * 目的为了获取getColumnCount方法获取列数
         * 也可以获取某一列的列名
         * */
        /*while (resultSet.next()){
            String id = resultSet.getString("Sno");
            String name = resultSet.getString("Sname");
            System.out.println("id= "+id+"  name= "+name);
        }*/
        /*

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.println(preparedStatement.execute());
            ResultSet resultSet = preparedStatement.executeQuery();
            //输出student表的Sno，Sname内容
            while (resultSet.next()){
                String id = resultSet.getString("Sno");
                String name = resultSet.getString("Sname");
                System.out.println("id= "+id+"  name= "+name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
    }
}
