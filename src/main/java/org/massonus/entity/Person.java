package org.massonus.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class Person extends SuperSchool implements Comparable<Person>, Serializable {

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private Integer courseId;

    private Integer task;

    private Role role;

    public Person() {
    }

    public Person(Integer id, Role role) {
        setId(id);
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
        return Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName);
    }

    @Override
    public int compareTo(Person o) {
        return this.lastName.compareTo(o.lastName);
    }
}
