package com.example.compare;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {
    public int compare(Person a, Person b){
        return a.getName().compareTo(b.getName());
    }
}

