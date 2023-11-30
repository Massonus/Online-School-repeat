package org.massonus.comparators;

import org.massonus.entity.Homework;

import java.util.Comparator;

public class ComparatorHomeworkByLectureId implements Comparator<Homework> {
    @Override
    public int compare(Homework o1, Homework o2) {
        return o1.getLectureId() - o2.getLectureId();
    }
}
