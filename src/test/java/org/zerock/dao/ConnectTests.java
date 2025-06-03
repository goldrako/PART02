package org.zerock.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectTests {
    // 데이터베이스 연결 정보를 상수로 추출
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/webdb";
    private static final String USER = "webuser";
    private static final String PASSWORD = "webuser";

    @Test
    public void testConnection() throws Exception {
        Class.forName(DRIVER_CLASS);

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            Assertions.assertNotNull(connection);
        }
    }

    @Test
    public void test1() {

        int v1 = 10;
        int v2 = 110;

        Assertions.assertNotEquals(v1,v2);
//        Assertions.assertEquals(v1,v2);

    }

    @Test
    public void testHikariCP() throws Exception {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(DRIVER_CLASS);
        config.setJdbcUrl(DB_URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        try (HikariDataSource ds = new HikariDataSource(config);
             Connection connection = ds.getConnection()) {
            System.out.println("### Connection = " + connection);
            Assertions.assertNotNull(connection);
        }
    }
}


//package org.zerock.dao;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//public class ConnectTests {
//
//    @Test
//    public void testConnection() throws Exception {
//
//        Class.forName("org.mariadb.jdbc.Driver");
//
//        Connection connection = DriverManager.getConnection(
//                "jdbc:mariadb://localhost:3306/webdb",
//                "webuser",
//                "webuser");
//
//        Assertions.assertNotNull(connection);
//
//        connection.close();
//    }
//
//    @Test
//    public void test1() {
//
//        int v1 = 10;
//        int v2 = 110;
//
//        Assertions.assertEquals(v1,v2);
//
//    }
//
//    @Test
//    public void testHikariCP() throws Exception {
//
//        HikariConfig config = new HikariConfig();
//        config.setDriverClassName("org.mariadb.jdbc.Driver");
//        config.setJdbcUrl("jdbc:mariadb://localhost:3306/webdb");
//        config.setUsername("webuser");
//        config.setPassword("webuser");
//        config.addDataSourceProperty("cachePrepStmts", "true");
//        config.addDataSourceProperty("prepStmtCacheSize", "250");
//        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//
//        HikariDataSource ds = new HikariDataSource(config);
//        Connection connection = ds.getConnection();
//
//        System.out.println(connection);
//
//        connection.close();
//
//    }
//
//}

