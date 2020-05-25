package com.Mysql;

import java.sql.Connection;
import java.util.*;

public class DataTable extends HashMap {
    /**
     * @program: MySql工具类
     * @description:
     * @author: nicesiri
     * @create: 2020-05-22 19:56
     */
    private String[] column;
    private String[][] row;
    private int columnCount;
    private int rowCount;
    private Map<String, ArrayList<String>> map;

    public DataTable() {
        super();
    }

    public DataTable(String[] column, Map<String, ArrayList<String>> map) {
        super();
        this.column = column;
        this.map = map;
        this.setDataTable();
    }

    public DataTable(String[] column, String[][] row, int columnCount, int rowCount) {
        super();
        this.column = column;
        this.row = row;
        this.columnCount = columnCount;
        this.rowCount = rowCount;
    }

    public void setDataTable() {
        /**
         * 设置结果集的表列，无论是一行还是多行，无论一列还是多列
         **/
        columnCount = map.size();
        //System.out.println(map.get(0));
        //需要使用迭代器的方式来提取出 map中的值，或者返回一个列名回来获取存储在map中的值

        ArrayList<String> temp = map.get(column[0]);
        rowCount = temp.size();//行数
        row = new String[rowCount][columnCount];
        //此处断点查看是否获取到了map中ArrayList的值
        for (int i = 0; i < rowCount; i++) {
            //二维数组的定义与用法
            //这里是第一行
            for (int j = 0; j < columnCount; j++) {
                temp = map.get(column[j]);//获取储存在map中的ArrayList数组，也就是一列值
                row[i][j] = temp.get(i);
            }
        }
    }

    public String[] getColumn() {
        return column;
    }

    public DataTable setColumn(String[] column) {
        this.column = column;
        return this;
    }

    public String[][] getRow() {
        return row;
    }

    public DataTable setRow(String[][] row) {
        this.row = row;
        return this;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public DataTable setColumnCount(int columnCount) {
        this.columnCount = columnCount;
        return this;
    }

    public int getRowCount() {
        return rowCount;
    }

    public DataTable setRowCount(int rowCount) {
        this.rowCount = rowCount;
        return this;
    }

    public Map<String, ArrayList<String>> getMap() {
        return map;
    }

    public DataTable setMap(Map<String, ArrayList<String>> map) {
        this.map = map;
        return this;
    }

}
