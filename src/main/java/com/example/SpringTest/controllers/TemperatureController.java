package com.example.SpringTest.controllers;

import com.example.SpringTest.exceptions.UserNotFoundException;
import com.example.SpringTest.services.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/temperature")
public class TemperatureController {

    @Autowired
    private TemperatureService temperatureService;

    @GetMapping("/{userId}")
    public ResponseEntity getByUserId(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(temperatureService.getTemperatureByUserId(userId));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
