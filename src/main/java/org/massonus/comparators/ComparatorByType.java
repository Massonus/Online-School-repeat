package org.massonus.comparators;

import org.massonus.entity.AdditionalMaterial;

import java.util.Comparator;

public class ComparatorByType implements Comparator<AdditionalMaterial> {
    @Override
    public int compare(AdditionalMaterial o1, AdditionalMaterial o2) {
        return o1.getResourceType().toString().compareTo(o2.getResourceType().toString());
    }
}
