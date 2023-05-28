package com.mvp.service.impl;

import com.mvp.model.PlayerResult;
import com.mvp.model.game.Game;
import com.mvp.service.GameCountService;
import com.mvp.service.GameExtractorService;
import com.mvp.service.MvpService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MvpServiceImpl implements MvpService {
    private static final String DATA_FOLDER_NAME = "resources";

    private final GameCountService gameCountService;
    private final GameExtractorService gameExtractorService;
    private final ResourcePatternResolver resourcePatternResolver;

    @Override
    public String getMostValuablePlayer() {
        Resource[] resources = getGameResources();
        return getMVP(resources);
    }

    private Resource[] getGameResources() {
        Resource[] resources;
        try {
            resources = resourcePatternResolver.getResources("file:" + DATA_FOLDER_NAME + "/*");
            if (resources.length == 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Games not found in source directory");
            }
            return resources;
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Data resource folder could not be read");
        }

    }

    private Map<String, Integer> calculateGameState(Resource[] resources) {
        Map<String, Integer> playerScoreResults = new HashMap<>();
        for (Resource resource : resources) {
            try {
                String data = resource.getContentAsString(StandardCharsets.UTF_8);
                Game game = gameExtractorService.extractGame(data);
                List<PlayerResult> playerResults = gameCountService.countPlayersScore(game);
                playerResults.forEach(result -> playerScoreResults.merge(result.getNickName(), result.getScore(), Integer::sum));
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while reading game data");
            }
        }
        return playerScoreResults;
    }

    private String getMVP(Resource[] resources) {
        return calculateGameState(resources)
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR))
                .getKey();
    }
}
