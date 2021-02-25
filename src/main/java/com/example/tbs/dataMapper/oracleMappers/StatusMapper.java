package com.example.tbs.dataMapper.oracleMappers;

import com.example.tbs.dataMapper.IStatusMapper;
import com.example.tbs.models.Status;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusMapper extends DBConfiguration implements IStatusMapper {

    public List<Status> getNextStatus(int level){
        List<Status> list = new ArrayList<>();
        String query = "SELECT * FROM status WHERE parent_level>=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,level);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Status(resultSet.getString("name"),resultSet.getInt("current_level"),resultSet.getInt("parent_level")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Status getLastStatus(){
    //получить здесь максимальный статус. вызов происходит после оплаты клиентом
    String query = "SELECT * FROM status WHERE current_level IN (SELECT MAX(current_level) FROM status)";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if(resultSet.next()){return new Status(resultSet.getString("name"),resultSet.getInt("current_level"),resultSet.getInt("parent_level"));}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Status getStatusById(int id) throws SQLException {
        String query = "SELECT * FROM status WHERE current_level = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
        return new Status(resultSet.getString("name"),resultSet.getInt("current_level"),resultSet.getInt("parent_level"));
        else return null;
    }
}
