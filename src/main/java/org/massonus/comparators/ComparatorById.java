package org.massonus.comparators;

import org.massonus.entity.Course;

import java.util.Comparator;

public class ComparatorById implements Comparator<Course> {
    @Override
    public int compare(Course o1, Course o2) {
        return o1.getId() - o2.getId();
    }
}
