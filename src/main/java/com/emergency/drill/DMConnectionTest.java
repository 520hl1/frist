package com.emergency.drill;

import java.sql.*;
import java.util.Date;

public class DMConnectionTest {
    public static void main(String[] args) {
        String url = "jdbc:dm://192.168.88.131:5236/SYSDBA";
        String user = "SYSDBA";
        String password = "Dameng@123";

        System.out.println("尝试连接到达梦数据库: " + url);

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("连接成功！开始插入数据...");
            insertSafetyNotice(conn);
            System.out.println("数据插入完成！");

        } catch (Exception e) {
            System.err.println("操作失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void insertSafetyNotice(Connection conn) throws SQLException {
        String sql = "INSERT INTO drill_safety_notice (" +
                "notice_title, notice_content, voice_file, duration, is_active, create_by" +
                ") VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // 设置参数（无外键依赖，直接填充有效值）
            pstmt.setString(1, "消防演练安全须知");
            pstmt.setString(2, "演练期间禁止携带火种，服从指挥人员安排...");
            pstmt.setString(3, "/upload/safety_notice/voice_20250605.mp3");
            pstmt.setInt(4, 120); // 持续时长 120秒
            pstmt.setInt(5, 1);     // 活跃状态
            pstmt.setString(6, "admin"); // 创建人

            // 执行插入
            int rows = pstmt.executeUpdate();
            System.out.println("成功插入 " + rows + " 条记录");
        }
    }
}