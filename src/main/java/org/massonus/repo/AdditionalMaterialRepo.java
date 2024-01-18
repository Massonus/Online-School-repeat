package org.massonus.repo;

import org.massonus.entity.AdditionalMaterial;
import org.massonus.entity.ResourceType;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Repository
public class AdditionalMaterialRepo implements UniversalRepository {

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

}
