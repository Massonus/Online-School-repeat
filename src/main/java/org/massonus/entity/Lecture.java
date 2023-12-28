package org.massonus.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Data
public class Lecture implements Comparable<Lecture>, Serializable {

    private Integer id;

    private String subject;

    private Person person;

    private List<Homework> homeworks;

    private List<AdditionalMaterial> materials;

    private String description;

    private Integer teacherId;

    private Integer courseId;

    private transient LocalDate lectureDate;

    private transient final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, EEEE");

    public Lecture() {
        lectureDate = LocalDate.now();
    }

    public Lecture(Integer id, String subject, Person person, List<AdditionalMaterial> materials) {
        setId(id);
        this.subject = subject;
        this.person = person;
        this.materials = materials;
    }

    public Lecture(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "\n Lecture{" +
                "id=" + id +
                ", name='" + subject + '\'' +
                ", lectureDate=" + formatter.format(lectureDate) +
                ", person=" + person +
                ", homeworks=" + homeworks +
                ", materials=" + materials +
                ", description='" + description + '\'' +
                ", teacherId=" + teacherId +
                ", courseId=" + courseId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecture lecture = (Lecture) o;
        return Objects.equals(id, lecture.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Lecture o) {
        return this.getMaterials().size() - o.getMaterials().size();
    }
}
