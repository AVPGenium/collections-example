package com.example.collection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class TextFile implements Collection<String> {
    private final String filename;
    private final List<String> lines;

    public TextFile(String filename) {
        this.filename = filename;
        this.lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            int i = 0;
            String line;
            line = reader.readLine();
            while (line != null && !line.equals("")) {
                i++;
                lines.add(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        return lines.size();
    }

    @Override
    public boolean isEmpty() {
        return lines.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return lines.contains(o);
    }

    @Override
    public Iterator<String> iterator() {
        return lines.iterator();
    }

    @Override
    public Object[] toArray() {
        return lines.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return lines.toArray(a);
    }

    @Override
    public boolean add(String e) {
        return lines.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return lines.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Spliterator<String> spliterator() {
        return Collection.super.spliterator();
    }
}
