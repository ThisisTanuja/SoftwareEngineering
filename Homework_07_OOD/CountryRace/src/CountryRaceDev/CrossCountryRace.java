package CountryRaceDev;

import java.util.*;

/**
 * Represents a cross-country race where runners from different teams compete. 
 * This class provides a main method to input the sequence of teams for runners 
 * and subsequently calculates and displays the results based on team scores.
 * <p>
 * Example usage:
 * <pre>
 *   Enter the team sequence for runners (e.g. A, A, B, B, ...):
 *   A, A, A, A, A, B, B, B, B, B
 *   ...
 * </pre>
 * </p>
 * 
 * Examples used in this program:
 * 1. testBasicTeamScoring: B, A, A, B, B, A, A, B, B, A
 * 2. testTeamWinsWithMinimumRunners: A, A, A, A, A, A, A, B, B, B, B, B
 * 3. testTieBreakWithSixthRunner: A, B, B, A, B, A, B, A, A, A, B, B
 * 4. testTeamWithUnknownRunners: null, null, null, null, null, B, B, B, B, B
 * 5. testLessThan5Runners: A, A, A, B, B, B, B, B
 * 6. testTwoTeamsLessThan5Runners: A, A, A, B, B, B, B
 * 7. testNoTeamsParticipating: no input
 * 8. testMoreThan5RunnersNoTie: A, A, A, B, B, B, B, A, A, A, B, B
 * 9. testMultipleTeams: A, A, A, B, B, B, B, A, A, A, B, B, C, C, C, C, C
 */
public class CrossCountryRace {

    /**
     * The main method to drive the cross-country race program. 
     * Asks the user to input the team sequence for runners and then 
     * displays the results based on team scores.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {

        // Initializing a scanner to capture user input.
        Scanner sc = new Scanner(System.in);

        while(true) {
        	
            // Prompting the user for input.
            System.out.println("Enter the team sequence for runners (e.g. A, A, B, B, ...) or 'EXIT' to exit the program.");
            String prompt = sc.nextLine();

            // Checking for exit condition
            if(prompt.equalsIgnoreCase("EXIT")) {
            	break;
            }
            
            String[] inputs = prompt.toUpperCase().split(",\\s*");

            // Storing runners in a map keyed by their respective teams.
            Map<String, List<Runner>> teamMap = new HashMap<>();
            
            for (int i = 0; i < inputs.length; i++) {
                Team team = new Team(inputs[i]);
                Runner runner = new Runner(i + 1, team);
                teamMap.computeIfAbsent(inputs[i], k -> new ArrayList<>()).add(runner);
            }

            // Converting team runner lists to a list of Team objects.
            List<Team> teams = new ArrayList<>();
            for (Map.Entry<String, List<Runner>> entry : teamMap.entrySet()) {
                teams.add(new Team(entry.getValue()));
            }
            
            // Calculating and printing the results.
            Results results = new Results(teams);
            results.printPlacement();
         
        }

        // Closing the scanner to free resources.
        sc.close();
    }
}