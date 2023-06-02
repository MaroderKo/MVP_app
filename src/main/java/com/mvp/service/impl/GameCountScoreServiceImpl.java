package com.mvp.service.impl;

import com.mvp.exception.NoWinnerTeamException;
import com.mvp.model.PlayerResult;
import com.mvp.model.game.Game;
import com.mvp.service.GameCountService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameCountScoreServiceImpl implements GameCountService {
    @Override
    public List<PlayerResult> countPlayersScore(Game game) {
        return game.getPlayers()
                .stream()
                .map(player -> new PlayerResult(player.getNickname(), player.getIndividualScore(getWinner(game)))).collect(Collectors.toUnmodifiableList());
    }

    private String getWinner(Game game) {
        Map<String, Integer> teamPoints = countTeamPoints(game);
        Set<Map.Entry<String, Integer>> entries = teamPoints.entrySet();
        if (entries.stream().map(Map.Entry::getValue).distinct().count() == 1) {
            throw new NoWinnerTeamException("The game has no winning team");
        }
        return entries.stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new NoWinnerTeamException("The game has no winning team"))
                .getKey();
    }

    private Map<String, Integer> countTeamPoints(Game game) {
        Map<String, Integer> teamPoints = new HashMap<>();
        game.getPlayers().forEach(player -> teamPoints.merge(player.getTeamName(), player.getTeamScore(), Integer::sum));
        return teamPoints;
    }
}
