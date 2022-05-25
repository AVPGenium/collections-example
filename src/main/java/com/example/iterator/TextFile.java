package com.example.iterator;

import java.util.Iterator;

/**
 * Этот класс предоставляет построчную итерацию по текстовому файлу.
 *   Метод итератора remove() генерирует UnsupportedOperatorException.
 *   Итератор перехватывает и генерирует IOExceptions как IllegalArgumentExceptions.
 */
public class TextFile implements Iterable<String> {
    final String filename;

    public TextFile(String filename) {
        this.filename = filename;
    }

    public Iterator<String> iterator() {
        return new TextFileIterator(filename);
    }

    public static void main(String[] args) {
        String filename = "TextFile.java";
        if (args.length > 0)
            filename = args[0];

        for (String line : new TextFile(filename))
            System.out.println(line);
    }
}
