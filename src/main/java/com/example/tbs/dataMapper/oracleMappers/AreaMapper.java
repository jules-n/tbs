package com.example.tbs.dataMapper.oracleMappers;

import com.example.tbs.dataMapper.IAreaMapper;
import com.example.tbs.models.Area;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaMapper extends DBConfiguration implements IAreaMapper {
    public boolean saveEntity(Area entity) {
        String query = "INSERT INTO area (number_of_area, availability,description) VALUES(?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, entity.getNumber());
            preparedStatement.setInt(2, 1);
            preparedStatement.setString(3, entity.getDescription());
            preparedStatement.execute();
            try {
                for(int facility:entity.getIdFacilities()) {
                    preparedStatement = connection.prepareStatement("INSERT INTO facilities_of_area VALUES(?,?)");
                    preparedStatement.setInt(1, facility);
                    preparedStatement.setInt(2, entity.getNumber());
                    preparedStatement.execute();
                }
                return true;
            }catch (SQLException ex){
                System.err.println("err in f-a");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("err in area");
            return false;
        }
    }
    public List<Area> getAll(){
        List<Area> list = new ArrayList<>();
    try {
        String query = "SELECT number_of_area, description FROM area WHERE availability = '1'";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int number = resultSet.getInt("number_of_area");
            list.add(new Area(number,resultSet.getString("description"),true,new FacilityMapper().getAllInRoom(number)));
        }

    }catch (SQLException ex){
        System.err.println(ex);
    }
        return list;
    }
    public Area getEntityById(int id) throws SQLException {
        String query = "SELECT * FROM area WHERE number_of_area = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
        return new Area(id,resultSet.getString("description"),resultSet.getBoolean("availability"),new FacilityMapper().getAllInRoom(id));
        else return null;
    }
}
