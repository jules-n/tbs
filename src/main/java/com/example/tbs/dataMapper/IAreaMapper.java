package com.example.tbs.dataMapper;

import com.example.tbs.models.Area;
import java.sql.SQLException;
import java.util.List;

public interface IAreaMapper {
    boolean saveEntity(Area entity);
    List<Area> getAll();
    Area getEntityById(int id)  throws SQLException;
}
