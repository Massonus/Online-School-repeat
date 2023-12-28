package org.massonus.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
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

    private int teacherId;

    private int courseId;

    private transient LocalDateTime lectureDate = LocalDateTime.now();

    private transient final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, EEEE HH:mm:ss");

    public Lecture() {
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
                ", personId=" + teacherId +
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
