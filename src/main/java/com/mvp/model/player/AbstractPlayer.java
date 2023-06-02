package com.mvp.model.player;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public abstract class AbstractPlayer {
    protected final static int WINNER_TEAM_POINT_VALUE = 10;

    protected final String name;
    protected final String nickname;
    protected final int number;
    protected final String teamName;

    public AbstractPlayer(String name, String nickname, int number, String teamName) {
        this.name = name;
        this.nickname = nickname;
        this.number = number;
        this.teamName = teamName;
    }

    public abstract int getIndividualScore(String winnerTeam);

    public abstract int getTeamScore();
}
