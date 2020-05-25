package com.Mysql;

import com.Mysql.Exception.MysqlConnectException;

import java.sql.*;

public class MysqlConnect {
    private Connection connection;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private int resultCounts = -1;


    public MysqlConnect(MysqlMessage mysqlMessage) {
        connection = mysqlMessage.getConnection();//从mysqlMessage获取连接
    }

    public String setPstmtParam(String SQL, int[] columns, String[] values) {
        /**
         * 预编译命令SQL
         * 需要提供一个int类型的类型标识数组，和一个String类型的值数组
         * Types.Integer 为类型标识数组的值
         * 结果集放置在MySQLConnection类中,调用getResultType获取
         * 返回值为String类型，当执行成功时返回”执行成功“
         * 同时有可能出现的错误有，SQL为空，SQL预编译失败，SQL
         *
         */

        if (SQL == null) return "SQL为空";
        try {
            preparedStatement = connection.prepareStatement(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "预编译失败";
        }
        if (columns != null && values != null && columns.length == values.length) {
            //分类填入预编译类中
            for (int i = 0; i < columns.length; i++) {
                try {
                    switch (columns[i]) {
                        case Types.INTEGER: {
                            preparedStatement.setInt(i + 1, Integer.parseInt(values[i]));
                            break;
                        }
                        case Types.FLOAT: {
                            preparedStatement.setFloat(i + 1, Float.parseFloat(values[i]));
                            break;
                        }
                        case Types.DOUBLE: {
                            preparedStatement.setDouble(i + 1, Double.parseDouble(values[i]));
                            break;
                        }
                        case Types.VARCHAR: {
                            preparedStatement.setString(i + 1, values[i]);
                            break;
                        }
                        default: {
                            throw new MysqlConnectException("第" + (i + 1) + "个参数出现问题");
                        }
                        //可自行添加输入项
                    }
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                    return "第" + i + "个赋值失败";
                } catch (MysqlConnectException throwable) {
                    throwable.printStackTrace();
                    return "赋值失败" + "编号" + i;
                }
            }
        } else if (columns == null && values == null) {
        } else {
            return "类型数组或值数组出现错误";
        }

        try {
            //开始执行SQL预编译命令，并且保存结果集到resultSet
            if (preparedStatement.execute()) {
                resultSet = preparedStatement.executeQuery();
            } else {
                resultCounts = preparedStatement.executeUpdate();//插入或修改操作
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "预编译成功";
    }

    public PreparedStatement getPreparedStatement() {
        if (preparedStatement != null) {
            return preparedStatement;
        } else {
            return null;
        }
    }

    public ResultType getResultType() {
        //返回sql语句结果集
        if (resultSet != null) {
            return new ResultType(resultSet);
        } else if (resultCounts >= 0) {
            return new ResultType(resultCounts);
        } else {
            return null;
        }
    }

    public boolean close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException throwables) {
            System.out.println("关闭数据库失败");
            throwables.printStackTrace();
        }
        return false;
    }
}
