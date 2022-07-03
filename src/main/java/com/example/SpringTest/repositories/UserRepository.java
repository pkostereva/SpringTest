package com.example.SpringTest.repositories;

import com.example.SpringTest.domain.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>{
    UserEntity findByName(String name);
}
