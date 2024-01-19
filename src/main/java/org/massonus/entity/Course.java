package org.massonus.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@ToString
public class Course implements Comparable<Course>, Serializable {

    private Integer id;

    private String CourseName;

    private List<Person> people;

    private List<Lecture> lectures;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(CourseName, course.CourseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CourseName);
    }

    @Override
    public int compareTo(Course o) {
        return this.CourseName.compareTo(o.CourseName);
    }
}

