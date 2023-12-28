package org.massonus.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class Person implements Comparable<Person>, Serializable {

    private Integer id;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private Integer courseId;

    private Role role;

    private transient Integer task;

    public Person() {
    }

    public Person(Integer id, Integer task, Role role) {
        this.id = id;
        this.task = task;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", courseId=" + courseId +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    @Override
    public int compareTo(Person o) {
        return this.lastName.compareTo(o.lastName);
    }
}
