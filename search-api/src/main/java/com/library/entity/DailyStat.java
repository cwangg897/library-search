package com.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "daily_stat")
@Getter
@ToString
public class DailyStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "query")
    private String query;

    @Column(name = "eventDateTime")
    private LocalDateTime eventDateTime;

    public DailyStat(String query, LocalDateTime localDateTime) {
        this.query = query;
        this.eventDateTime = localDateTime;
    }
}
