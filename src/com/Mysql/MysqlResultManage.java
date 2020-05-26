package com.Mysql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MysqlResultManage {
    private ResultSet resultSet;
    private String[] columnsName = null;
    private DoubleArray[] doubleArrays;
    private Map<String, ArrayList<String>> map;

    public MysqlResultManage(ResultType resultType) {
        this.resultSet = resultType.getResultSet();
        map = new ConcurrentHashMap<>();//初始化map
    }

    public DataTable manageResult() {
        if (resultSet == null) {
            return null;
        }
        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            //获取结果集的元数据
            int n = resultSetMetaData.getColumnCount();//计算有多少行
            columnsName = new String[n];
            doubleArrays = new DoubleArray[n];
            for (int i = 0; i < n; i++) {
                doubleArrays[i] = new DoubleArray();//初始化doubleArrays类，需要注意java底层编译逻辑
                String valueName = resultSetMetaData.getColumnName(i + 1);//此处应该为i+1，为第一行
                //resultSetMetaData.getColumnName()，该函数返回值从1开始
                columnsName[i] = valueName;
                //保存结果集的列名，初始化doubleArrays类
            }

            while (resultSet.next()) {
                //一个arraylist的数组
                for (int i = 0; i < n; i++) {//循环获取列名，提取表中的数据值
                    //这里如果有一个key-value的数据结构就好了
                    //开始学习Map接口
                    //计划使用键值对的方式存储查询表数据
                    //String value = resultSet.getString(i);
                    //生成的map的ColumnsName都是key=Sno，导致map一直在修改Value,所以修改map为<String，String[]>
                    String arrayListValue;
                    arrayListValue = resultSet.getObject(columnsName[i]).toString();//将所有表的信息都转换为String类型
                    doubleArrays[i].add(arrayListValue);//赋值到DoubleArrays内部类的Arraylist里面
                }
            }

            for (int i = 0; i < n; i++) {
                //将结果集封装到Map中
                //有没有可能多线程的同步封装呢？
                ArrayList<String> temp = doubleArrays[i].getArrayList();
                map.put(columnsName[i], temp);

            }
            return new DataTable(columnsName, map);//返回一个DataTable类，包含完整信息
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    //需要处理查询单行，多行
    class DoubleArray {
        //内部类，实现的是DoubleArray内部封装ArrayList<String>
        //作用：ArrayList类的数组
        private final ArrayList<String> arrayList;

        public DoubleArray() {
            arrayList = new ArrayList<>();
        }

        public void add(String value) {
            arrayList.add(value);
        }

        public ArrayList<String> getArrayList() {
            return arrayList;
        }
    }


}
