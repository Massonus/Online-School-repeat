package org.massonus.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
public class Homework implements Serializable {

    private Integer id;

    private String task;

    private transient LocalDate deadline;

    private Integer lectureId;

    private transient final DateTimeFormatter formatterDeadline;

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
