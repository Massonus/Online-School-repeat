package org.massonus.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class AdditionalMaterial extends SuperSchool implements Comparable<AdditionalMaterial>, Serializable {

    private String name;

    private Integer courseId;

    private Integer lectureId;

    private ResourceType resourceType;

    public AdditionalMaterial() {
    }

    public AdditionalMaterial(String name, ResourceType resourceType) {
        this.name = name;
        this.resourceType = resourceType;
    }

    public AdditionalMaterial(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AdditionalMaterial{" +
                "id=" + id +
                " name='" + name + '\'' +
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
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(AdditionalMaterial o) {
        return this.id - o.getId();
    }
}
