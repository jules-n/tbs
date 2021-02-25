package com.example.tbs.dataMapper;

import com.example.tbs.models.Event;
import java.util.List;

public interface IOrderMapper <T extends Event> {
    List<T> getMyOrders(int client);
}
