package org.massonus.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
@Entity
@Table (name = "additional_material")
@Data
@ToString
public class AdditionalMaterial implements Comparable<AdditionalMaterial>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id", nullable = false)
    private Integer id;

    private String task;

    private ResourceType resourceType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    @ToString.Exclude
    private Lecture lecture;

    public AdditionalMaterial() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdditionalMaterial that = (AdditionalMaterial) o;
        return Objects.equals(task, that.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task);
    }

    @Override
    public int compareTo(AdditionalMaterial o) {
        return this.id - o.getId();
    }
}
