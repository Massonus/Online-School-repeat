package org.massonus.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

@Data
@ToString
@Component
public class AdditionalMaterial implements Comparable<AdditionalMaterial>, Serializable {

    private Integer id;

    private String task;

    private ResourceType resourceType;

    private Integer lectureId;

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
