package CountryRaceTest;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import CountryRaceDev.Runner;
import CountryRaceDev.StandardScoringStrategy;
import CountryRaceDev.Team;

public class StandardScoringStrategyTest {
	@Test
    public void testCalculateScore() {
        StandardScoringStrategy strategy = new StandardScoringStrategy();
        Runner runner1 = new Runner(1, new Team("Team A"));
        Runner runner2 = new Runner(2, new Team("Team A"));
        Runner runner3 = new Runner(3, new Team("Team A"));
        Runner runner4 = new Runner(4, new Team("Team A"));
        Runner runner5 = new Runner(5, new Team("Team A"));
        // Assuming score is sum of places of top 5 runners
        int expectedScore = 1 + 2 + 3 + 4 + 5;
        assertEquals(expectedScore, strategy.calculateScore(Arrays.asList(runner1, runner2, runner3, runner4, runner5)));
    }

}
