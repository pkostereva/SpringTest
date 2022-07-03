package com.example.SpringTest.models.responseModels;

import com.example.SpringTest.domain.RequestHistoryEntity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class RequestHistoryResponse {
    private Long id;
    private Long userId;
    private String temperature;
    private Timestamp created;

    public static RequestHistoryResponse toResponseModel(RequestHistoryEntity entity) {
        RequestHistoryResponse model = new RequestHistoryResponse();
        model.setId(entity.getId());
        model.setUserId(entity.getUserId());
        model.setTemperature(entity.getTemperature());
        model.setCreated(entity.getCreated());

        return  model;
    }
}
