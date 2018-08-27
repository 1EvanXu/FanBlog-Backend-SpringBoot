package com.evan.blog.repository.typehandlers;

import com.evan.blog.model.enums.UserLevel;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserLevelTypeHandler implements TypeHandler<UserLevel> {
    @Override
    public void setParameter(PreparedStatement ps, int i, UserLevel parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getLevelcode());
    }

    @Override
    public UserLevel getResult(ResultSet rs, String s) throws SQLException {
        int i = rs.getInt(s);
        return UserLevel.getUserLevel(i);
    }

    @Override
    public UserLevel getResult(ResultSet rs, int columnIndex) throws SQLException {
        int i = rs.getInt(columnIndex);
        return UserLevel.getUserLevel(i);
    }

    @Override
    public UserLevel getResult(CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        return UserLevel.getUserLevel(code);
    }
}
