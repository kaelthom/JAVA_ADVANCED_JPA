package com.java_advanced_ajc;

import java.util.List;

public interface GenericDAO<T, K> {

    List<T> findAll();

    T findOne(K id);

    void update(T entity);

    void delete(T entity);

    void deleteByKey(K id);

    void create(T entity);

}
