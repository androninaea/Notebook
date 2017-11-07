package ru.andronina.notebook.repository;

import java.util.Collection;

public interface AbstractRepository<T, K> {
    public void add(T value);

    public void delete(T value);

    public Collection<T> getAll();

    T findByID(K key);

}

