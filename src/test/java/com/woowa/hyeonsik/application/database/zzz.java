package com.woowa.hyeonsik.application.database;

import com.woowa.hyeonsik.server.database.DatabaseConnector;
import com.woowa.hyeonsik.server.database.property.DatabaseProperty;
import com.woowa.hyeonsik.server.database.property.MysqlProperty;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class zzz {
    @Test
    @Disabled
    void a() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        DatabaseProperty property = new MysqlProperty();

        // DB 커넥션 생성
        Class.forName(property.getDriverName());
        Connection conn = DriverManager.getConnection(property.getUrl(), property.getUser(), property.getPassword());

        // 쿼리 작성
        String sql = "SELECT * FROM test";

        // 결과 확인
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()) {
            Long id = rs.getLong(1);
            System.out.println("조회결과: " + id);
        }
    }

    @Test
    @Disabled
    void b() throws Exception {
        int numberOfRequests = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(100); // Adjust the pool size as needed

        DatabaseConnector databaseConnection = new DatabaseConnector(new MysqlProperty());
        for (int i = 0; i < numberOfRequests; i++) {
            final int finalI = i;
            executorService.submit(() -> {
                try {
                    databaseConnection.execute("INSERT INTO test(id) values(" + finalI + ")");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        Thread.sleep(5000);

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }
}
