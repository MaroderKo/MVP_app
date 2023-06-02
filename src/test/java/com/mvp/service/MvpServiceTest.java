package com.mvp.service;

import com.mvp.model.game.Game;
import com.mvp.model.player.HandballPlayer;
import com.mvp.service.impl.GameCountScoreServiceImpl;
import com.mvp.service.impl.MvpServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

class MvpServiceTest {
    private final GameCountService gameCountService = new GameCountScoreServiceImpl();
    private final GameExtractorService gameExtractorService = Mockito.mock(GameExtractorService.class);
    private final ResourcePatternResolver resourcePatternResolver = Mockito.mock(ResourcePatternResolver.class);
    private final MvpService service = new MvpServiceImpl(gameCountService, gameExtractorService, resourcePatternResolver);

    @Test
    void getMostValuablePlayer() throws Exception {
        String data = """
                HANDBALL
                player 1;nick1;4;Team A;0;20
                player 2;nick2;8;Team A;15;20
                player 3;nick3;15;Team A;10;20
                player 4;nick4;16;Team B;1;25
                player 5;nick5;23;Team B;12;25
                player 6;nick6;42;Team B;8;25
                """
                .replace("\n", System.lineSeparator());
        List<HandballPlayer> decoded = List.of(
                new HandballPlayer("player1", "nick1", 4, "Team A", 0, 20),
                new HandballPlayer("player2", "nick2", 8, "Team A", 15, 20),
                new HandballPlayer("player3", "nick3", 15, "Team A", 10, 20),
                new HandballPlayer("player4", "nick4", 16, "Team B", 1, 25),
                new HandballPlayer("player5", "nick5", 23, "Team B", 12, 25),
                new HandballPlayer("player6", "nick6", 42, "Team B", 8, 25)
        );
        when(resourcePatternResolver.getResources("file:resources/*")).thenReturn(new Resource[]{new ByteArrayResource(data.getBytes())});
        when(gameExtractorService.extractGame(data)).thenReturn(new Game(decoded));
        assertThat(service.getMostValuablePlayer(), is("nick2"));

    }
}