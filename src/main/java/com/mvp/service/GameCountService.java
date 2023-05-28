package com.mvp.service;

import com.mvp.model.PlayerResult;
import com.mvp.model.game.Game;

import java.util.List;

public interface GameCountService {
    List<PlayerResult> countPlayersScore(Game game);
}
