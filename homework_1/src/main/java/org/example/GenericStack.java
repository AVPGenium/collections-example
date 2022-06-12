package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenericStack<E> implements Stack<E> {
    int maxSize;
    int size;
    List<E> elements;

    public GenericStack() {
        elements = new ArrayList<E>();
        size = 0;
    }

    @Override
    public void push(E element) throws StackException {
        if (isFull())
            throw new StackException("Stack is full");
        else{
            elements.add(element);
            size++;
        }
    }

    @Override
    public E pop() throws StackException {
        E element;
        if (isEmpty())
            throw new StackException("Stack is empty");
        else{
            element = elements.get(size-1);
            elements.remove(size-1);
            size--;
        }
        return element;
    }

    @Override
    public E peek() {
        if (size == 0){
            return null;
        }
        else {
            return elements.get(size - 1);
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == maxSize;
    }

    @Override
    public void setMaxSize(int size) {
        this.maxSize = size;
    }

    @Override
    public void pushAll(Collection<? extends E> src) throws StackException {
        for (E e : src) {
            push(e);
        }
    }

    @Override
    public void popAll(Collection<? super E> dst) throws StackException {
        while (!isEmpty()){
            dst.add(pop());
        }
    }
}
