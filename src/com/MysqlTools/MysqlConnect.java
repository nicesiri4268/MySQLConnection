package com.MysqlTools;

import com.MysqlTools.Exception.MysqlConnectException;

import java.sql.*;

public class MysqlConnect {
    private Connection connection;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private int resultCounts = -1;
    /*
     * 使用MySQLMessage产生的Connection对象，
     * 创建PrepareStatement对象
     * 预编译SQL
     * 对SQL赋值：columnsType数组（int）,values数组（String）
     * 使用方法getResultType（）， 返回一个ResultType类（封装Result和ResultCount属性）
     * */

    public MysqlConnect(MysqlMessage mysqlMessage) {
        connection = mysqlMessage.getConnection();//从mysqlMessage获取连接
    }

    public String setPstmtParam(String SQL, int[] columnsType, String[] values) {
        /*
         * 预编译命令SQL
         * 需要提供一个int类型的类型标识数组，和一个String类型的值数组
         * Types.Integer 为类型标识数组的值
         * 结果集放置在MySQLConnection类中,调用getResultType获取
         * 返回值为String类型，当执行成功时返回”执行成功“
         * 同时有可能出现的错误有，SQL为空，SQL预编译失败，SQL
         */

        if (SQL == null) return "SQL为空";
        try {
            preparedStatement = connection.prepareStatement(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "预编译失败";
        }
        if (columnsType != null && values != null && columnsType.length == values.length) {
            //分类填入预编译类中
            for (int i = 0; i < columnsType.length; i++) {
                try {
                    switchColumnsType(columnsType, values, i);//预编译后的赋值
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                    return "第" + i + "个赋值失败";
                } catch (MysqlConnectException throwable) {
                    throwable.printStackTrace();
                    return "赋值失败" + "编号" + i;
                }
            }
        } else if (columnsType == null && values == null) {
            System.out.print("");
            //允许类型数组或者值数组为空
        } else {
            return "类型数组或值数组出现错误";
        }

        try {
            //开始执行SQL预编译命令，并且保存结果集到resultSet
            if (preparedStatement.execute()) {
                //execute()返回的是一个boolean值,代表两种不同的操作啊
                // getResultSet()返回的是结果集,而getUpdateCount()返回的是更新的记数
                resultSet = preparedStatement.executeQuery();
            } else {
                resultCounts = preparedStatement.getUpdateCount();//插入或修改操作
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "预编译成功";
    }

    /**
     * @param columns 参数1 int数组存储着有关value的类型
     * @param values  参数2 String数组 存储着预编译后的要赋的值
     * @param i       参数3 循环的次数
     * @apiNote 可以添加Type.的分支以适应更多类型
     */
    private void switchColumnsType(int[] columns, String[] values, int i) throws SQLException {
        switch (columns[i]) {
            case Types.INTEGER: {
                preparedStatement.setInt(i + 1, Integer.parseInt(values[i]));
                break;
            }
            case Types.SMALLINT: {
                preparedStatement.setShort(i + 1, Short.parseShort(values[i]));
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
            case Types.VARCHAR:
            case Types.CHAR: {
                preparedStatement.setString(i + 1, values[i]);
                break;
            }
            default: {
                throw new MysqlConnectException("第" + (i + 1) + "个参数出现问题");
            }
            //可自行添加输入项
        }
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
