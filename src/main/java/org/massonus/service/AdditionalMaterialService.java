package org.massonus.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.massonus.entity.AdditionalMaterial;
import org.massonus.entity.ResourceType;
import org.massonus.repo.AdditionalMaterialRepo;
import org.massonus.repo.UniversalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class AdditionalMaterialService implements UniversalService<AdditionalMaterial>, UniversalRepository {

    private static final Logger logger = LogManager.getLogger(AdditionalMaterialService.class);

    private final AdditionalMaterialRepo materialsRepo;

    @Autowired
    public AdditionalMaterialService(AdditionalMaterialRepo materialsRepo) {
        this.materialsRepo = materialsRepo;

        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        try {
            context.setConfigLocation(AdditionalMaterialService.class.getResource("/log4j.xml").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    AdditionalMaterial material;

    public AdditionalMaterial createElementByUser() {
        material = new AdditionalMaterial();
        int size = materialsRepo.getMaterialList().size();
        material.setId(size + 1);
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

        return material;
    }

    AdditionalMaterial createElementAuto() {
        material = new AdditionalMaterial();
        int id = materialsRepo.getMaterialList().size() + 1;
        material.setId(id);

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
