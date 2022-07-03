package com.example.SpringTest.services;

import com.example.SpringTest.domain.CityEntity;
import com.example.SpringTest.domain.RequestHistoryEntity;
import com.example.SpringTest.domain.UserEntity;
import com.example.SpringTest.exceptions.UserNotFoundException;
import com.example.SpringTest.repositories.CityRepository;
import com.example.SpringTest.repositories.RequestHistoryRepository;
import com.example.SpringTest.repositories.UserRepository;
import com.example.SpringTest.utils.TemperatureParser;
import com.example.SpringTest.utils.UrlGenerator;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Class TemperatureService:")
public class TemperatureServiceTests {
    @Mock
    private UserRepository userRepository;
    @Mock
    private CityRepository cityRepository;
    @Mock
    private RequestHistoryRepository requestHistoryRepository;
    @Mock
    private TemperatureParser temperatureParser;
    @Mock
    private UrlGenerator urlGenerator;

    private TemperatureService temperatureService;

    @BeforeEach
    void SetUp() {
        temperatureService = new TemperatureService(userRepository, requestHistoryRepository, temperatureParser, urlGenerator);
    }

    @Test
    @DisplayName("incorrect userId")
    void shouldThrowUserNotFoundException() {
        given(userRepository.findById(any()))
                .willReturn(Optional.empty());

        assertThrows(
                UserNotFoundException.class,
                () -> temperatureService.getTemperatureByUserId(1L));
    }

    @Test
    @DisplayName("should get temperature for user")
    void shouldGetTemperatureForUser() throws IOException {
        CityEntity city = new CityEntity(1, "Sochi", "sochi");
        given(userRepository.findById(1L))
                .willReturn(Optional.of(new UserEntity(
                        1L,
                        "Anna",
                        "password",
                        city)));

        given(urlGenerator.getHtmlDocument(city.getParserAlias()))
                .willReturn(new Document("https://realmeteo.ru/%s/1/current"));

        given(temperatureParser.getTemperature(any())).willReturn("34");

        given(requestHistoryRepository.save(any())).willReturn(new RequestHistoryEntity());

        assertDoesNotThrow(() -> temperatureService.getTemperatureByUserId(1L));
    }
}
