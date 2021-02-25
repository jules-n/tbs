package com.example.tbs.dataMapper;

import com.example.tbs.models.Status;

import java.sql.SQLException;
import java.util.List;

public interface IStatusMapper {
    public List<Status> getNextStatus(int level);
    public Status getLastStatus();
    public Status getStatusById(int id) throws SQLException;
}
