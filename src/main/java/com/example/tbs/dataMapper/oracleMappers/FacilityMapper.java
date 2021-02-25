package com.example.tbs.dataMapper.oracleMappers;

import com.example.tbs.dataMapper.IFacilityMapper;
import com.example.tbs.models.Facility;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacilityMapper extends DBConfiguration implements IFacilityMapper {

    public List<Facility> getAllInRoom(int number){
        List<Facility> facilities = new ArrayList<>();
        try {
            String query = "SELECT facility.name_of_facility FROM facility INNER JOIN facilities_of_area ON facilities_of_area.facility = facility.id WHERE facilities_of_area.number_of_area =?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,number);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                facilities.add(new Facility(resultSet.getString("name_of_facility")));
            }
        }catch (SQLException ex){

        }
        return facilities;
    }

    public boolean saveEntity(Facility entity) {
        String query = "INSERT INTO facility(name_of_facility)  VALUES(?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<Facility> getAll(){
        List<Facility> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM facility";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                list.add(new Facility(resultSet.getInt("id"),resultSet.getString("name_of_facility")));
            }

        }catch (SQLException ex){
            System.err.println(ex);
        }
        return list;
    }
}
