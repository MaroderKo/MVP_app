package com.mvp.service;

import com.mvp.model.PlayerResult;
import com.mvp.model.game.Game;
import com.mvp.model.player.BasketballPlayer;
import com.mvp.model.player.HandballPlayer;
import com.mvp.service.impl.GameCountScoreServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

class GameCountScoreServiceTest {
    private final GameCountService service = new GameCountScoreServiceImpl();

    @Test
    void countBasketballPlayersScore() {
        BasketballPlayer basketballPlayerA = new BasketballPlayer("player1", "nick1", 1, "Team A", 1, 1, 1);
        BasketballPlayer basketballPlayerB = new BasketballPlayer("player2", "nick2", 1, "Team B", 0, 0, 0);

        Game game = new Game(List.of(basketballPlayerA, basketballPlayerB));

        PlayerResult basketballPlayerAResult = new PlayerResult(basketballPlayerA.getNickname(), basketballPlayerA.getIndividualScore(basketballPlayerA.getTeamName()));
        PlayerResult basketballPlayerBResult = new PlayerResult(basketballPlayerB.getNickname(), basketballPlayerB.getIndividualScore(""));

        List<PlayerResult> playerResults = service.countPlayersScore(game);

        assertThat(playerResults, containsInAnyOrder(basketballPlayerAResult, basketballPlayerBResult));
    }

    @Test
    void countHandballPlayersScore() {
        HandballPlayer basketballPlayerA = new HandballPlayer("player1", "nick1", 1, "Team A", 1, 0);
        HandballPlayer basketballPlayerB = new HandballPlayer("player2", "nick2", 1, "Team B", 0, 1);

        Game game = new Game(List.of(basketballPlayerA, basketballPlayerB));

        PlayerResult basketballPlayerAResult = new PlayerResult(basketballPlayerA.getNickname(), basketballPlayerA.getIndividualScore(basketballPlayerA.getTeamName()));
        PlayerResult basketballPlayerBResult = new PlayerResult(basketballPlayerB.getNickname(), basketballPlayerB.getIndividualScore(""));

        List<PlayerResult> playerResults = service.countPlayersScore(game);

        assertThat(playerResults, containsInAnyOrder(basketballPlayerAResult, basketballPlayerBResult));
    }
}