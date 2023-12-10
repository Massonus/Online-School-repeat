package org.massonus.repo;

import org.massonus.entity.AdditionalMaterial;
import org.massonus.service.AdditionalMaterialsService;

import java.util.*;

public class AdditionalMaterialsRepo implements UniversalRepository {
    private final AdditionalMaterialsService materialsService = new AdditionalMaterialsService();
    private List<AdditionalMaterial> materials;
    private Set<AdditionalMaterial> materialSet;

    public List<AdditionalMaterial> createAndFillListAuto(int lectureId) {
        Random random = new Random();
        materialSet = new HashSet<>();
        int lengthMas = random.nextInt(1, 30);
        for (int i = 0; i < lengthMas; i++) {
            AdditionalMaterial element = materialsService.createElementAuto();
            element.setLectureId(lectureId);
            materialSet.add(element);
        }
        materials = new ArrayList<>(materialSet);
        return materials;
    }

    public List<AdditionalMaterial> createAndFillListByUser(int lectureId) {
        System.out.println("Additional material:");
        int lengthMas = lengthMasByUser();
        materialSet = new HashSet<>();
        for (int i = 0; i < lengthMas; i++) {
            AdditionalMaterial element = materialsService.createElementByUser();
            element.setLectureId(lectureId);
            materialSet.add(element);
        }
        materials = new ArrayList<>(materialSet);
        return materials;
    }
}
