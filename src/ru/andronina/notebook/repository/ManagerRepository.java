package ru.andronina.notebook.repository;

import ru.andronina.notebook.model.Manager;

import java.util.*;

public class ManagerRepository implements AbstractRepository<Manager, String> {
    private final Map<String, Manager> managers = new TreeMap<>();

    @Override
    public void add(Manager value) {
        String key = UUID.randomUUID().toString();
        value.setId(key);
        managers.put(key, value);
    }

    @Override
    public void delete(Manager value) {
        managers.remove(value.getId());
    }

    @Override
    public Collection<Manager> getAll() {
        return Collections.unmodifiableCollection(managers.values());
    }

    @Override
    public Manager findByID(String key) {
        return managers.getOrDefault(key, Manager.EMPTY);
    }

}
