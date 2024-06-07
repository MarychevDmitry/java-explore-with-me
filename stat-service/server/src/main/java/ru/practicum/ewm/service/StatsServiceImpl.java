package ru.practicum.ewm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.RequestDto;
import ru.practicum.ewm.RequestOutputDto;
import ru.practicum.ewm.ViewsStatsRequest;
import ru.practicum.ewm.repository.StatsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final StatsRepository statRepository;

    @Override
    public void saveHit(RequestDto hit) {
        statRepository.saveHit(hit);
    }

    @Override
    public List<RequestOutputDto> getViewStatsList(ViewsStatsRequest request) {
        if (request.isUnique()) {
            return statRepository.getUniqueStats(request);
        }

        return statRepository.getStats(request);
    }
}