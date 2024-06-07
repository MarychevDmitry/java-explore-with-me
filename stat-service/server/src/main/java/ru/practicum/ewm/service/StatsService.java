package ru.practicum.ewm.service;

import ru.practicum.ewm.RequestDto;
import ru.practicum.ewm.RequestOutputDto;
import ru.practicum.ewm.ViewsStatsRequest;

import java.util.List;

public interface StatsService {
    void saveHit(RequestDto hit);

    List<RequestOutputDto> getViewStatsList(ViewsStatsRequest request);
}