package com.springboot.umc9th.domain.store.repository;

import com.springboot.umc9th.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
