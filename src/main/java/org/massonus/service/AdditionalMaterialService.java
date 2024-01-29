package org.massonus.service;

import org.massonus.entity.AdditionalMaterial;
import org.massonus.entity.Lecture;
import org.massonus.entity.ResourceType;
import org.massonus.repo.AdditionalMaterialRepo;
import org.massonus.repo.LectureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class AdditionalMaterialService implements UniversalService<AdditionalMaterial> {

    private final AdditionalMaterialRepo materialsRepo;
    private final LectureRepo lectureRepo;

    @Autowired
    public AdditionalMaterialService(AdditionalMaterialRepo materialsRepo, LectureRepo lectureRepo) {
        this.materialsRepo = materialsRepo;
        this.lectureRepo = lectureRepo;
    }

    AdditionalMaterial material;

    public AdditionalMaterial createElementByUserForm(String task, ResourceType resourceType, Long lectureId) {
        material = new AdditionalMaterial();

        material.setTask(task);
        material.setResourceType(resourceType);
        Lecture lectureById = lectureRepo.findById(lectureId).orElse(null);
        material.setLecture(lectureById);

        return material;
    }

    public AdditionalMaterial createElementByUser() {
        material = new AdditionalMaterial();
        System.out.println("Enter name of material");
        Scanner scanner1 = new Scanner(System.in);
        String name = scanner1.nextLine();
        material.setTask(name);

        System.out.println("1. To select the resourceType URL");
        System.out.println("2. To select the resourceType VIDEO");
        System.out.println("3. To select the resourceType BOOK");
        Scanner scanner2 = new Scanner(System.in);
        int resourceType = scanner2.nextInt();
        if (resourceType == 1) {
            material.setResourceType(ResourceType.URL);
        } else if (resourceType == 2) {
            material.setResourceType(ResourceType.VIDEO);
        } else if (resourceType == 3) {
            material.setResourceType(ResourceType.BOOK);
        } else {
            System.out.println("Incorrect");
        }

        System.out.println("Choose the lecture for the material");
        lectureRepo.findAll().forEach(System.out::println);
        Scanner scanner3 = new Scanner(System.in);
        Long id = scanner3.nextLong();
        Lecture lectureById = lectureRepo.findById(id).get();
        material.setLecture(lectureById);

        return material;
    }

    AdditionalMaterial createElementAuto() {
        material = new AdditionalMaterial();
        long id = materialsRepo.findAll().size() + 1L;

        material.setTask("Material " + id);

        if (id % 2 == 0) {
            material.setResourceType(ResourceType.URL);
        } else if (id % 3 == 2) {
            material.setResourceType(ResourceType.VIDEO);
        } else {
            material.setResourceType(ResourceType.BOOK);
        }

        return material;
    }

    public AdditionalMaterial materialRefactor(final AdditionalMaterial material) {
        System.out.println("Write down the new information: ");

        System.out.println("Resource type: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. To select the resourceType URL");
        System.out.println("2. To select the resourceType VIDEO");
        System.out.println("3. To select the resourceType BOOK");

        int resourceType = scanner.nextInt();

        if (resourceType == 1) {
            material.setResourceType(ResourceType.URL);
        } else if (resourceType == 2) {
            material.setResourceType(ResourceType.VIDEO);
        } else if (resourceType == 3) {
            material.setResourceType(ResourceType.BOOK);
        } else {
            System.out.println("Incorrect");
        }

        System.out.println("Task: ");
        Scanner scanner1 = new Scanner(System.in);
        String task = scanner1.nextLine();
        material.setTask(task);

        System.out.println("Choose the lecture for the material");
        lectureRepo.findAll().forEach(System.out::println);
        Scanner scanner2 = new Scanner(System.in);
        Long id = scanner2.nextLong();
        Lecture lectureById = lectureRepo.findById(id).get();
        material.setLecture(lectureById);

        return material;
    }

    public void saveMaterial(final AdditionalMaterial material) {
        materialsRepo.save(material);
    }

    public List<AdditionalMaterial> getMaterialList() {
        return materialsRepo.findAll();
    }

    public Optional<AdditionalMaterial> getMaterialById(final long id) {
        return materialsRepo.findById(id);
    }

    public void deleteMaterial(final long id) {
        materialsRepo.deleteById(id);
    }

    public List<AdditionalMaterial> sortMaterialsById(List<AdditionalMaterial> materials) {
        return materials.stream()
                .sorted(Comparator.comparing(AdditionalMaterial::getId))
                .collect(Collectors.toList());
    }

    public List<AdditionalMaterial> sortMaterialsByType(List<AdditionalMaterial> materials) {
        return materials.stream()
                .sorted(Comparator.comparing(a -> a.getResourceType().toString()))
                .collect(Collectors.toList());
    }

    public List<AdditionalMaterial> sortMaterialsByName(List<AdditionalMaterial> materials) {
        return materials.stream()
                .sorted(Comparator.comparing(AdditionalMaterial::getTask))
                .collect(Collectors.toList());
    }

    /*public List<AdditionalMaterial> sortMaterialsByLectureId(List<AdditionalMaterial> materials) {
        return materials.stream()
                .sorted(Comparator.comparing(AdditionalMaterial::getLectureId))
                .collect(Collectors.toList());
    }*/

    /*public Map<Integer, List<AdditionalMaterial>> groupingMaterialsAsMap(List<AdditionalMaterial> materials) {
        return materials.stream()
                .collect(Collectors.groupingBy(AdditionalMaterial::getLectureId));
    }*/
}
