package org.massonus.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.massonus.entity.AdditionalMaterial;
import org.massonus.repo.AdditionalMaterialRepo;
import org.massonus.service.AdditionalMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

@Component
public class AdditionalMaterialView {
    private static final Logger logger = LogManager.getLogger(AdditionalMaterialView.class);
    private final AdditionalMaterialService materialService;
    private final AdditionalMaterialRepo materialRepo;

    @Autowired
    public AdditionalMaterialView(AdditionalMaterialService materialService, AdditionalMaterialRepo materialRepo) {
        this.materialService = materialService;
        this.materialRepo = materialRepo;

        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        try {
            context.setConfigLocation(AdditionalMaterialView.class.getResource("/log4j.xml").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void workWithMaterials() {
        List<AdditionalMaterial> materials = materialRepo.getMaterialList();
        while (true) {


            System.out.println("\n Make your choice (use only numbers)");
            System.out.println("1. Print all Materials");
            System.out.println("2. Add new Material");
            System.out.println("3. Change information about material by id");
            System.out.println("4. Delete material");
            System.out.println("5. Get size of array");
            System.out.println("6. Sort by id");
            System.out.println("7. Sort by type");
            System.out.println("8. Sort by name");
            System.out.println("9. Sort all materials by lectureID");
            System.out.println("10. Print all materials as Map");
            System.out.println("11. Get key of map");
            System.out.println("0. Return");

            Scanner scanner = new Scanner(System.in);
            String select = scanner.nextLine();

            switch (select) {
                case "1":
                    materials = materialRepo.getMaterialList();
                    materialService.printAll(materials);
                    break;

                case "2":
                    final AdditionalMaterial elementByUser = materialService.createElementByUser();
                    materialRepo.addMaterial(elementByUser);
                    break;

                case "3":
                    int id = materialService.choiceId();
                    AdditionalMaterial refactoredMaterial = materialService.materialRefactor(materialRepo.getMaterialById(id));
                    materialRepo.updateMaterial(refactoredMaterial);
                    break;

                case "4":
                    materialRepo.deleteMaterial(materialRepo.getMaterialById(materialService.choiceId()));
                    break;

                case "5":
                    System.out.println(materials.size());
                    logger.info("checked size");
                    break;

                case "6":
                    materials = materialService.sortMaterialsById(materials);
                    System.out.println(materials);
                    logger.info("sorted by id");
                    break;

                case "7":
                    materials = materialService.sortMaterialsByType(materials);
                    System.out.println(materials);
                    logger.info("sorted by type");
                    break;

                case "8":
                    materials = materialService.sortMaterialsByName(materials);
                    System.out.println(materials);
                    logger.info("sorted by name");
                    break;

                /*case "9":
                    allMaterials = materialService.sortMaterialsByLectureId(allMaterials);
                    logger.info("sorted by lectureId");
                    break;

                case "10":
                    materialsAsMap.forEach((k, v) -> System.out.println("lectureID: " + k + " " + v));
                    logger.info("printed");
                    break;

                case "11":
                    Scanner scanner1 = new Scanner(System.in);
                    int i = scanner1.nextInt();
                    List<AdditionalMaterial> materials = materialsAsMap.get(i);
                    materials.forEach(System.out::println);
                    logger.info("printed map by key: " + i);
                    break;*/

                case "0":
                    logger.info("returned to CourseController");
                    return;
            }
        }
    }
}
