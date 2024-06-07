package ru.practicum.ewm;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public interface Client {
    public ResponseEntity<Object> saveHit(HttpServletRequest request);

    public ResponseEntity<Object> getHit(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);
}
