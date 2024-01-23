package org.massonus.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "lecture")
@Data
public class Lecture implements Comparable<Lecture>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id", nullable = false)
    private Integer id;

    private String subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @ToString.Exclude
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    @ToString.Exclude
    private Person person;

    @OneToMany(mappedBy = "lecture",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    private List<Homework> homeworks = new ArrayList<>();

    @OneToMany(mappedBy = "lecture",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    private List<AdditionalMaterial> materials = new ArrayList<>();

    private String description;

    @Column(name = "lecture_date")
    private Date lectureDateSql;

    @Transient
    private transient LocalDate lectureDate;

    @Transient
    private transient DateTimeFormatter formatter;

    public Lecture() {
        lectureDate = LocalDate.now();
        formatter = DateTimeFormatter.ofPattern("MMM d, EEEE");
        lectureDateSql = Date.valueOf(lectureDate);
    }

    @Override
    public String toString() {

        if (Objects.isNull(formatter)) {
            return "\n Lecture{" +
                    "id=" + id +
                    ", name='" + subject + '\'' +
                    /*", person=" + person +
                    ", homeworks=" + homeworks +*/
                    ", materials=" + materials +
                    ", description='" + description + '\'' +
                    '}';
        } else {
            return "\n Lecture{" +
                    "id=" + id +
                    ", name='" + subject + '\'' +
                    ", lectureDate=" + formatter.format(lectureDate) +
                    /*", person=" + person +
                    ", homeworks=" + homeworks +*/
                    ", materials=" + materials +
                    ", description='" + description + '\'' +
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
