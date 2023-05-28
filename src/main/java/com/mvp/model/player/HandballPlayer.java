package com.mvp.model.player;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class HandballPlayer extends AbstractPlayer {
    static int GOAL_MADE_VALUE = 2;
    static int GOAL_RECEIVED_VALUE = -1;

    int goalMade;
    int goalReceived;

    @JsonCreator
    public HandballPlayer(@JsonProperty("name") String name,
                          @JsonProperty("nickname") String nickname,
                          @JsonProperty("number") int number,
                          @JsonProperty("teamName") String teamName,
                          @JsonProperty("goalMade") int goalMade,
                          @JsonProperty("goalReceived") int goalReceived) {
        super(name, nickname, number, teamName);
        this.goalMade = goalMade;
        this.goalReceived = goalReceived;
    }

    @Override
    public int getIndividualScore(String winnerTeam) {
        return goalMade * GOAL_MADE_VALUE + goalReceived * GOAL_RECEIVED_VALUE + (teamName.equals(winnerTeam) ? WINNER_TEAM_POINT_VALUE : 0);
    }

    @Override
    public int getTeamScore() {
        return goalMade;
    }
}
