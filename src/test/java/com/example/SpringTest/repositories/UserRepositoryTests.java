package com.example.SpringTest.repositories;

import com.example.SpringTest.domain.CityEntity;
import com.example.SpringTest.domain.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureTestDatabase
@DisplayName("Class UserRepository")
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserRepositoryTests {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private UserRepository userRepository;

    public UserRepositoryTests() {
    }

    @DisplayName("should save all UserEntities")
    @Test
    void shouldSaveAllUserEntities() {
        List<CityEntity> cities = Arrays.asList(
                new CityEntity(1, "Sochi", "sochi"),
                new CityEntity(2, "Moscow", "moscow")
        );
        cityRepository.saveAll(cities);

        List<UserEntity> users = Arrays.asList(
                new UserEntity("Anna", "pass", cities.get(0)),
                new UserEntity("Nick", "pass", cities.get(1))
        );

        Iterable<UserEntity> result = userRepository.saveAll(users);

        assertThat(result).usingRecursiveComparison().isEqualTo(users);
        assertThat(result).hasSize(users.size());
    }

    @DisplayName("should return user by id")
    @Test
    void shouldReturnUserEntityById() {
        CityEntity city =
                new CityEntity(1, "Sochi", "sochi");

        cityRepository.save(city);

        List<UserEntity> users = Arrays.asList(
                new UserEntity("Paul", "pass", city),
                new UserEntity("Stan", "pass", city)
        );
        Iterable<UserEntity> userEntities = userRepository.saveAll(users);
        List<UserEntity> result = new ArrayList<UserEntity>();

        userEntities.forEach(result::add);
        Optional<UserEntity> user = userRepository.findById(1L);
        Long id = user.get().getId();

        assertThat(id.equals(result.get(0)));
    }

    @DisplayName("shouldn't return user by id")
    @Test
    void shouldThrowNoSuchElementException() {
        Optional<UserEntity> result = userRepository.findById(4L);

        assertThrows(
                NoSuchElementException.class,
                () -> userRepository.findById(4L).get());
    }
}
