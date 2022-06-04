package com.example.collection;

import java.util.Arrays;

public class Example {
    public static void main(String[] args) {
        TextFile textFile = new TextFile("text.txt");
        int size = textFile.size();
        System.out.println(size);
        textFile.add("234");

        size = textFile.size();
        System.out.println(size);

        textFile.addAll(Arrays.asList("345", "567"));

        size = textFile.size();
        System.out.println(size);

        textFile.clear();

        size = textFile.size();
        System.out.println(size);
    }
}
