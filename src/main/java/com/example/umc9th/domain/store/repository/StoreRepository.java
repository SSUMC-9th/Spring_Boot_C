package com.example.umc9th.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.umc9th.domain.store.entity.Store;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findByName(String name);

    Optional<Store> findById(Long storeId);
}
