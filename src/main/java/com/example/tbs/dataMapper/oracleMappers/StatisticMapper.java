package com.example.tbs.dataMapper.oracleMappers;

import com.example.tbs.dataMapper.IStatisticMapper;
import com.example.tbs.models.Area;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatisticMapper extends DBConfiguration implements IStatisticMapper<StatisticMapper.Status> {
    public class Status{
        private String name;
        private int number;
        private float sum;
        public Status(String name, int number, float sum){
            this.name = name;
            this.number = number;
            this.sum = sum;
        }
        public String getName() {
            return name;
        }

        public int getNumber() {
            return number;
        }

        public float getSum() {
            return sum;
        }
    }

    public float getSumForYear(){
        try {
            String query = "SELECT SUM(sum) AS total_sum FROM orders INNER JOIN reservation ON orders.id = reservation.id WHERE EXTRACT(year FROM reservation.time_from) = EXTRACT(year FROM sysdate) AND orders.status = 4";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                return resultSet.getFloat("total_sum");
            }

        }catch (SQLException ex){
            System.err.println(ex);
        }
        return 0;
    }
    public List<Status> statusList(){
        List<Status> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM status_statistic";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                list.add(new Status(resultSet.getString("name"),resultSet.getInt("number_of_orders"),resultSet.getFloat("sum")));
            }

        }catch (SQLException ex){
            System.err.println(ex);
        }
        return list;
    }

    public Area getFavourite(){
        try {
            String query = "SELECT number_of_area, COUNT(number_of_area) FROM reservation  GROUP BY number_of_area HAVING COUNT (number_of_area)=(SELECT MAX(mycount) FROM (SELECT number_of_area, COUNT(number_of_area) mycount FROM reservation GROUP BY number_of_area))";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                System.err.println();
                return new AreaMapper().getEntityById(resultSet.getInt("number_of_area"));
            }

        }catch (SQLException ex){
            System.err.println(ex);
        }
        return null;
    }
}
