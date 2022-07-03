package com.example.SpringTest.repositories;

import com.example.SpringTest.domain.RequestHistoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestHistoryRepository extends CrudRepository<RequestHistoryEntity, Long> {
    @Query("SELECT h FROM RequestHistoryEntity h WHERE h.userId = :userId")
    List<RequestHistoryEntity> findByUserId(@Param("userId") Long userId);
}
