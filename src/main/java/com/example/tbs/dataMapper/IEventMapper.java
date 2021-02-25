package com.example.tbs.dataMapper;

import com.example.tbs.models.Event;
import java.util.List;

public interface IEventMapper {

    boolean saveEntity(Event entity);
    List<Event> getAll();
    Event getEntityById(int id);
    List<Event> getSchedule(int area);
    boolean updateStatus(int id, int status);
}
