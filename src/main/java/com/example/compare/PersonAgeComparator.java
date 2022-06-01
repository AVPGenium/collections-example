package com.example.compare;

import java.util.Comparator;

public class PersonAgeComparator implements Comparator<Person> {
    public int compare(Person a, Person b){
        //return Integer.compare(a.getAge(), b.getAge());
        if(a.getAge() > b.getAge())
            return 1;
        else if(a.getAge() < b.getAge())
            return -1;
        else
            return 0;
    }
}

