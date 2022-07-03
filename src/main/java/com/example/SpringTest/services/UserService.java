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
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private RequestHistoryRepository requestHistoryRepository;

    public Long registration(CreateUserRequest user) throws UserAlreadyExistsException {
        if (userRepository.findByName(user.getName()) != null) {
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует!");
        }

        CityEntity city = cityRepository.findById(user.getCityId()).get();
        UserEntity entity = new UserEntity(user.getName(), user.getPassword(), city);

        return userRepository.save(entity).getId();
    }

    public UserResponse getById(Long id) {
         UserEntity user = userRepository.findById(id).get();
         return UserResponse.toResponseModel(user);
    }

    public List<UserResponse> getAll() {
        ArrayList<UserResponse> result = new ArrayList<>();
        userRepository.findAll().forEach(u -> result.add(UserResponse.toResponseModel(u)));
        return result;
    }

    public List<RequestHistoryEntity> getHistoryByUserId(Long userId) {
        return requestHistoryRepository.findByUserId(userId);
    }

    public List<RequestHistoryEntity> getAllHistory() {
        List<RequestHistoryEntity> result = new ArrayList<>();
        requestHistoryRepository.findAll().forEach(result::add);
        return result;
    }
}
