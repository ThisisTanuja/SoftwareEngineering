package CountryRaceTest;

import CountryRaceDev.*;
import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;

public class BonusScoringStrategyTest {

    @Test
    public void testCalculateScoreWithBonus() {
        ScoringStrategy strategy = new BonusScoringStrategy();
        Runner runner1 = new Runner(1, new Team("Team A"));
        Runner runner2 = new Runner(2, new Team("Team A"));
        Runner runner3 = new Runner(3, new Team("Team A"));
        Runner runner4 = new Runner(4, new Team("Team A"));
        Runner runner5 = new Runner(5, new Team("Team A"));
        // Assuming bonus strategy gives a reduction for top 3 runners
        int standardScore = 1 + 2 + 3 + 4 + 5;
        int bonus = 3; // Assuming a fixed bonus of 1 per top 3 runners
        int expectedScore = standardScore - bonus;
        assertEquals(expectedScore, strategy.calculateScore(Arrays.asList(runner1, runner2, runner3, runner4, runner5)));
    }
}
