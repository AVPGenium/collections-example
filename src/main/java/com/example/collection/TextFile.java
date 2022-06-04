package com.example.collection;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class TextFile implements Collection<String> {
    private final String filename;
    private final List<String> lines;

    public TextFile(String filename) {
        this.filename = filename;
        this.lines = new ArrayList<>();
        try (InputStream is = TextFile.class.getClassLoader().getResourceAsStream(filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))){
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
        return lines.size() == 0;
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
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(String s) {
        try(FileWriter writer = new FileWriter(TextFile.class.getClassLoader().getResource(filename).getPath(), true);
            BufferedWriter bufferWriter = new BufferedWriter(writer)){
            bufferWriter.write(s);
        } catch (IOException e) {
            System.out.println(e);
        }
        return lines.add(s);
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        try(FileWriter writer = new FileWriter(TextFile.class.getClassLoader().getResource(filename).getPath(), true);
            BufferedWriter bufferWriter = new BufferedWriter(writer)){
            c.forEach(element -> {
                try {
                    bufferWriter.write(element);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            System.err.println(e);
        }
        return lines.addAll(c);
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
        try(FileWriter writer = new FileWriter(TextFile.class.getClassLoader().getResource(filename).getPath());
            BufferedWriter bufferWriter = new BufferedWriter(writer)){
            bufferWriter.write("");
        } catch (IOException e) {
            System.out.println(e);
        }
        lines.clear();
    }
}
