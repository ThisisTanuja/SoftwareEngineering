package CountryRaceDev;

import java.util.List;

public class StandardScoringStrategy implements ScoringStrategy{
	
	@Override
	public int calculateScore(List<Runner> runners) {
		// TODO Auto-generated method stub
		return runners.stream()
				.sorted((r1, r2) -> Integer.compare(r1.getPlace(), r2.getPlace()))
				.limit(5)
				.mapToInt(Runner::getPlace)
				.sum();
	}

}


