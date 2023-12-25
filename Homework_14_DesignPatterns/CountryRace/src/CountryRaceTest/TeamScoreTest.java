package CountryRaceTest;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import CountryRaceDev.Runner;
import CountryRaceDev.StandardScoringStrategy;
import CountryRaceDev.Team;

public class TeamScoreTest {
	@Test
	public void testTeamScoreWithStandardStrategy() {
		StandardScoringStrategy strategy = new StandardScoringStrategy();
		Team team = new Team(Arrays.asList(new Runner(1, new Team("Team A")), new Runner(2, new Team("Team A")),
				new Runner(3, new Team("Team A")), new Runner(4, new Team("Team A")),
				new Runner(5, new Team("Team A"))), strategy);

		int expectedScore = 1 + 2 + 3 + 4 + 5; // Sum of places of top 5 runners
		assertEquals(expectedScore, team.score());
	}

}
