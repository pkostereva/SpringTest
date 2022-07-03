package com.example.SpringTest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = com.example.SpringTest.domain.RequestHistoryEntity.TABLE_NAME)
public class RequestHistoryEntity {
    public static final String TABLE_NAME = "requests_history";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String temperature;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp()
    private Timestamp created;

    private Long userId;

    public RequestHistoryEntity(String temperature, Long userId) {
        this.temperature = temperature;
        this.userId = userId;
    }
}
