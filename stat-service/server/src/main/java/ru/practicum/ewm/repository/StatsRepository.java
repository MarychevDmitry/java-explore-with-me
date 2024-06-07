package ru.practicum.ewm.repository;

import ru.practicum.ewm.RequestDto;
import ru.practicum.ewm.RequestOutputDto;
import ru.practicum.ewm.ViewsStatsRequest;

import java.util.List;

public interface StatsRepository {
    void saveHit(RequestDto hit);

    List<RequestOutputDto> getStats(ViewsStatsRequest request);

    List<RequestOutputDto> getUniqueStats(ViewsStatsRequest request);
}