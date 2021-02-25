package com.example.tbs.dataMapper.oracleMappers;

import com.example.tbs.dataMapper.IOrderMapper;
import com.example.tbs.models.Area;
import com.example.tbs.models.Event;
import com.example.tbs.models.Status;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper<T extends Event> extends DBConfiguration implements IOrderMapper{
    public class Order extends Event {
        private int id;
        private Timestamp time_to;
        private Timestamp time_from;
        private Area area;
        private float sum;
        private Status status;

        public Order(){
            super();
        }
        public Order(int id, Timestamp time_to, Timestamp time_from, Area area, float sum, Status status) {
            this.id = id;
            this.time_to = time_to;
            this.time_from = time_from;
            this.area = area;
            this.sum = sum;
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public Timestamp getTime_to() {
            return time_to;
        }

        public Timestamp getTime_from() {
            return time_from;
        }

        public Area getNumber_of_area() {
            return area;
        }

        public float getSum() {
            return sum;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return "Event#" + id+
                    ", area: "+area.getDescription()+", time: "+time_from.toLocalDateTime().toString()+
                    "-"+time_to.toLocalDateTime().toString()+" status: "+status.getStatus()+", sum: "+sum;
        }
    }

    public List<Order> getMyOrders(int client){
        List<Order> list = new ArrayList<Order>();
        String query = "SELECT * FROM orders WHERE client = ?";
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,client);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(new Order(
                        resultSet.getInt("id"),
                        resultSet.getTimestamp("time_from"),
                        resultSet.getTimestamp("time_to"),
                        new AreaMapper().getEntityById(resultSet.getInt("number_of_area")),
                        resultSet.getFloat("sum"),
                        new StatusMapper().getStatusById(resultSet.getInt("status"))));
            }
        }
        catch (SQLException ex){
            System.err.println(ex);
        }
        return list;
    }
}
