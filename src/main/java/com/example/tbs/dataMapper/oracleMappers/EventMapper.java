package com.example.tbs.dataMapper.oracleMappers;

import com.example.tbs.dataMapper.IEventMapper;
import com.example.tbs.models.Event;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventMapper extends DBConfiguration implements IEventMapper {

    public boolean saveEntity(Event entity) {
        String query = "INSERT INTO reservation (status, time_from, time_to, client, number_of_area, number_of_clients) VALUES(?, ?, ?, ?, ?,?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, entity.getStatus().getLevel());
            preparedStatement.setTimestamp(2, entity.getFrom());
            preparedStatement.setTimestamp(3, entity.getTo());
            preparedStatement.setInt(4, entity.getUser().getId());
            preparedStatement.setInt(5, entity.getArea().getNumber());
            preparedStatement.setInt(6, entity.getNumberOfClients());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<Event> getAll(){
        List<Event> list = new ArrayList<>();
        String query = "SELECT * FROM reservation";
        try {
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            list.add(new Event(new StatusMapper().getStatusById(resultSet.getInt("status")),
                    resultSet.getTimestamp("time_from"), resultSet.getTimestamp("time_to"),
                    new UserMapper().getEntityById(resultSet.getInt("client")),
                    new AreaMapper().getEntityById(resultSet.getInt("number_of_area")),
                    resultSet.getInt("id")));
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Event getEntityById(int id){
        String query = "SELECT * FROM reservation WHERE id = ?";
        Event event = null;
        try{
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            event = new Event(new StatusMapper().getStatusById(resultSet.getInt("status")),
                    resultSet.getTimestamp("time_from"), resultSet.getTimestamp("time_to"),
                    new UserMapper().getEntityById(resultSet.getInt("client")),
                    new AreaMapper().getEntityById(resultSet.getInt("number_of_area")),
                    resultSet.getInt("id"));
        }
        catch (SQLException ex){
        System.err.println(ex);
        }
        return event;
    }

    public List<Event> getSchedule(int area){
        String query = "SELECT * FROM schedule WHERE number_of_area = ?";
        List<Event> list = new ArrayList<>();
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,area);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
                list.add(new Event(new StatusMapper().getStatusById(resultSet.getInt("status")),
                        resultSet.getTimestamp("time_from"), resultSet.getTimestamp("time_to"),
                        null,
                        new AreaMapper().getEntityById(resultSet.getInt("number_of_area")),
                        resultSet.getInt("id")));
        }
        catch (SQLException ex){
            System.err.println(ex);
        }
        return list;
    }

    public boolean updateStatus(int id, int status){
        String query = "UPDATE reservation SET status = ? WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            return true;
        }
        catch (SQLException ex){
            return false;
        }
    }
}
