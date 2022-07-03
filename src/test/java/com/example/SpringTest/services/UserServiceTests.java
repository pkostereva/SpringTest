package com.example.SpringTest.services;

import com.example.SpringTest.domain.CityEntity;
import com.example.SpringTest.domain.RequestHistoryEntity;
import com.example.SpringTest.domain.UserEntity;
import com.example.SpringTest.exceptions.UserAlreadyExistsException;
import com.example.SpringTest.models.requestModels.CreateUserRequest;
import com.example.SpringTest.models.responseModels.UserResponse;
import com.example.SpringTest.repositories.CityRepository;
import com.example.SpringTest.repositories.RequestHistoryRepository;
import com.example.SpringTest.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Class UserService:")
public class UserServiceTests {
    @Mock
    private UserRepository userRepository;
    @Mock
    private CityRepository cityRepository;
    @Mock
    private RequestHistoryRepository requestHistoryRepository;

    private UserService userService;

    @BeforeEach
    void SetUp() {
        userService = new UserService(userRepository, cityRepository, requestHistoryRepository);
    }

    @Test
    @DisplayName("incorrect registration of new user")
    void shouldThrowUserAlreadyExistsException() {
        given(userRepository.findByName(any()))
                .willReturn(new UserEntity("Bob", "password", new CityEntity(1, "Sochi", "sochi")));

        assertThrows(
                UserAlreadyExistsException.class,
                () -> userService.registration(new CreateUserRequest("Bob", "password", 1)));
    }

    @Test
    @DisplayName("correct registration of new user")
    void shouldCompleteUserRegistration() throws UserAlreadyExistsException {
        given(userRepository.findByName("Anny"))
                .willReturn(null);

        given(cityRepository.findById(1))
                .willReturn(Optional.of(new CityEntity(1, "Sochi", "sochi")));

        given(userRepository.save(any())).willReturn(new UserEntity(1L, "Anny", "password", new CityEntity(1, "Sochi", "sochi")));

        assertThat(userService.registration(new CreateUserRequest("Anny", "password", 1))).isEqualTo(1L);
    }

    @Test
    @DisplayName("incorrect get by id")
    void shouldThrowNoSuchElementException() {
        given(userRepository.findById(any()))
                .willReturn(Optional.empty());

        assertThrows(
                NoSuchElementException.class,
                () -> userService.getById(1L));
    }

    @Test
    @DisplayName("correct get by id")
    void shouldGetUserEntityById() {
        UserEntity user = new UserEntity(
                1L,
                "Anny",
                "password",
                new CityEntity(1, "Sochi", "sochi"));

        given(userRepository.findById(any()))
                .willReturn(Optional.of(user));

        assertThat(userService.getById(1L)).usingRecursiveComparison().isEqualTo(UserResponse.toResponseModel(user));
    }

    @Test
    @DisplayName("correct get all users")
    void shouldReturnAllUserEntities() {
        List<UserEntity> users = List.of(
                new UserEntity(
                        1L,
                        "Anny",
                        "password",
                        new CityEntity(1, "Sochi", "sochi"))
        );

        List<UserResponse> userResponses = new ArrayList<>();
        users.forEach(u -> userResponses.add(UserResponse.toResponseModel(u)));

        given(userRepository.findAll())
                .willReturn(users);

        List<UserResponse> result = userService.getAll();

        assertThat(result).hasSize(userResponses.size()).usingRecursiveComparison().isEqualTo(userResponses);
    }

    @Test
    @DisplayName("correct get history by userId")
    void shouldGetUserHistoryByUserId() {
        List<RequestHistoryEntity> history = Arrays.asList(
                new RequestHistoryEntity(1L, "30", new Timestamp(System.currentTimeMillis()), 1L),
                new RequestHistoryEntity(2L, "25", new Timestamp(System.currentTimeMillis()), 1L)
        );

        given(requestHistoryRepository.findByUserId(1L))
                .willReturn(history);

        assertThat(userService.getHistoryByUserId(1L)).usingRecursiveComparison().isEqualTo(history);
        assertThat(userService.getHistoryByUserId(1L)).hasSize(history.size());
    }

    @Test
    @DisplayName("correct get all history")
    void shouldGetUserHistory() {
        List<RequestHistoryEntity> history = Arrays.asList(
                new RequestHistoryEntity(1L, "30", new Timestamp(System.currentTimeMillis()), 1L),
                new RequestHistoryEntity(2L, "25", new Timestamp(System.currentTimeMillis()), 2L)
        );

        given(requestHistoryRepository.findAll())
                .willReturn(history);

        assertThat(userService.getAllHistory()).usingRecursiveComparison().isEqualTo(history);
    }
}
