package ru.practicum.ewm.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.RequestOutputDto;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ViewStatsMapper implements RowMapper<RequestOutputDto> {

    @Override
    public RequestOutputDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return RequestOutputDto.builder()
                .app(rs.getString("app"))
                .uri(rs.getString("uri"))
                .hits(rs.getInt("hits"))
                .build();
    }
}


