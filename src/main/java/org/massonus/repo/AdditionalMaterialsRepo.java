package org.massonus.repo;

import org.massonus.entity.AdditionalMaterial;
import org.massonus.entity.Lecture;
import org.massonus.entity.Person;
import org.massonus.entity.ResourceType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class AdditionalMaterialsRepo implements UniversalRepository {

    public List<AdditionalMaterial> getAllMaterials() {
        try {
            final String sql = "SELECT * FROM additional_material";
            try (Connection conn = createCon();
                 Statement statement = conn.createStatement()) {
                final ResultSet resultSet = statement.executeQuery(sql);

                List<AdditionalMaterial> materials = new ArrayList<>();

                while (resultSet.next()) {
                    AdditionalMaterial material = new AdditionalMaterial();
                    material.setId(resultSet.getInt("id"));
                    material.setTask(resultSet.getString("task"));

                    if (resultSet.getString("resource_type").equals("URL")) {
                        material.setResourceType(ResourceType.URL);
                    } else if (resultSet.getString("resource_type").equals("BOOK")) {
                        material.setResourceType(ResourceType.BOOK);
                    } else {
                        material.setResourceType(ResourceType.VIDEO);
                    }

                    material.setCourseId(resultSet.getInt("course_id"));
                    material.setLectureId(resultSet.getInt("lecture_id"));
                    materials.add(material);
                }

                return materials;
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
        throw new IllegalArgumentException();
    }
    /*public List<Lecture> createAndFillListAuto(List<Person> people) {
        Random random = new Random();
        int lengthMas = random.nextInt(1, 50);
        lectureSet = new HashSet<>();
        for (int i = 0; i < lengthMas; i++) {
            Lecture elementAuto = lectureService.createElementAuto();
            Person person = Optional.ofNullable(getPersonForLectureAuto(people))
                    .orElse(new Person());

            elementAuto.setPerson(person);
            elementAuto.setPersonId(person.getId());
            lectureSet.add(elementAuto);
        }
        lectures = new ArrayList<>(lectureSet);
        logger.info("List created successful, size : " + lengthMas);
        return lectures;
    }*/

}
