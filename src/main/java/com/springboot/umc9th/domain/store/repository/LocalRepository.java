package com.springboot.umc9th.domain.store.repository;

import com.springboot.umc9th.domain.store.entity.Local;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalRepository extends JpaRepository<Local, Long> {
}
