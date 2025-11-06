package com.example.UMCChapter4.domain.store.repository;

import com.example.UMCChapter4.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
