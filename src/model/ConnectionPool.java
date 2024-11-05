// Source code is decompiled from a .class file using FernFlower decompiler.
package model;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionPool {
    private final String DB = "inventarioCastores";
    private final String URL = "jdbc:mysql://localhost:3306/inventarioCastores?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USER = "root";
    private final String PASS = "root";
    private static ConnectionPool dataSource;
    private BasicDataSource basicDataSource = null;

    private ConnectionPool() {
        this.basicDataSource = new BasicDataSource();
        this.basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        this.basicDataSource.setUsername("root");
        this.basicDataSource.setPassword("root");
        this.basicDataSource.setUrl("jdbc:mysql://localhost:3306/inventarioCastores?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        this.basicDataSource.setMinIdle(5);
        this.basicDataSource.setMaxIdle(20);
        this.basicDataSource.setMaxTotal(50);
        this.basicDataSource.setMaxWaitMillis(-1L);
    }

    public static ConnectionPool getInstance() {
        if (dataSource == null) {
            dataSource = new ConnectionPool();
            return dataSource;
        } else {
            return dataSource;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.basicDataSource.getConnection();
    }

    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
