package org.massonus.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.massonus.entity.AdditionalMaterial;
import org.massonus.entity.ResourceType;
import org.massonus.repo.AdditionalMaterialsRepo;
import org.massonus.repo.UniversalRepository;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AdditionalMaterialsService implements UniversalService<AdditionalMaterial>, UniversalRepository {

    private static final Logger logger = LogManager.getLogger(AdditionalMaterialsService.class);

    private AdditionalMaterialsRepo materialsRepo;

    public AdditionalMaterialsService(AdditionalMaterialsRepo materialsRepo) {
        this.materialsRepo = materialsRepo;
    }

    public AdditionalMaterialsService() {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        try {
            context.setConfigLocation(AdditionalMaterialsService.class.getResource("/log4j.xml").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    AdditionalMaterial material;

    AdditionalMaterial createElementByUser() {
        material = new AdditionalMaterial();
        int size = materialsRepo.getAllMaterials().size();
        material.setId(size + 2);
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

    public boolean removeById(List<AdditionalMaterial> list, int id) {
        for (int i = 0; i < list.size(); i++) {
            AdditionalMaterial element = list.get(i);
            if (id == element.getId()) {
                System.out.println(list.get(i));
                AdditionalMaterial remove = list.remove(i);
                deleteMaterialFromDatabase(id);
                logger.info("element removed " + remove);
                return true;
            }
        }
        return false;
    }

    public boolean add(List<AdditionalMaterial> materials, Integer lectureId) {
        AdditionalMaterial elementByUser = createElementByUser();
        elementByUser.setLectureId(lectureId);
        insertMaterialIntoDatabase(elementByUser);
        logger.info("added: " + elementByUser);
        return materials.add(elementByUser);
    }

    public void add(AdditionalMaterial material) {
        int size = materialsRepo.getAllMaterials().size();
        material.setId(size + 1);
        insertMaterialIntoDatabase(material);
    }

    private void insertMaterialIntoDatabase(final AdditionalMaterial material) {
        try {

            String sql = "INSERT INTO public.additional_material(id, task, resource_type, lecture_id)VALUES (?, ?, ?, ?)";

            try (Connection conn = createCon();
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

                preparedStatement.setInt(1, material.getId());
                preparedStatement.setString(2, material.getTask());
                preparedStatement.setString(3, material.getResourceType().toString());
                preparedStatement.setInt(4, material.getLectureId());

                int rows = preparedStatement.executeUpdate();
                System.out.println("add Lines Material: " + rows);
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }

    private void deleteMaterialFromDatabase(int id) {
        try {
            final String sql = "DELETE FROM public.additional_material WHERE id=" + id;
            try (Connection conn = createCon();
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }

    public AdditionalMaterial getById(List<AdditionalMaterial> list, int id) {
        if (list == null) {
            System.out.println("Please create an Array");
            return null;
        }

        for (AdditionalMaterial element : list) {
            if (id == element.getId()) {
                return element;
            }
        }
        return null;
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
                .sorted(Comparator.comparing(AdditionalMaterial::getTask))
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
