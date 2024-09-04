package com.library.repository;

import com.library.controller.response.StatResponse;
import com.library.entity.DailyStat;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DailyStatRepository extends JpaRepository<DailyStat, Long> {

    long countByQueryAndEventDateTimeBetween(String query, LocalDateTime start, LocalDateTime end);

    @Query("SELECT new com.library.controller.response.StatResponse(ds.query, count(ds.query)) "
        + "FROM DailyStat ds "
        + "GROUP BY ds.query ORDER BY count(ds.query) DESC")
    List<StatResponse> findTopQuery(Pageable pageable);
}
