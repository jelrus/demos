package epam.com.esm.model.service;

import epam.com.esm.persistence.entity.BaseEntity;

import java.util.List;

public interface BaseService<E extends BaseEntity> {

    E create(E e);

    E update(E e);

    E findById(Long id);

    E delete(Long id);

    List<E> findAll();
}