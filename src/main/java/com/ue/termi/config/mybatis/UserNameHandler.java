package com.ue.termi.config.mybatis;
/*
          .--,       .--,
         ( (  \.---./  ) )
          '.__/o   o\__.'
             {=  ^  =}
              >  -  <
             /       \
            //       \\
           //|   .   |\\
           "'\       /'"_.-~^`'-.
              \  _  /--'         `
            ___)( )(___
           (((__) (__)))
   高山仰止,景行行止.虽不能至,心向往之。
*/

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: iserver
 * @description: mybatis返回值 类型 处理
 * @author: youyi
 * @create: 2020-05-29 11:32
 **/
public class UserNameHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i,"name="+s);
    }

    @Override
    public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String name = resultSet.getString(s);
        return "name=null";
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String name = resultSet.getString(i);
        return "name=null";
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String name = callableStatement.getString(i);
        return "name=null";
    }
}
