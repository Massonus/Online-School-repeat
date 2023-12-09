package org.massonus.repo;

import org.massonus.entity.AdditionalMaterial;
import org.massonus.service.AdditionalMaterialsService;

import java.util.*;

public class AdditionalMaterialsRepo implements AboutRepo<AdditionalMaterial> {
    final AdditionalMaterialsService materialsService = new AdditionalMaterialsService();
    private List<AdditionalMaterial> materials;

    public List<AdditionalMaterial> createAndFillListAuto(int lectureId) {
        Random random = new Random();
        Set<AdditionalMaterial> materialSet = new HashSet<>();
        int lengthMas = random.nextInt(1, 30);
        for (int i = 0; i < lengthMas; i++) {
            AdditionalMaterial element = materialsService.createElementAuto();
            element.setLectureId(lectureId);
            materialSet.add(element);
        }
        materials = new ArrayList<>(materialSet);
        return materials;
    }

    public void createAndFillListByUser(int lectureId) {
        System.out.println("Additional material:");
        int lengthMas = lengthMas();
        materials = new ArrayList<>();
        for (int i = 0; i < lengthMas; i++) {
            AdditionalMaterial element = materialsService.createElementByUser();
            element.setLectureId(lectureId);
            materials.add(element);
        }
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
}
