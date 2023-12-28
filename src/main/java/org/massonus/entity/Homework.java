package org.massonus.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
public class Homework implements Serializable {

    private Integer id;

    private String task;

    private transient final LocalDateTime deadline;

    private Integer lectureId;

    private transient final DateTimeFormatter formatterDeadline;

    public Homework() {
        deadline = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), 12, 0, LocalTime.now().getSecond());
        formatterDeadline = DateTimeFormatter.ofPattern("MMM d, HH:mm");
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id=" + id +
                ", lectureId=" + lectureId +
                ", task='" + task + '\'' +
                ", deadline=" + formatterDeadline.format(deadline) +
                '}';
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
