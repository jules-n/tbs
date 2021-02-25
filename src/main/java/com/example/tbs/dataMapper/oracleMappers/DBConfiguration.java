package com.example.tbs.dataMapper.oracleMappers;


import java.sql.*;

public class DBConfiguration {
    protected static Connection connection = null;
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String user = "system";
    private String password = "1234";
    protected Statement statement = null;
    protected ResultSet resultSet = null;
    protected PreparedStatement preparedStatement = null;
    public DBConfiguration(){

           try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                connection = DriverManager.getConnection(url,
                        user, password);
            } catch (ClassNotFoundException e) {
                System.err.println("коннектор - ленивый батрак");
            } catch (SQLException e) {
                System.err.println("чекайте сервер");
            }

    }

    public Connection getConnection(){
        return connection;
    }
}
