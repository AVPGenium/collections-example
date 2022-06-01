package com.example.splitterator;

import java.util.ArrayList;
import java.util.Spliterator;

public class ArrayListSplitterator {
    public static void main(String[] args)
    {

        // create an ArrayList which contains
        // user details from user class
        ArrayList<User> list = new ArrayList<>();
        list.add(new User("Aman", 24));
        list.add(new User("Suraj", 23));
        list.add(new User("Amar", 24));
        list.add(new User("Kajal", 22));

        // create Spliterator of ArrayList
        // using spliterator() method
        Spliterator<User> users = list.spliterator();

        // print result from Spliterator
        System.out.println("list of Emails:");

        // forEachRemaining method of Spliterator
        users.forEachRemaining(ArrayListSplitterator::print);
    }

    public static void print(User u) {
        System.out.println("User name : " + u.getName()
                + " and user age: " + u.getAge());
    }
}
