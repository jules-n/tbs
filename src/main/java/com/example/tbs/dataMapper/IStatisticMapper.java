package com.example.tbs.dataMapper;

import com.example.tbs.models.Area;

import java.util.List;

public interface IStatisticMapper<T> {
    float getSumForYear();
    Area getFavourite();
    List<T> statusList();
}
