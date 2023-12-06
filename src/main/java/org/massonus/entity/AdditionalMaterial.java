package org.massonus.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@EqualsAndHashCode(callSuper = true)
@Data
public class AdditionalMaterial extends SuperSchool implements Comparable<AdditionalMaterial>, Serializable {

    private String name;

    private Integer courseId;

    private Integer lectureId;

    private ResourceType resourceType;

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
    public int compareTo(AdditionalMaterial o) {
        return this.id - o.getId();
    }
}
