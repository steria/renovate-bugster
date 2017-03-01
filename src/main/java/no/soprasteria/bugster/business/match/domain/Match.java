package no.soprasteria.bugster.business.match.domain;

import no.soprasteria.bugster.business.team.domain.Team;

public interface Match extends Comparable<Match> {

    void setId(Integer id);

    Integer getId();

    Team getHomeTeam();

    void setHomeTeam(Team homeTeam);

    Team getAwayTeam();

    void setAwayTeam(Team awayTeam);

    Score getScore();

    void setScore(Score score);
}