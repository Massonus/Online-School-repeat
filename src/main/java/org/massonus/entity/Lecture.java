package org.massonus.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "lecture")
@Data
public class Lecture implements Comparable<Lecture>, Serializable {

    @Id
    @Column(name = "lecture_id")
    private Integer id;

    @Column(name = "subject")
    private String subject;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany
    @JoinColumn(name = "homework_id")
    private List<Homework> homeworks;

    @OneToMany
    @JoinColumn(name = "material_id")
    private List<AdditionalMaterial> materials;

    @Column(name = "description")
    private String description;

    @Column(name = "teacher_id")
    private Integer teacherId;

    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "lecture_date")
    private Date lectureDateSql;

    @Transient
    private transient LocalDate lectureDate;

    @Transient
    private transient DateTimeFormatter formatter;

    public Lecture() {
        lectureDate = LocalDate.now();
        formatter = DateTimeFormatter.ofPattern("MMM d, EEEE");
    }

    @Override
    public String toString() {

        if (Objects.isNull(formatter)) {
            return "\n Lecture{" +
                    "id=" + id +
                    ", name='" + subject + '\'' +
                    ", person=" + person +
                    ", homeworks=" + homeworks +
                    ", materials=" + materials +
                    ", description='" + description + '\'' +
                    ", teacherId=" + teacherId +
                    ", courseId=" + courseId +
                    '}';
        } else {
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
