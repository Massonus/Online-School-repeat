package org.massonus.service;

import org.massonus.entity.Person;
import org.massonus.entity.Role;

import java.util.Map;

public class ControlWorkThread implements Runnable {

    private final Map<Integer, Person> people;
    private final Integer task;
    private final Integer id;

    public ControlWorkThread(Map<Integer, Person> people, Integer task, Integer id) {
        this.people = people;
        this.task = task;
        this.id = id;
    }

    @Override
    public void run() {
        Person person = new Person();
        person.setId(id.longValue());
        person.setTask(task);
        person.setRole(Role.STUDENT);
        System.out.println("I am a " + person.getRole() + " with id: " + person.getId() + ". I have task number = " + person.getTask());
        people.put(Integer.valueOf(person.getId().toString()), person);
    }
}
