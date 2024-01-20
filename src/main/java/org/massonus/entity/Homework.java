package org.massonus.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
@Entity
@Table (name = "homework")
@Data
public class Homework implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "homework_id", nullable = false)
    private Integer id;

    @Column(name = "task")
    private String task;

    @Column(name = "deadline")
    @Transient
    private transient LocalDate deadline;

    @Column(name = "lecture_id")
    private Integer lectureId;

    @Transient
    private transient final DateTimeFormatter formatterDeadline;

    @Column(name = "deadline")
    private Date sqlDate;



    public Homework() {
        deadline = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth() + 2);
        formatterDeadline = DateTimeFormatter.ofPattern("MMM d, EEEE");
        sqlDate = Date.valueOf(deadline);
    }

    @Override
    public String toString() {

        if (Objects.isNull(formatterDeadline)) {
            return "Homework{" +
                    "id=" + id +
                    ", lectureId=" + lectureId +
                    ", task='" + task + '\'' +
                    '}';
        } else {
            return "Homework{" +
                    "id=" + id +
                    ", lectureId=" + lectureId +
                    ", task='" + task + '\'' +
                    ", deadline=" + formatterDeadline.format(deadline) +
                    '}';
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Homework homework = (Homework) o;
        return Objects.equals(id, homework.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
