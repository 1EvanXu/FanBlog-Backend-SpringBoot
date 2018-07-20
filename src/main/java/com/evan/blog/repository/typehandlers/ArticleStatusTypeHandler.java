package com.evan.blog.repository.typehandlers;


import com.evan.blog.model.enums.ArticleStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleStatusTypeHandler implements TypeHandler<ArticleStatus> {
    public void setParameter(PreparedStatement preparedStatement, int i, ArticleStatus articleStatus, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, articleStatus.getStatusCode());
    }

    public ArticleStatus getResult(ResultSet resultSet, String s) throws SQLException {
        int code = resultSet.getInt(s);
        return ArticleStatus.getArticleStatus(code);
    }

    public ArticleStatus getResult(ResultSet resultSet, int i) throws SQLException {
        int code = resultSet.getInt(i);
        return ArticleStatus.getArticleStatus(code);
    }

    public ArticleStatus getResult(CallableStatement callableStatement, int i) throws SQLException {
        int code = callableStatement.getInt(i);
        return ArticleStatus.getArticleStatus(code);
    }
}

