package org.massonus.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
@Data
public class Course extends SuperSchool implements Comparable<Course>, Serializable {

    private String name;

    private List<Person> people;

    private List<Lecture> lectures;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                " name='" + name + '\'' +
                ", people=" + people +
                ", lectures=" + lectures +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Course o) {
        return this.name.compareTo(o.name);
    }
}

