package com.evan.blog.repository.typehandlers;


import com.evan.blog.model.enums.DraftStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DraftStatusTypeHandler implements TypeHandler<DraftStatus> {
    public void setParameter(PreparedStatement preparedStatement, int i, DraftStatus draftStatus, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, draftStatus.getStatusCode());
    }

    public DraftStatus getResult(ResultSet resultSet, String s) throws SQLException {
        int code = resultSet.getInt(s);
        return DraftStatus.getArticleStatus(code);
    }

    public DraftStatus getResult(ResultSet resultSet, int i) throws SQLException {
        int code = resultSet.getInt(i);
        return DraftStatus.getArticleStatus(code);
    }

    public DraftStatus getResult(CallableStatement callableStatement, int i) throws SQLException {
        int code = callableStatement.getInt(i);
        return DraftStatus.getArticleStatus(code);
    }
}

