package com.example.tbs.dataMapper;

import com.example.tbs.models.Facility;
import java.util.List;

public interface IFacilityMapper {

    List<Facility> getAllInRoom(int number);
    boolean saveEntity(Facility entity);
    List<Facility> getAll();
}
