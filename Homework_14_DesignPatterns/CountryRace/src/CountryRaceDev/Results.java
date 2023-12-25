/**
 * The {@code CountryRaceDev} package organizes related classes and interfaces 
 * for a simulation of a cross-country race.
 */
package CountryRaceDev;

// importing necessary libraries and packages
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the results and standings of teams after a cross-country race.
 * This class provides methods to print the race results and resolve tie situations.
 */
public class Results {
	
	/** List of teams participating in the race. */
	private List<Team> teams;
	
	/**
	 * Constructs a new {@code Results} object representing the results for a list of teams.
	 *
	 * @param teams The list of teams participating in the race.
	 */
	public Results(List<Team> teams) {
		this.teams = teams;
	}
	
	/**
	 * Prints the race results based on team scores.
	 * In the event of a tie between team scores, tie breakers are considered.
	 */
	public void printPlacement() {
		
		// Variables to store the previous team in the loop and the winning team.
	    Team previousTeam = null;
	    Team winningTeam = null;
	    
	    // Sorting teams based on their scores and possible tie breakers.
	    List<Team> sortedTeams = teams.stream()
	            .sorted(Comparator.comparingInt(Team::score)
	                    .thenComparing(Team::getTiebreakPlace))
	            .collect(Collectors.toList());
	    
	    // Iterating through the sorted list of teams to print their standings.
	    for (Team team : sortedTeams) {
	        if (previousTeam != null && previousTeam.score() == team.score()) {
	            // It's a tie, so printing tie break place.
	            System.out.println(team.getTeamName() + " = " + team.score());
	        } else {
	            System.out.println(team.getTeamName() + " = " + team.score());
	        }
	        previousTeam = team;

	        if (winningTeam == null) {
	            winningTeam = team;
	        }
	    }

	    // Check for a tie between the top two teams and determine the overall winner.
	    if (sortedTeams.size() >= 2 && sortedTeams.get(0).score() == sortedTeams.get(1).score()) {
	        System.out.println("Team " + winningTeam.getTeamName() + " wins by tie breaker (their 6th runner finished " + winningTeam.getTiebreakPlace() + "th and Team " + sortedTeams.get(1).getTeamName() + "s 6th runner finished " + sortedTeams.get(1).getTiebreakPlace() + "th)");
	    } else {
	        System.out.println("Team " + winningTeam.getTeamName() + " wins");
	    }
	}
	
	/**
	 * Resolves ties between multiple teams.
	 * If two or more teams have the same score, the tie is broken based on the placement of their 6th runner.
	 *
	 * @param tiedTeams An array of teams that are tied.
	 * @return An array of teams sorted based on their score and tiebreakers.
	 */
	public Team[] breakTie(Team[] tiedTeams) {
		return Arrays.stream(tiedTeams)
				.sorted(Comparator.comparingInt(Team :: score)
						.thenComparing(Team :: getTiebreakPlace))
				.toArray(Team[] :: new);
	}
}
