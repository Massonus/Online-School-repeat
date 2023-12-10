package org.massonus.service;

import org.massonus.entity.AdditionalMaterial;
import org.massonus.entity.ResourceType;

import java.util.*;
import java.util.stream.Collectors;

public class AdditionalMaterialsService implements UniversalService<AdditionalMaterial> {
    AdditionalMaterial material;

    public AdditionalMaterial createElementByUser() {
        material = new AdditionalMaterial();
        System.out.println("Enter id of material");
        Scanner scanner1 = new Scanner(System.in);
        int id = scanner1.nextInt();
        material.setId(id);

        System.out.println("Enter name of material");
        Scanner scanner2 = new Scanner(System.in);
        String name = scanner2.nextLine();
        material.setName(name);
        material.setCourseId(CourseService.courseId);

        System.out.println("1. To select the resourceType URL");
        System.out.println("2. To select the resourceType VIDEO");
        System.out.println("2. To select the resourceType BOOK");
        Scanner scanner3 = new Scanner(System.in);
        int resourceType = scanner3.nextInt();
        if (resourceType == 1) {
            material.setResourceType(ResourceType.URL);
        } else if (resourceType == 2) {
            material.setResourceType(ResourceType.VIDEO);
        } else if (resourceType == 3) {
            material.setResourceType(ResourceType.BOOK);
        } else {
            System.out.println("Incorrect");
        }

        return material;
    }

    public AdditionalMaterial createElementAuto() {
        material = new AdditionalMaterial();
        Random random = new Random();
        int id = random.nextInt(1, 70);
        material.setId(id);

        if (id <= 15) {
            material.setName("Learn new information on the Wiki");
            material.setResourceType(ResourceType.URL);
        } else if (id > 16 && id < 36) {
            material.setName("Learn new information on the YouTube");
            material.setResourceType(ResourceType.VIDEO);
        } else {
            material.setName("Learn new information in the Book");
            material.setResourceType(ResourceType.BOOK);
        }
        material.setCourseId(CourseService.courseId);

        return material;
    }

    public boolean add(List<AdditionalMaterial> materials) {
        if (choice().equals("2")) {
            AdditionalMaterial elementAuto = createElementAuto();
            logger.info("added: " + elementAuto);
            materials.add(elementAuto);
            return true;
        } else if (choice().equals("1")) {
            AdditionalMaterial elementByUser = createElementByUser();
            logger.info("added: " + elementByUser);
            materials.add(elementByUser);
            return true;
        }
        return false;
    }

    public boolean add(List<AdditionalMaterial> materials, int index) {
        if (choice().equals("2")) {
            AdditionalMaterial elementAuto = createElementAuto();
            logger.info("added: " + elementAuto);
            materials.add(index, elementAuto);
            return true;
        } else if (choice().equals("1")) {
            AdditionalMaterial elementByUser = createElementByUser();
            logger.info("added: " + elementByUser);
            materials.add(index, elementByUser);
            return true;
        }
        return false;
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
