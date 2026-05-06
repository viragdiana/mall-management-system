package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Mall;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MallRepository extends JpaRepository<Mall, Long> {

    List<Mall> findByNameContainingIgnoreCase(
            String name,
            Sort sort
    );
}