package com.example.SpringTest.models.responseModels;

import com.example.SpringTest.domain.CityEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CityResponse {
    private Integer id;
    private String name;

    public static CityResponse toResponseModel(CityEntity entity) {
        CityResponse model = new CityResponse(
                entity.getId(),
                entity.getName());
        return  model;
    }
}
