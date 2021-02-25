package com.example.tbs.dataMapper.oracleMappers;

import com.example.tbs.dataMapper.IUserMapper;
import com.example.tbs.models.Admin;
import com.example.tbs.models.Customer;
import com.example.tbs.models.Owner;
import com.example.tbs.models.User;

import java.sql.SQLException;

public class UserMapper extends DBConfiguration implements IUserMapper<User> {

    public boolean saveEntity(User entity) {
        String query = "INSERT INTO system_user (user_name, surname, phone,login,password,role) VALUES(?, ?, ?, ?, ?,?)";
        try {
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getSurname());
        preparedStatement.setLong(3, entity.getPhone());
        preparedStatement.setString(4, entity.getLogin());
        preparedStatement.setString(5, entity.getPass());
        preparedStatement.setString(6, entity.getRole());
        preparedStatement.execute();
        return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public User getEntity(String login, String password) {
        User user = null;
        try {
            //
            String query = "SELECT * FROM system_user WHERE login LIKE ? AND password LIKE ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String role = resultSet.getString("role");
                int id = resultSet.getInt("id");
                String name = resultSet.getString("user_name");
                String surname = resultSet.getString("surname");
                long phone = resultSet.getLong("phone");
                switch (role){
                    case "admin":
                        user = new Admin(id, name, surname,phone);
                        break;
                    case "owner":
                        user = new Owner(id, name, surname,phone);
                        break;
                    default:
                        user = new Customer(id, name, surname,phone);
                        break;
                }
            }
        } catch (SQLException ex) {
            System.err.println("user get error");
        }
        return user;
    }

    public User getEntityById(int id){
        User user = null;
        try {
            String query = "SELECT * FROM system_user WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String role = resultSet.getString("role");
                String name = resultSet.getString("user_name");
                String surname = resultSet.getString("surname");
                long phone = resultSet.getLong("phone");
                switch (role){
                    case "admin":
                        user = new Admin(id, name, surname,phone);
                        break;
                    case "owner":
                        user = new Owner(id, name, surname,phone);
                        break;
                    default:
                        user = new Customer(id, name, surname,phone);
                        break;
                }
            }
        } catch (SQLException ex) {
            System.err.println("user get error");
        }
        return user;
    }
}
