package com.example.SpringTest.models.responseModels;

import com.example.SpringTest.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private CityResponse city;

    public static UserResponse toResponseModel(UserEntity entity) {
        UserResponse model = new UserResponse(
                entity.getId(),
                entity.getName(),
                CityResponse.toResponseModel(entity.getCity()));
        return  model;
    }

}
