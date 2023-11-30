package org.massonus.comparators;

import org.massonus.entity.AdditionalMaterial;

import java.util.Comparator;

public class ComparatorByName implements Comparator<AdditionalMaterial> {
    @Override
    public int compare(AdditionalMaterial o1, AdditionalMaterial o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
