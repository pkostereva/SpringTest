package com.example.SpringTest.services;

import com.example.SpringTest.domain.CityEntity;
import com.example.SpringTest.models.responseModels.CityResponse;
import com.example.SpringTest.repositories.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Class CityService:")
public class CityServiceTests {
    @Mock
    private CityRepository cityRepository;
    private CityService cityService;

    @BeforeEach
    void SetUp() {
        cityService = new CityService(cityRepository);
    }

    @Test
    @DisplayName("correct get all cities")
    void shouldReturnAllCityEntities() {
        List<CityEntity> cities = List.of(
                new CityEntity(
                        1,
                        "Sochi",
                        "sochi")
        );

        ArrayList<Object> cityResponses = new ArrayList<>();
        cities.forEach(c -> cityResponses.add(CityResponse.toResponseModel(c)));

        given(cityRepository.findAll())
                .willReturn(cities);

        List<CityResponse> result = cityService.getAll();

        assertThat(result).hasSize(cityResponses.size()).usingRecursiveComparison().isEqualTo(cities);
    }
}
