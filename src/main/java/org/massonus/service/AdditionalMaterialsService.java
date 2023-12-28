package org.massonus.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.massonus.entity.AdditionalMaterial;
import org.massonus.entity.ResourceType;
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

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(AdditionalMaterialsService.class);

    public AdditionalMaterialsService() {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        try {
            context.setConfigLocation(AdditionalMaterialsService.class.getResource("/log4j.xml").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    AdditionalMaterial material;

    public AdditionalMaterial createElementByUser() {
        material = new AdditionalMaterial();
        System.out.println("Enter id of material");
        Scanner scanner1 = new Scanner(System.in);
        Integer id = scanner1.nextInt();
        material.setId(id);
        System.out.println("Enter name of material");
        Scanner scanner2 = new Scanner(System.in);
        String name = scanner2.nextLine();
        material.setTask(name);
        System.out.println("Enter courseId of material");
        Scanner scanner3 = new Scanner(System.in);
        Integer courseId = scanner3.nextInt();
        material.setCourseId(courseId);
        System.out.println("Enter lectureId of material");
        Scanner scanner4 = new Scanner(System.in);
        Integer lectureId = scanner4.nextInt();
        material.setLectureId(lectureId);


        System.out.println("1. To select the resourceType URL");
        System.out.println("2. To select the resourceType VIDEO");
        System.out.println("3. To select the resourceType BOOK");
        Scanner scanner5 = new Scanner(System.in);
        int resourceType = scanner5.nextInt();
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
        if (list == null) {
            System.out.println("Please create the List");
            logger.warn("array is empty");
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            AdditionalMaterial element = list.get(i);
            if (id == element.getId()) {
                System.out.println(list.get(i));
                AdditionalMaterial remove = list.remove(i);
                logger.info("element removed " + remove);
                return true;
            }
        }
        return false;
    }

    public void delete(int id) {
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


    public boolean add(List<AdditionalMaterial> materials) {
        AdditionalMaterial elementByUser = createElementByUser();
        insertIntoPrepare(elementByUser);
        logger.info("added: " + elementByUser);
        boolean add = materials.add(elementByUser);
        return add;
    }

    public void insertIntoPrepare(final AdditionalMaterial material) {
        try {

            String sql = "INSERT INTO public.additional_material(id, task, resource_type, course_id, lecture_id)VALUES (?, ?, ?, ?, ?)";

            try (Connection conn = createCon();
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, material.getId());
                preparedStatement.setString(2, material.getTask());
                preparedStatement.setString(3, material.getResourceType().toString());
                preparedStatement.setInt(4, material.getCourseId());
                preparedStatement.setInt(5, material.getLectureId());

                int rows = preparedStatement.executeUpdate();
                System.out.println("add Lines Device: " + rows);
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
