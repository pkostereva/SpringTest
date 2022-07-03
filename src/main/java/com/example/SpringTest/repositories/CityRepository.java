package com.example.SpringTest.repositories;

import com.example.SpringTest.domain.CityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<CityEntity, Integer> {
}

