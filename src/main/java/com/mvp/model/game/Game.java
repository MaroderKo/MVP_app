package com.mvp.model.game;

import com.mvp.model.player.AbstractPlayer;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@RequiredArgsConstructor
@Value
public class Game {

    List<? extends AbstractPlayer> players;
}
