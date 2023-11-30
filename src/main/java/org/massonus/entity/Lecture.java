package org.massonus.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Data
public class Lecture extends SuperSchool implements Comparable<Lecture>, Serializable {

    private String name;

    private Person person;

    private List<Homework> homeworks;

    private List<AdditionalMaterial> materials;

    private String description;

    private int personId;

    private int courseId;

    private transient LocalDateTime lectureDate = LocalDateTime.now();

    private transient final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, EEEE HH:mm:ss");

    @Override
    public String toString() {
        return "\n Lecture{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                /*", lectureDate=" + formatter.format(lectureDate) +*/
                ", person=" + person +
                ", homeworks=" + homeworks +
                ", materials=" + materials +
                ", description='" + description + '\'' +
                ", personId=" + personId +
                ", courseId=" + courseId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecture lecture = (Lecture) o;
        return Objects.equals(getId(), lecture.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public int compareTo(Lecture o) {
        return this.getMaterials().size() - o.getMaterials().size();
    }
}
