package org.massonus.repo;

import org.massonus.entity.Homework;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class HomeworkRepo implements UniversalRepository {

    public List<Homework> getAllHomework() {
        try {
            final String sql = "SELECT * FROM homework";
            try (Connection conn = createCon();
                 Statement statement = conn.createStatement()) {
                final ResultSet resultSet = statement.executeQuery(sql);

                final List<Homework> homeworkList = new ArrayList<>();

                while (resultSet.next()) {
                    Homework homework = new Homework();
                    homework.setId(resultSet.getInt("id"));
                    homework.setTask(resultSet.getString("task"));
                    homework.setLectureId(resultSet.getInt("lecture_id"));
                    homeworkList.add(homework);
                }

                return homeworkList;
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
        throw new IllegalArgumentException();
    }
}
