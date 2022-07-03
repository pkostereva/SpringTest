package com.example.SpringTest.services;

import com.example.SpringTest.domain.RequestHistoryEntity;
import com.example.SpringTest.domain.UserEntity;
import com.example.SpringTest.exceptions.TemperatureNotFoundException;
import com.example.SpringTest.exceptions.UserNotFoundException;
import com.example.SpringTest.repositories.RequestHistoryRepository;
import com.example.SpringTest.repositories.UserRepository;
import com.example.SpringTest.utils.TemperatureParser;
import com.example.SpringTest.utils.UrlGenerator;
import lombok.AllArgsConstructor;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TemperatureService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RequestHistoryRepository requestHistoryRepository;
    @Autowired
    private TemperatureParser temperatureParser;
    @Autowired
    private UrlGenerator urlGenerator;

    public String getTemperatureByUserId(Long userId) throws UserNotFoundException, IOException, TemperatureNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()){
            throw new UserNotFoundException("Пользователь не найден");
        }
        UserEntity user = optionalUser.get();

        Document html = urlGenerator.getHtmlDocument(user.getCity().getParserAlias());
        String temperature = temperatureParser.getTemperature(html);

        if (temperature.isEmpty()){
            throw new TemperatureNotFoundException("Проблема с получением значения температуры, попробуйте позже");
        }

        saveRequestHistory(user.getId(), temperature);
        return temperature;
    }

    private void saveRequestHistory(Long userId, String temperature) {
        RequestHistoryEntity entity = new RequestHistoryEntity(temperature, userId);
        requestHistoryRepository.save(entity);
    }
}
