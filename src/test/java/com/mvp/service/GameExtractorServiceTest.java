package com.mvp.service;

import com.mvp.generator.PlayerGenerator;
import com.mvp.model.game.Game;
import com.mvp.model.player.BasketballPlayer;
import com.mvp.model.player.HandballPlayer;
import com.mvp.service.impl.GameExtractorServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GameExtractorServiceTest {
    private final CsvObjectMapperService objectMapperService = Mockito.mock(CsvObjectMapperService.class);
    private final GameExtractorService service = new GameExtractorServiceImpl(objectMapperService);

    @Test
    void extractHandballGame() {
        HandballPlayer player = PlayerGenerator.getHandballPlayer();
        when(objectMapperService.decode("data", HandballPlayer.class)).thenReturn(List.of(player));
        String data = "HANDBALL" + System.lineSeparator() + "data";
        Game game = service.extractGame(data);
        verify(objectMapperService).decode("data", HandballPlayer.class);
        assertThat(game, is(new Game(List.of(player))));
    }

    @Test
    void extractBasketballGame() {
        BasketballPlayer player = PlayerGenerator.getBasketballPlayer();
        when(objectMapperService.decode("data", BasketballPlayer.class)).thenReturn(List.of(player));
        String data = "BASKETBALL" + System.lineSeparator() + "data";
        Game game = service.extractGame(data);
        verify(objectMapperService).decode("data", BasketballPlayer.class);
        assertThat(game, is(new Game(List.of(player))));
    }
}