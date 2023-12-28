package org.massonus.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class AdditionalMaterial implements Comparable<AdditionalMaterial>, Serializable {

    private Integer id;

    private String task;

    private Integer courseId;

    private Integer lectureId;

    private ResourceType resourceType;

    public AdditionalMaterial() {
    }

    public AdditionalMaterial(String task, ResourceType resourceType) {
        this.task = task;
        this.resourceType = resourceType;
    }

    public AdditionalMaterial(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "AdditionalMaterial{" +
                "id=" + id +
                " name='" + task + '\'' +
                ", courseId=" + courseId +
                ", lectureId=" + lectureId +
                ", resourceType=" + resourceType +
                '}';
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
