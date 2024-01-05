package org.massonus.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Data
@ToString
public class AdditionalMaterial implements Comparable<AdditionalMaterial>, Serializable {

    private Integer id;

    private String task;

    private ResourceType resourceType;

    private Integer lectureId;

    public AdditionalMaterial() {

    }

    public AdditionalMaterial(String task, ResourceType resourceType, Integer lectureId) {
        this.task = task;
        this.resourceType = resourceType;
        this.lectureId = lectureId;
    }

    public AdditionalMaterial(String task, ResourceType resourceType) {
        this.task = task;
        this.resourceType = resourceType;
    }

    public AdditionalMaterial(String task) {
        this.task = task;
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
