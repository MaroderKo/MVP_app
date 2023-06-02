package com.mvp.generator;

import com.mvp.model.player.BasketballPlayer;
import com.mvp.model.player.HandballPlayer;

import java.util.Random;
import java.util.UUID;

public class PlayerGenerator {
    private final static Random rand = new Random();

    public static BasketballPlayer getBasketballPlayer() {
        return new BasketballPlayer(UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                rand.nextInt(),
                UUID.randomUUID().toString(),
                rand.nextInt(),
                rand.nextInt(),
                rand.nextInt());
    }

    public static HandballPlayer getHandballPlayer() {
        return new HandballPlayer(UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                rand.nextInt(),
                UUID.randomUUID().toString(),
                rand.nextInt(),
                rand.nextInt());
    }
}
