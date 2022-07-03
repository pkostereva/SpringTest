package com.example.SpringTest.services;

import com.example.SpringTest.models.responseModels.CityResponse;
import com.example.SpringTest.repositories.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CityService {
    @Autowired
    private CityRepository cityRepository;


    public List<CityResponse> getAll() {
        ArrayList<CityResponse> result = new ArrayList<>();
        cityRepository.findAll().forEach(c -> result.add(CityResponse.toResponseModel(c)));
        return result;
    }
}
