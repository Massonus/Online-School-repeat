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

    private Role role;

    private Integer courseId;

    private transient Integer task;

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
