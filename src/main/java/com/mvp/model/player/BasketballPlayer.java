package com.mvp.model.player;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class BasketballPlayer extends AbstractPlayer {
    static int SCORED_POINT_VALUE = 2;
    static int REBOUND_POINT_VALUE = 1;
    static int ASSIST_POINT_VALUE = 1;

    int scoredPoint;
    int rebound;
    int assist;


    @JsonCreator
    public BasketballPlayer(@JsonProperty("name") String name,
                            @JsonProperty("nickname") String nickname,
                            @JsonProperty("number") int number,
                            @JsonProperty("teamName") String teamName,
                            @JsonProperty("scoredPoint") int scoredPoint,
                            @JsonProperty("rebound") int rebound,
                            @JsonProperty("assist") int assist) {
        super(name, nickname, number, teamName);
        this.scoredPoint = scoredPoint;
        this.rebound = rebound;
        this.assist = assist;
    }

    @Override
    public int getIndividualScore(String winnerTeam) {
        return scoredPoint * SCORED_POINT_VALUE + rebound * REBOUND_POINT_VALUE + assist * ASSIST_POINT_VALUE + (teamName.equals(winnerTeam) ? WINNER_TEAM_POINT_VALUE : 0);
    }

    @Override
    public int getTeamScore() {
        return scoredPoint;
    }
}
