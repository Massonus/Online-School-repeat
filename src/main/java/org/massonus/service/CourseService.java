package org.massonus.service;

import org.massonus.entity.*;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class CourseService implements UniversalService<Course> {

    public boolean serial(Course course) {
        final File file = new File("src/main/java/org/massonus/service/serialization.txt");
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(course);
            System.out.println("serial completed successfully \n" + course);
        } catch (IOException e) {
            Arrays.stream(e.getStackTrace()).forEach(System.out::println);
        }
        return true;
    }

    public Course deSer() {
        final File file = new File("src/main/java/org/massonus/service/serialization.txt");
        Course deSer = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            deSer = (Course) inputStream.readObject();
            System.out.println("deSer completed successfully ");
        } catch (IOException | ClassNotFoundException e) {
            Arrays.stream(e.getStackTrace()).forEach(System.out::println);
        }
        return deSer;
    }

    public boolean removeById(List<Course> list, int id) {
        if (list == null) {
            System.out.println("Please create the List");
            logger.warning("array is empty");
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            Course element = list.get(i);
            if (id == element.getId()) {
                System.out.println(list.get(i));
                Course remove = list.remove(i);
                logger.info("element removed " + remove);
                return true;
            }
        }
        return false;
    }

    public Course getById(List<Course> list, int id) {
        if (list == null) {
            System.out.println("Please create an Array");
            return null;
        }

        for (Course element : list) {
            if (id == element.getId()) {
                return element;
            }
        }
        return null;
    }

    public List<AdditionalMaterial> getAllMaterials(List<Course> courses) {
        List<AdditionalMaterial> materials = getAllLectures(courses).stream()
                .map(Lecture::getMaterials)
                .flatMap(Collection::stream).toList();
        logger.info("List of materials created " + materials.size());
        return materials;
    }

    public List<Homework> getAllHomework(List<Course> courses) {
        List<Homework> homeworkList = getAllLectures(courses).stream()
                .map(Lecture::getHomeworks)
                .flatMap(Collection::stream).toList();
        logger.info("List of homework created " + homeworkList.size());
        return homeworkList;
    }

    public List<Lecture> getAllLectures(List<Course> courses) {
        List<Lecture> lectures = courses.stream()
                .map(Course::getLectures)
                .flatMap(Collection::stream)
                .toList();
        logger.info("List of lectures created " + lectures.size());
        return lectures;
    }

    public List<Person> getAllPeople(List<Course> courses) {
        List<Person> people = courses.stream()
                .map(Course::getPeople)
                .flatMap(Collection::stream)
                .toList();
        logger.info("List of people created " + people.size());
        return people;
    }

    public List<Course> sortCoursesById(List<Course> courses) {
        return courses.stream()
                .sorted(Comparator.comparing(Course::getId))
                .toList();
    }
}

