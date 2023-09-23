package com.example.case2.repository;

import com.example.case2.model.entity.Mars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarsRepository extends JpaRepository<Mars, Long> {
}
