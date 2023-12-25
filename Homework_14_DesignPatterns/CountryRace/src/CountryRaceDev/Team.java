/**
 * The {@code CountryRaceDev} package organizes related classes and interfaces 
 * for a simulation of a cross-country race.
 */
package CountryRaceDev;

// importing necessary libraries and packages
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a team participating in a cross-country race.
 * Each {@code Team} object encapsulates information about the team's name and the list of 
 * runners representing the team.
 */
public class Team {
	
	/** List storing runners associated with this team. */
	private List<Runner> runners;

	/** Name of the team. */
	private String teamName;
	
	/** Scoring strategy of the team*/
	private ScoringStrategy scoringStrategy;
	
	
	/**
	 * Constructs a new {@code Team} without any arguments.
	 *
	 * @param noargs
	 */
	public Team() {
        this.teamName = "Unknown Team";
    }
	
	/**
	 * Constructs a new {@code Team} using only the team's name.
	 *
	 * @param teamName The name of the team. If null or empty, defaults to "Unknown Team".
	 */
	public Team(String teamName) {
		if(teamName == null || teamName.trim().isEmpty()) {
			this.teamName = "Unknown Team";
		} else {
			this.teamName = teamName;
		}
	}
	
	/**
	 * Constructs a new {@code Team} using a list of runners.
	 * The team's name is inferred from the first runner in the list.
	 *
	 * @param runners The list of runners representing the team.
	 * @throws IllegalArgumentException if the list is null, empty, or has fewer than 5 runners.
	 */
	public Team(List<Runner> runners) {
		
		// Checking for duplicate runners
		Set<Runner> uniqueRunners = new HashSet<>(runners);
        if (uniqueRunners.size() != runners.size()) {
            throw new IllegalArgumentException("Duplicate runners detected!");
        }
        
		if (runners == null || runners.size() < 5 || runners.isEmpty()) {
			throw new IllegalArgumentException("A team must consist of atleast 5 runners.");
		}
		this.runners = runners;
		
		Runner firstRunner = runners.get(0);
		if(firstRunner != null && firstRunner.getTeam() != null) {
			this.teamName = firstRunner.getTeam().getTeamName();
		} else {
			this.teamName = "Unknown Team";
		}
	}
	
	public Team(List<Runner> runners, ScoringStrategy strategy) {
		this.runners = runners;
		this.scoringStrategy = strategy;
	}
	
	/**
	 * Retrieves the name of the team.
	 *
	 * @return The name of the team.
	 */
	public String getTeamName() {
		return teamName;
	}
	
	/**
	 * Computes and returns the score of the team based on the placements of its top 5 runners.
	 * If no runners are associated with the team, returns 0.
	 *
	 * @return The computed score of the team.
	 */
//	public int score() {
//		if(runners == null) {
//			return 0;
//		}
//		return runners.stream()
//				.sorted(Comparator.comparingInt(Runner::getPlace))
//				.limit(5)
//				.mapToInt(Runner::getPlace)
//				.sum();
//	}
	
	 public int score() {
	        return scoringStrategy.calculateScore(runners);
	    }
	
	/**
	 * Retrieves the placement of the 6th runner, which can be used for tie-breaking purposes.
	 * If the team has fewer than 6 runners or no runners are associated, 
	 * returns the maximum integer value.
	 *
	 * @return The placement of the 6th runner or Integer.MAX_VALUE if less than 6 runners.
	 */
	public int getTiebreakPlace() {
		if (runners.size() < 6 || runners == null) {
			return Integer.MAX_VALUE;
		}
		return runners.get(5).getPlace();
	}                                                                                                                                                      
}