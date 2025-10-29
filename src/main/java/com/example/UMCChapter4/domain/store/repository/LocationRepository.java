package com.example.UMCChapter4.domain.store.repository;

import com.example.UMCChapter4.domain.store.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends JpaRepository<Location, Long> {
    @Query("select l.name from Location l where l.id = :locationId")
    String findLocationName(@Param("locationId") Long locationId);
}
