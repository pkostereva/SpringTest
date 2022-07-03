package com.example.SpringTest.repositories;

import com.example.SpringTest.domain.CityEntity;
import com.example.SpringTest.domain.RequestHistoryEntity;
import com.example.SpringTest.domain.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@DisplayName("Class UserRepository")
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class RequestHistoryRepositoryTests {
    @Autowired
    private RequestHistoryRepository requestHistoryRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private UserRepository userRepository;

    @DisplayName("should save requestHistory for user")
    @Test
    void shouldSaveRequestHistoryByUserId() {
        CityEntity city =
                new CityEntity(1, "Sochi", "sochi");

        cityRepository.save(city);

        UserEntity user = new UserEntity("Anna", "pass", city);

        userRepository.save(user);

        RequestHistoryEntity history = new RequestHistoryEntity(
                        1L,
                        "30",
                        new Timestamp(System.currentTimeMillis()),
                        user.getId());

        RequestHistoryEntity result = requestHistoryRepository.save(history);

        assertThat(result.getId()).isGreaterThan(0);
        assertThat(result.getUserId()).isEqualTo(user.getId());
    }

    @DisplayName("should return requestHistory by userId")
    @Test
    void shouldReturnRequestHistoryByUserId() {
        CityEntity city =
                new CityEntity(1, "Sochi", "sochi");

        cityRepository.save(city);

        List<UserEntity> users = Arrays.asList(
                new UserEntity("Anna", "pass", city),
                new UserEntity("Nick", "pass", city)
        );

        userRepository.saveAll(users);

        List<RequestHistoryEntity> history = Arrays.asList(
                new RequestHistoryEntity(
                        1L,
                        "30",
                        new Timestamp(System.currentTimeMillis()),
                        users.get(0).getId()),
                new RequestHistoryEntity(
                        2L,
                        "30",
                        new Timestamp(System.currentTimeMillis()),
                        users.get(1).getId())
        );

        requestHistoryRepository.saveAll(history);

        List<RequestHistoryEntity> historyOfUser1 = requestHistoryRepository.findByUserId(users.get(0).getId());

        assertThat(historyOfUser1).allMatch(h -> Objects.equals(h.getUserId(), users.get(0).getId()));
        assertThat(historyOfUser1).noneMatch(h -> Objects.equals(h.getUserId(), users.get(1).getId()));
    }

    @DisplayName("should return requestHistory for all users")
    @Test
    void shouldReturnRequestHistoryForAllUsers() {
        CityEntity city =
                new CityEntity(1, "Sochi", "sochi");
        cityRepository.save(city);

        List<UserEntity> users = Arrays.asList(
                new UserEntity("Anna", "pass", city),
                new UserEntity("Nick", "pass", city)
        );
        userRepository.saveAll(users);

        List<RequestHistoryEntity> history = Arrays.asList(
                new RequestHistoryEntity(
                        1L,
                        "30",
                        new Timestamp(System.currentTimeMillis()),
                        users.get(0).getId()),
                new RequestHistoryEntity(
                        2L,
                        "30",
                        new Timestamp(System.currentTimeMillis()),
                        users.get(1).getId())
        );

        requestHistoryRepository.saveAll(history);
        Iterable<RequestHistoryEntity> result = requestHistoryRepository.findAll();

        assertThat(result).usingRecursiveComparison().ignoringFields("created").isEqualTo(history);
        assertThat(result).hasSize(history.size());
    }
}
