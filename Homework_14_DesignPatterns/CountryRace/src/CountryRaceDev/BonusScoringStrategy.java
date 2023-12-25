package CountryRaceDev;

import java.util.List;

public class BonusScoringStrategy implements ScoringStrategy {
    @Override
    public int calculateScore(List<Runner> runners) {
        // Implement bonus scoring logic here
        // Example: Standard score minus a bonus for top 3 runners
        int standardScore = new StandardScoringStrategy().calculateScore(runners);
        int bonus = runners.stream()
                           .sorted((r1, r2) -> Integer.compare(r1.getPlace(), r2.getPlace()))
                           .limit(3)
                           .mapToInt(r -> 1) // Assuming a fixed bonus for top 3 runners
                           .sum();
        return standardScore - bonus;
    }
}
