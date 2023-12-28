package org.massonus.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
public class Course implements Comparable<Course>, Serializable {

    private Integer id;

    private String course_name;

    private List<Person> people;

    private List<Lecture> lectures;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                " name='" + course_name + '\'' +
                ", people=" + people +
                ", lectures=" + lectures +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(course_name, course.course_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course_name);
    }

    @Override
    public int compareTo(Course o) {
        return this.course_name.compareTo(o.course_name);
    }
}

