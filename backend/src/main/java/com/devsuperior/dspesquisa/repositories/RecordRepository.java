package com.devsuperior.dspesquisa.repositories;

import com.devsuperior.dspesquisa.entities.Record;
import java.time.Instant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecordRepository extends JpaRepository<Record, Long> {

    @Query("SELECT r FROM Record r WHERE (COALESCE(:min, NULL) IS NULL OR r.moment >= :min)"
        + " AND (COALESCE(:max, NULL) IS NULL OR r.moment <= :max)")
    Page<Record> findByMoments(Instant min, Instant max, Pageable pageable);
}
