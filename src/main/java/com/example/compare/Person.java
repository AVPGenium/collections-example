package com.example.compare;

public class Person
//        implements Comparable<Person>
{
    private String name;
    private int age;

    public Person(String name){
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName(){return name;}

    public int getAge() {
        return age;
    }

    //    @Override
//    public int compareTo(Person p) {
//        return name.compareTo(p.getName());
//    }


//    @Override
//    public String toString() {
//        return "Person{" +
//                "name='" + name + '\'' +
//                ", age=" + age +
//                '}';
//    }
}
