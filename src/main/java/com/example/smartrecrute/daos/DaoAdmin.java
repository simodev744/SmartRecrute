package com.example.smartrecrute.daos;

import com.example.smartrecrute.connection.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoAdmin {

    private Connection connection;

    public DaoAdmin() throws SQLException, ClassNotFoundException {
        this.connection = DBConnection.getConnection();
    }
}