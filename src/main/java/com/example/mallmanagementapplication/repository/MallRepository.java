package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Mall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * save(entity)
 * findById(id)
 * findAll()
 * deleteById(id)
 * delete(entity)
 * count()
 * existsById(id)
 */

@Repository
public interface MallRepository extends JpaRepository<Mall, Long> {
}
