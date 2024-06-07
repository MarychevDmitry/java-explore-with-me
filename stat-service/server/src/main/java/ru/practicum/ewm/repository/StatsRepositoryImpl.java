package ru.practicum.ewm.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.RequestDto;
import ru.practicum.ewm.RequestOutputDto;
import ru.practicum.ewm.ViewsStatsRequest;

import java.sql.Timestamp;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StatsRepositoryImpl implements StatsRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final ViewStatsMapper viewStatsMapper;

    @Override
    public void saveHit(RequestDto hit) {
        String query = "INSERT INTO stats (app, uri, ip, created) " +
                "VALUES (:app, :url, :ip, :created)";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("app", hit.getApp())
                .addValue("url", hit.getUri())
                .addValue("ip",hit.getIp())
                .addValue("created", Timestamp.valueOf(hit.getTimestamp()));

        namedParameterJdbcTemplate.update(query, namedParameters);
    }

    @Override
    public List<RequestOutputDto> getStats(ViewsStatsRequest request) {
        String query;
        SqlParameterSource namedParameters;

        if (request.getUris().isEmpty()) {
            query = "SELECT app, uri, COUNT (ip) AS hits FROM stats " +
                    "WHERE (created >= :start AND created <= :end) " +
                    "GROUP BY app, uri ORDER BY hits DESC";

            namedParameters = new MapSqlParameterSource()
                    .addValue("start", request.getStart())
                    .addValue("end", request.getEnd());

            return namedParameterJdbcTemplate.query(query, namedParameters, viewStatsMapper);
        }

        query = "SELECT app, uri, COUNT (ip) AS hits FROM stats " +
                "WHERE (created >= :start AND created <= :end) AND uri IN (:uris) " +
                "GROUP BY app, uri ORDER BY hits DESC";

        namedParameters = new MapSqlParameterSource()
                .addValue("start", request.getStart())
                .addValue("end", request.getEnd())
                .addValue("uris", request.getUris());

        return namedParameterJdbcTemplate.query(query, namedParameters, viewStatsMapper);
    }

    @Override
    public List<RequestOutputDto> getUniqueStats(ViewsStatsRequest request) {
        String query = "SELECT app, uri, COUNT (DISTINCT ip) AS hits FROM stats " +
                "WHERE (created >= :start AND created <= :end) AND uri IN (:uris) " +
                "GROUP BY app, uri ORDER BY hits DESC";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("start", request.getStart())
                .addValue("end", request.getEnd())
                .addValue("uris", request.getUris());

        return namedParameterJdbcTemplate.query(query, namedParameters, viewStatsMapper);
    }
}