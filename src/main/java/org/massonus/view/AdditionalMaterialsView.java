package org.massonus.view;

import org.massonus.entity.AdditionalMaterial;
import org.massonus.entity.Course;
import org.massonus.entity.Lecture;
import org.massonus.entity.Person;
import org.massonus.log.Logger;
import org.massonus.service.AdditionalMaterialsService;
import org.massonus.service.CourseService;
import org.massonus.service.LectureService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AdditionalMaterialsView {
    private static final Logger logger = new Logger("AdditionalMaterialsView");
    private final AdditionalMaterialsService materialService;
    private final LectureService lectureService;
    private final CourseService courseService = new CourseService();

    public AdditionalMaterialsView(AdditionalMaterialsService materialService, LectureService lectureService) {
        this.materialService = materialService;
        this.lectureService = lectureService;
    }

    public void workWithMaterials(List<Course> courses) {

        while (true) {
            System.out.println("\n Make your choice (use only numbers)");
            System.out.println("1. To work with special materials");
            System.out.println("2. To work with all materials");
            System.out.println("0. To return");

            Scanner scanner = new Scanner(System.in);
            int select;

            try {
                select = scanner.nextInt();
            } catch (Exception e) {
                for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                    System.out.println(stackTraceElement);
                }
                select = 69;
            }

            switch (select) {
                case 1:
                    List<Lecture> allLectures = courseService.getAllLectures(courses);
                    allLectures.forEach(System.out::println);
                    int id = lectureService.choiceId();
                    Lecture specialLectureForMaterial = lectureService.getById(allLectures, id);
                    List<AdditionalMaterial> materials = specialLectureForMaterial.getMaterials();
                    workWithSpecialMaterial(materials, specialLectureForMaterial.getId());
                    break;

                case 2:
                    List<AdditionalMaterial> allMaterials = courseService.getAllMaterials(courses);
                    workWithAllMaterials(allMaterials);
                    break;

                case 69:
                    logger.warning("incorrect symbol: " + select);
                    System.out.println("Incorrect number");
                    break;
                case 0:
                    logger.info("returned to CourseController");
                    return;
            }
        }
    }


    private void workWithSpecialMaterial(List<AdditionalMaterial> materials, Integer lectureId) {
        while (true) {
            System.out.println("\n What you want to do with Material?");
            System.out.println("1. Print all Materials");
            System.out.println("2. Add new Material");
            System.out.println("3. To remove element");
            System.out.println("4. To check that array is Empty");
            System.out.println("5. To get size of array");
            System.out.println("6. To sort by id");
            System.out.println("7. To sort by type");
            System.out.println("8. To sort by name");
            System.out.println("0. To return");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    materialService.printAll(materials);
                    break;

                case "2":
                    materialService.add(materials, lectureId);
                    break;

                case "3":
                    int id = materialService.choiceId();
                    materialService.removeById(materials, id);
                    break;

                case "4":
                    System.out.println(materials.isEmpty());
                    logger.info("checked for empty");
                    break;

                case "5":
                    System.out.println(materials.size());
                    logger.info("checked size");
                    break;

                case "6":
                    materials = materialService.sortMaterialsById(materials);
                    logger.info("sorted by id");
                    break;

                case "7":
                    materials = materialService.sortMaterialsByType(materials);
                    logger.info("sorted by type");
                    break;

                case "8":
                    materials = materialService.sortMaterialsByName(materials);
                    logger.info("sorted by name");
                    break;

                case "0":
                    logger.info("returned");
                    return;
            }
        }
    }

    private void workWithAllMaterials(List<AdditionalMaterial> allMaterials) {

        Map<Integer, List<AdditionalMaterial>> materialsAsMap = materialService.groupingMaterialsAsMap(allMaterials);

        while (true) {
            System.out.println("\n1. To print all materials as List");
            System.out.println("2. To sort all materials by lectureID");
            System.out.println("3. To print all materials as Map");
            System.out.println("4. To get key of map");
            System.out.println("0. To return");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    allMaterials.forEach(System.out::println);
                    logger.info("returned");
                    break;

                case "2":
                    allMaterials = materialService.sortMaterialsByLectureId(allMaterials);
                    logger.info("sorted by lectureId");
                    break;

                case "3":
                    materialsAsMap.forEach((k, v) -> System.out.println("lectureID: " + k + " " + v));
                    logger.info("printed");
                    break;

                case "4":
                    Scanner scanner1 = new Scanner(System.in);
                    int i = scanner1.nextInt();
                    List<AdditionalMaterial> materials = materialsAsMap.get(i);
                    materials.forEach(System.out::println);
                    logger.info("printed map by key: " + i);
                    break;

                case "0":
                    return;
            }
        }
    }
}
