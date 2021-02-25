package com.example.tbs.comparators;

import com.example.tbs.models.Event;

import java.util.Comparator;

public class NewnessComparator implements Comparator<Event> {
    @Override
    public int compare(Event t, Event t1) {
        if (t.getId() == t1.getId()) {
            return 0;
        } else if (t.getId() < t1.getId()) {
            return -1;
        } else {
            return 1;
        }
    }
}
