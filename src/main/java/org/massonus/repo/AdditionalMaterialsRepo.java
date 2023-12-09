package org.massonus.repo;

import org.massonus.entity.AdditionalMaterial;
import org.massonus.service.AdditionalMaterialsService;

import java.util.*;
import java.util.stream.Collectors;

public class AdditionalMaterialsRepo implements AboutRepo<AdditionalMaterial> {
    final AdditionalMaterialsService materialsService = new AdditionalMaterialsService();
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
        int lengthMas = lengthMas();
        materialSet = new HashSet<>();
        for (int i = 0; i < lengthMas; i++) {
            AdditionalMaterial element = materialsService.createElementByUser();
            element.setLectureId(lectureId);
            materialSet.add(element);
        }
        materials = new ArrayList<>(materialSet);
        return materials;
    }

    public void add() {
        if (choice().equals("2")) {
            AdditionalMaterial elementAuto = materialsService.createElementAuto();
            logger.info("added: " + elementAuto);
            materials.add(elementAuto);
        } else {
            AdditionalMaterial elementByUser = materialsService.createElementByUser();
            logger.info("added: " + elementByUser);
            materials.add(elementByUser);
        }
    }

    public void add(int index) {
        if (choice().equals("2")) {
            AdditionalMaterial elementAuto = materialsService.createElementAuto();
            logger.info("added: " + elementAuto);
            materials.add(index, elementAuto);
        } else {
            AdditionalMaterial elementByUser = materialsService.createElementByUser();
            logger.info("added: " + elementByUser);
            materials.add(index, elementByUser);
        }
    }

    public List<AdditionalMaterial> sortMaterialsById(List<AdditionalMaterial> materials) {
        return materials.stream()
                .sorted(Comparator.comparing(AdditionalMaterial::getId))
                .toList();
    }

    public List<AdditionalMaterial> sortMaterialsByType(List<AdditionalMaterial> materials) {
        return materials.stream()
                .sorted(Comparator.comparing(a -> a.getResourceType().toString()))
                .toList();
    }

    public List<AdditionalMaterial> sortMaterialsByName(List<AdditionalMaterial> materials) {
        return materials.stream()
                .sorted(Comparator.comparing(AdditionalMaterial::getName))
                .toList();
    }

    public List<AdditionalMaterial> sortMaterialsByLectureId(List<AdditionalMaterial> materials) {
        return materials.stream()
                .sorted(Comparator.comparing(AdditionalMaterial::getLectureId))
                .toList();
    }

    public Map<Integer, List<AdditionalMaterial>> groupingMaterialsAsMap(List<AdditionalMaterial> materials) {
        return materials.stream()
                .collect(Collectors.groupingBy(AdditionalMaterial::getLectureId));
    }
}
