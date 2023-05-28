package com.mvp.service.impl;

import com.mvp.model.GameType;
import com.mvp.model.game.Game;
import com.mvp.model.player.AbstractPlayer;
import com.mvp.model.player.BasketballPlayer;
import com.mvp.model.player.HandballPlayer;
import com.mvp.service.CsvObjectMapperService;
import com.mvp.service.GameExtractorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameExtractorServiceImpl implements GameExtractorService {
    private final CsvObjectMapperService csvObjectMapperService;

    @Override
    public Game extractGame(String data) {
        GameType gameType = getGameName(data);
        data = data.substring(gameType.name().length());
        data = data.trim();
        return new Game(getGamePlayers(data, gameType));
    }

    private List<? extends AbstractPlayer> getGamePlayers(String data, GameType gameType)
    {
        if (gameType == GameType.BASKETBALL) {
            return csvObjectMapperService.decode(data, BasketballPlayer.class);
        }
        if (gameType == GameType.HANDBALL) {
            return csvObjectMapperService.decode(data, HandballPlayer.class);
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could ot read data of game "+gameType.name());
    }

    private GameType getGameName(String data)
    {
        String gameName = data.split(System.lineSeparator())[0];
        try {
            return GameType.valueOf(gameName);
        }
        catch (IllegalArgumentException e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Game type %s not found", gameName));
        }
    }
}
