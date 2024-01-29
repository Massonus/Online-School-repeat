package org.massonus.service.controlWork;

import org.massonus.entity.Person;

import java.util.*;

public class ControlWorkService {

    private void fillMapTimesOfWork(Map<Integer, Integer> timesOfWork, Map<Integer, Person> people) {
        int time;
        for (int i = 1; i < 10; i++) {
            Random random = new Random();
            time = random.nextInt(8, 14);
            timesOfWork.put(people.get(i).getId().intValue(), time);
            System.out.println("Student " + people.get(i).getId() + " did the control work in " + time);
        }
    }

    private void searchBestTime(Map<Integer, Integer> timesOfWork) {
        int limitTime = 12;
        for (int i = 1; i < timesOfWork.size(); i++) {
            if (timesOfWork.get(i) < limitTime) {
                limitTime = timesOfWork.get(i);
            }
        }
        for (int i = 1; i < timesOfWork.size(); i++) {
            if (timesOfWork.get(i) == limitTime) {
                System.out.println("Student " + (i) + " has the best time = " + timesOfWork.get(i));
            }
        }

    }

    private void searchWorseTime(Map<Integer, Integer> timesOfWork) {
        for (int i = 1; i < timesOfWork.size(); i++) {
            if (timesOfWork.get(i) > 12) {
                System.out.println("Student " + (i) + " time = " + timesOfWork.get(i) + " couldn't keep");
            }
        }
    }

    private void createAndFillArrayThread(Map<Integer, Person> people, List<Thread> threads) {
        List<Integer> tasks = fillTasksList();

        int index;
        int max = 9;
        for (int i = 1; i < 11; i++) {
            Random random = new Random();
            index = random.nextInt(0, max + 1);
            max--;

            Thread thread = new Thread(new ControlWorkThread(people, tasks.get(index), i));
            threads.add(thread);
            tasks.remove(index);
        }
    }

    private List<Integer> fillTasksList() {
        List<Integer> tasks = new ArrayList<>();
        for (int i = 11; i < 21; i++) {
            tasks.add(i);
        }
        System.out.println("List of tasks\n" + tasks);
        return tasks;
    }

    public void startControlWork() {
        List<Thread> threads = new ArrayList<>();
        Map<Integer, Person> people = new HashMap<>();
        Map<Integer, Integer> timesOfWork = new HashMap<>();

        createAndFillArrayThread(people, threads);

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                System.out.println(stackTraceElement);
            }
        }

        fillMapTimesOfWork(timesOfWork, people);
        searchBestTime(timesOfWork);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Arrays.stream(e.getStackTrace()).forEach(System.out::println);
        }
        searchWorseTime(timesOfWork);
    }
}
