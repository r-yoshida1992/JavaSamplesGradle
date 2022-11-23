package com.example.swing.entitygenerator.connector;

import com.example.swing.entitygenerator.config.Config;

import java.sql.*;

public class DataBaseConnector {
    static Config conf = Config.getInstance();

    public static ResultSet executeSql(String sql) throws SQLException {
        Connection con = DriverManager.getConnection(conf.getUrl(), conf.getUser(), conf.getPassword());
        PreparedStatement stmt = con.prepareStatement(sql);
        return stmt.executeQuery();
    }

}