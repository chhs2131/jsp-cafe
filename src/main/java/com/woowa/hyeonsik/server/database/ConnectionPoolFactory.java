package com.woowa.hyeonsik.server.database;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.woowa.hyeonsik.server.database.property.DatabaseProperty;

import java.sql.SQLException;

public class ConnectionPoolFactory {
    public static MysqlConnectionPoolDataSource getConnectionPool(DatabaseProperty databaseProperty) {
        try {
            MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
            dataSource.setUrl(databaseProperty.getUrl());
            dataSource.setUser(databaseProperty.getUser());
            dataSource.setPassword(databaseProperty.getPassword());

            dataSource.setMaxReconnects(5);
            dataSource.setInitialTimeout(2);
            dataSource.setAutoReconnect(true);
            return dataSource;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
