package com.example.SpringTest.controllers;

import com.example.SpringTest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/{userId}")
    public ResponseEntity getByUserId(@PathVariable Long userId) {
        try {
            var history = userService.getHistoryByUserId(userId);
            if (history.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(history);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping()
    public ResponseEntity getAll() {
        try {
            var history = userService.getAllHistory();
            if (history.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(history);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
