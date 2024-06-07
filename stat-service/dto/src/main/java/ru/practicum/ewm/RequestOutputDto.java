package ru.practicum.ewm;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class RequestOutputDto {
    private String app;
    private String uri;
    private int hits;
}