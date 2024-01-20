package org.massonus.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "course")
@Data
@ToString
public class Course implements Comparable<Course>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false)
    private Integer id;

    @Column(name = "course_name")
    private String CourseName;

    @OneToMany
    @JoinColumn(name = "person_id")
    private List<Person> people;

    @OneToMany
    @JoinColumn(name = "lecture_id")
    private List<Lecture> lectures;

    public Course() {
    }

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

