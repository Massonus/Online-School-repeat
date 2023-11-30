package org.massonus.view;

import org.massonus.comparators.ComparatorByName;
import org.massonus.comparators.ComparatorByType;
import org.massonus.entity.AdditionalMaterial;
import org.massonus.log.Logger;
import org.massonus.repo.AdditionalMaterialsRepo;

import java.util.*;
import java.util.stream.Collectors;

public class AdditionalMaterialsView {
    final static Logger logger = new Logger("AdditionalMaterialsView");
    final AdditionalMaterialsRepo materialsRepo = new AdditionalMaterialsRepo();

    public void workWithMaterial() {

        Comparator<AdditionalMaterial> comparatorByType = new ComparatorByType();

        Comparator<AdditionalMaterial> comparatorByName = new ComparatorByName();

        while (true) {
            System.out.println("\n What you want to do with Material?");
            System.out.println("1. Print all Materials");
            System.out.println("2. Add new Material by index");
            System.out.println("3. Add new Material to the end");
            System.out.println("4. To remove element");
            System.out.println("5. To check that array is Empty");
            System.out.println("6. To get size of array");
            System.out.println("7. To sort by id");
            System.out.println("8. To sort by type");
            System.out.println("9. To sort by name");
            System.out.println("0. To return");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    materialsRepo.getAll(AdditionalMaterialsRepo.materials);
                    break;

                case "2":
                    int index = materialsRepo.choiceIndex();
                    materialsRepo.add(index);
                    break;

                case "3":
                    materialsRepo.add();
                    break;

                case "4":
                    materialsRepo.removeById(AdditionalMaterialsRepo.materials);
                    break;

                case "5":
                    System.out.println(AdditionalMaterialsRepo.materials.isEmpty());
                    logger.info("checked for empty");
                    break;

                case "6":
                    System.out.println(AdditionalMaterialsRepo.materials.size());
                    logger.info("checked size");
                    break;

                case "7":
                    Collections.sort(AdditionalMaterialsRepo.materials);
                    logger.info("sorted by id");
                    break;

                case "8":
                    AdditionalMaterialsRepo.materials.sort(comparatorByType);
                    logger.info("sorted by type");
                    break;

                case "9":
                    AdditionalMaterialsRepo.materials.sort(comparatorByName);
                    logger.info("sorted by name");
                    break;

                case "0":
                    logger.info("returned");
                    return;
            }
        }
    }

    public static void workWithAllMaterials(List<AdditionalMaterial> allMaterials) {
        Map<Integer, List<AdditionalMaterial>> materialMap = allMaterials.stream()
                .collect(Collectors.groupingBy(AdditionalMaterial::getLectureId));

        while (true) {
            System.out.println("\n1. To print all materials as List");
            System.out.println("2. To sort all materials");
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
                    List<AdditionalMaterial> list = allMaterials.stream()
                            .sorted(Comparator.comparing(AdditionalMaterial::getLectureId))
                            .toList();
                    list.forEach(System.out::println);

                    logger.info("sorted by lectureId");
                    break;

                case "3":
                    materialMap.forEach((k, v) -> System.out.println("lectureID: " + k + " " + v));
                    logger.info("printed");
                    break;

                case "4":
                    Scanner scanner1 = new Scanner(System.in);
                    int i = scanner1.nextInt();
                    List<AdditionalMaterial> materials = materialMap.get(i);
                    materials.forEach(System.out::println);
                    logger.info("printed map by key: " + i);
                    break;

                case "0":
                    return;
            }
        }
    }
}
