package com.sample.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DbUtil {

    private Connection con = null;

    public String connect() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            // MySQLに接続
            con = DriverManager.getConnection("jdbc:mysql://db:3306/test_database", "testuser", "password");
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

        return null;
    }

    public void close() {
        try {
            if (this.con.isClosed()) return;
            this.con.close();
        } catch (SQLException e) {

        }
    }

    public Connection getCon() {
        return con;
    }

// 何年かしたらこんな共通処理を書いた気がする。
// ログを仕込んで性能を測れるようにして。
// 懐かしい。
//    public ArrayList<HashMap> select(String sql) {
//        ArrayList<HashMap> result = new ArrayList<>();
//        Statement stm = null;
//        ResultSet rs = null;
//        try {
//            stm = con.createStatement();
//            rs = stm.executeQuery(sql);
//            ResultSetMetaData meta = rs.getMetaData();
//
//            while(rs.next()){
//                HashMap record = new HashMap();
//                for (int index=0;index<meta.getColumnCount();index++) {
//                    String columnName = meta.getColumnName(index);
//
//                    switch (meta.getColumnType(index)) {
//                        case Types.INTEGER:
//                            record.put(columnName, rs.getInt(columnName));
//                        break;
//                    }
//
//                }
//            }
//        } catch (SQLException e) {
//
//        } finally {
//            try {
//                rs.close();
//                stm.close();
//            } catch(SQLException e) {
//
//            }
//        }
//
//        return result;
//    }

}
