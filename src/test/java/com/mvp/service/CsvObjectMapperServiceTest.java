package com.mvp.service;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.mvp.model.player.BasketballPlayer;
import com.mvp.model.player.HandballPlayer;
import com.mvp.service.impl.CsvObjectMapperServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

class CsvObjectMapperServiceTest {
    private final CsvMapper csvMapper = CsvMapper.builder()
            .disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
            .build();
    private final CsvObjectMapperServiceImpl csvObjectMapperService = new CsvObjectMapperServiceImpl(csvMapper);

    @Test
    void decodeBasketballPlayer() {
        String data = """
                player 1;nick1;4;Team A;10;2;7
                player 2;nick2;16;Team B;20;0;0
                """;
        BasketballPlayer playerA = new BasketballPlayer("player 1", "nick1", 4, "Team A", 10, 2, 7);
        BasketballPlayer playerB = new BasketballPlayer("player 2", "nick2", 16, "Team B", 20, 0, 0);
        List<BasketballPlayer> decoded = csvObjectMapperService.decode(data, BasketballPlayer.class);
        assertThat(decoded, containsInAnyOrder(playerA, playerB));
    }

    @Test
    void decodeHandballPlayer() {
        String data = """
                player 1;nick1;4;Team A;0;20
                player 2;nick2;8;Team B;15;20
                """;
        HandballPlayer playerA = new HandballPlayer("player 1", "nick1", 4, "Team A", 0, 20);
        HandballPlayer playerB = new HandballPlayer("player 2", "nick2", 8, "Team B", 15, 20);
        List<HandballPlayer> decoded = csvObjectMapperService.decode(data, HandballPlayer.class);
        assertThat(decoded, containsInAnyOrder(playerA, playerB));
    }
}