package com.example.tbs.comparators;

import com.example.tbs.models.Event;

import java.util.Comparator;

public class StatusComparator implements Comparator<Event> {
    @Override
    public int compare(Event t, Event t1) {
        if (t.getStatus().getLevel() == t1.getStatus().getLevel()) {
            return 0;
        } else if (t.getStatus().getLevel() < t1.getStatus().getLevel()) {
            return -1;
        } else {
            return 1;
        }
    }
}
