package com.example.splitterator;

import java.util.Spliterator;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Consumer;

class TaggedArray<T> {
    private final Object[] elements; // immutable after construction

    TaggedArray(T[] data, Object[] tags) {
        int size = data.length;
        if (tags.length != size) throw new IllegalArgumentException();
        this.elements = new Object[2 * size];
        for (int i = 0, j = 0; i < size; ++i) {
            elements[j++] = data[i];
            elements[j++] = tags[i];
        }
    }

    public Spliterator<T> spliterator() {
        return new TaggedArraySpliterator<>(elements, 0, elements.length);
    }

    public static <T> void parEach(TaggedArray<T> a, Consumer<T> action) {
        Spliterator<T> s = a.spliterator();
        long targetBatchSize = s.estimateSize() / (ForkJoinPool.getCommonPoolParallelism() * 8);
        new ParEach(null, s, action, targetBatchSize).invoke();
    }
}
