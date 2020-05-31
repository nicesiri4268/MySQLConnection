package com.MysqlTools;

import java.sql.ResultSet;

public class ResultType {
    /**
     * @program: MySql工具类
     * @description: 控制返回的是ResultSet还是ResultCount，提供返回方法
     * @author: nicesiri
     * @create: 2020-05-24 20:38
     */

    private ResultSet resultSet = null;
    private int resultCount = -1;
    private boolean resultSetFlag = false;

    public ResultType() {
        super();
    }

    public ResultType(ResultSet resultSet) {
        super();
        if (resultSet != null) {
            this.resultSet = resultSet;
            this.resultSetFlag = true;
        }
    }

    public ResultType(int resultCount) {
        super();
        if (resultCount >= 0) {
            this.resultCount = resultCount;
        }
    }

    public ResultSet getResultSet() {
        if (resultSet != null) {
            return resultSet;
        }
        return null;
    }

    public ResultType setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
        return this;
    }

    public int getResultCount() {
        if (resultCount != -1 && resultSetFlag == false) {
            return resultCount;
        }
        return -1;
    }

    public ResultType setResultCount(int resultCount) {
        this.resultCount = resultCount;
        return this;
    }

    public boolean isResultSetFlag() {
        return resultSetFlag;
    }

}
