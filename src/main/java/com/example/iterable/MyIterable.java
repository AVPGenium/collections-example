package com.example.iterable;

import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

public class MyIterable<T> implements Iterable<T> {
    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }

    /**
     * The default implementation should usually be overridden.
     * The spliterator returned by the default implementation has poor splitting capabilities,
     * is unsized, and does not report any spliterator characteristics.
     * Implementing classes can nearly always provide a better implementation.
     */
    @Override
    public Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), 0);
    }
}
