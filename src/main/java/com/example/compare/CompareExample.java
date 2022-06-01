package com.example.compare;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class CompareExample {
    public static void main(String[] args) {
        PersonComparator personComparator = new PersonComparator();
        Comparator<Person> pcomp = new PersonNameComparator()
                .thenComparing(new PersonAgeComparator());
//        SortedSet<Person> persons = new TreeSet<>(personComparator);
        SortedSet<Person> persons = new TreeSet<>(personComparator);
        persons.add(new Person("Vasya"));
        persons.add(new Person("Petya"));
        persons.add(new Person("Dima"));
        System.out.println(persons);

        persons = new TreeSet<>(pcomp);
        persons.add(new Person("Vasya", 10));
        persons.add(new Person("Petya", 12));
        persons.add(new Person("Vasya", 12));
        System.out.println(persons);

    }
}
