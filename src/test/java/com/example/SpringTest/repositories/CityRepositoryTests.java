package com.example.SpringTest.repositories;

import com.example.SpringTest.domain.CityEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@DisplayName("Class CityRepository")
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class CityRepositoryTests {

    @Autowired
    private CityRepository cityRepository;

    @DisplayName("should save all CityEntities")
    @Test
    void shouldSaveAllCityEntities() {
        List<CityEntity> cities = Arrays.asList(
                new CityEntity(1, "Sochi", "sochi"),
                new CityEntity(2, "Moscow", "moscow")
        );

        Iterable<CityEntity> result = cityRepository.saveAll(cities);

        assertThat(result).usingRecursiveComparison().isEqualTo(cities);
        assertThat(result).hasSize(cities.size());
    }

    @DisplayName("should return all cities")
    @Test
    void shouldReturnAllCityEntities() {
        List<CityEntity> cities = Arrays.asList(
            new CityEntity(1, "Sochi", "sochi"),
            new CityEntity(2, "Moscow", "moscow")
        );

        cityRepository.saveAll(cities);

        Iterable<CityEntity> result = cityRepository.findAll();
        assertThat(result).usingRecursiveComparison().isEqualTo(cities);
        assertThat(result).hasSize(cities.size());
    }
}
