package com.example.adoptame.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepository<T,ID> extends CrudRepository<T,ID> {
}
