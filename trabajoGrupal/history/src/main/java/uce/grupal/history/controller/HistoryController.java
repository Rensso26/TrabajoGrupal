package uce.grupal.history.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uce.grupal.history.History;
import uce.grupal.history.repository.HistoryRepository;

@RestController
@RequestMapping("/history")
public class HistoryController {
    private final HistoryRepository historyRepository;

    @Autowired
    public HistoryController(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @PostMapping("/log")
    public ResponseEntity<History> logEntry(@RequestBody History history) {
        history.setTimestamp(LocalDateTime.now());
        History savedHistory = historyRepository.save(history);
        return ResponseEntity.ok(savedHistory);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<History>> getUserHistory(@PathVariable Long userId) {
        List<History> history = historyRepository.findByUserId(userId);
        return ResponseEntity.ok(history);
    }
}
