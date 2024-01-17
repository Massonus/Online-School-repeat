package org.massonus.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.massonus.entity.Homework;
import org.massonus.repo.HomeworkRepo;
import org.massonus.repo.UniversalRepository;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.stream.Collectors;

public class HomeworkService implements UniversalService<Homework>, UniversalRepository {
    private static final Logger logger = LogManager.getLogger(HomeworkService.class);
    private final HomeworkRepo homeworkRepo;

    public HomeworkService(HomeworkRepo homeworkRepo) {
        this.homeworkRepo = homeworkRepo;
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        try {
            context.setConfigLocation(HomeworkService.class.getResource("/log4j.xml").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }



    Homework homework;

    private Homework createElementByUser() {
        homework = new Homework();
        int size = homeworkRepo.getAllHomework().size();
        homework.setId(size + 1);

        System.out.println("Enter task of homework");
        Scanner scanner2 = new Scanner(System.in);
        String task = scanner2.nextLine();
        homework.setTask(task);

        return homework;
    }

    private Homework createElementAuto() {
        homework = new Homework();
        Random random = new Random();
        int id = random.nextInt(1, 50);
        homework.setId(id);

        if (id < 10 || id > 40) {
            homework.setTask("Doing first and second");
        } else if (id < 20 || id > 30) {
            homework.setTask("Doing last");
        } else {
            homework.setTask("No homework!!!");
        }
        return homework;
    }

    public boolean add(List<Homework> homeworks, Integer lectureId) {
        Homework elementByUser = createElementByUser();
        elementByUser.setLectureId(lectureId);
        insertHomeworkIntoDatabase(elementByUser);
        logger.info("added: " + elementByUser);
        return homeworks.add(elementByUser);
    }

    public void add(Homework homework) {
        int size = homeworkRepo.getAllHomework().size();
        homework.setId(size + 1);
        insertHomeworkIntoDatabase(homework);
    }

    public boolean removeById(List<Homework> list, int id) {
        for (int i = 0; i < list.size(); i++) {
            Homework element = list.get(i);
            if (id == element.getId()) {
                System.out.println(list.get(i));
                Homework remove = list.remove(i);
                deleteHomeworkFromDatabaseById(id);
                logger.info("element removed " + remove);
                return true;
            }
        }
        return false;
    }

    private void insertHomeworkIntoDatabase(final Homework homework) {
        try {

            String sql = "INSERT INTO public.homework(id, task, lecture_id, deadline)VALUES (?, ?, ?, ?)";

            try (Connection conn = createCon();
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

                preparedStatement.setInt(1, homework.getId());
                preparedStatement.setString(2, homework.getTask());
                preparedStatement.setInt(3, homework.getLectureId());
                preparedStatement.setDate(4, homework.getSqlDate());

                int rows = preparedStatement.executeUpdate();
                System.out.println("add Lines Homework: " + rows);
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }

    private void deleteHomeworkFromDatabaseById(int id) {
        try {
            final String sql = "DELETE FROM public.homework WHERE id=" + id;
            try (Connection conn = createCon();
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }

    public void deleteHomeworkFromDatabaseByLectureId(int id) {
        try {
            final String sql = "DELETE FROM public.homework WHERE lecture_id=" + id;
            try (Connection conn = createCon();
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }

    public Homework getById(List<Homework> list, int id) {
        if (list == null) {
            System.out.println("Please create an Array");
            return null;
        }

        for (Homework element : list) {
            if (id == element.getId()) {
                return element;
            }
        }
        return null;
    }

    public List<Homework> sortHomeworkByLectureId(List<Homework> homeworks) {
        return homeworks.stream()
                .sorted(Comparator.comparing(Homework::getLectureId))
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Homework>> groupHomeworksByLectureId(List<Homework> homeworks) {
        return homeworks.stream()
                .collect(Collectors.groupingBy(Homework::getLectureId));
    }
}
