package CountryRaceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import CountryRaceDev.Results;
import CountryRaceDev.Runner;
import CountryRaceDev.Team;

/**
 * Test class for cross-country race scoring
 * This class test various scenarios of race scoring and tie-breaking.
 */

public class CrossCountryRaceScoringTest {
	private Team teamA;
	private Team teamB;
	private Team teamC;
	private Team teamD;
	
	@Before
	public void setUp() {
		teamA = new Team("A");
		teamB = new Team("B");
		teamC = new Team("C");
		teamD = null;
	}
	
	
	// Tests basic team scoring logic
	// Input: B, A, A, B, B, A, A, B, B, A
	@Test
	public void testBasicTeamScoring() {
		teamA = new Team(Arrays.asList(new Runner(3, teamA), new Runner(2, teamA), new Runner(6, teamA), new Runner(7, teamA), new Runner(10, teamA)));
        teamB = new Team(Arrays.asList(new Runner(1, teamB), new Runner(4, teamB), new Runner(5, teamB), new Runner(8, teamB), new Runner(9, teamB)));
        
        Results results = new Results(Arrays.asList(teamA, teamB));
        results.printPlacement();
        
        assertEquals(28, teamA.score());
        assertEquals(27, teamB.score());
        
        assertEquals(teamB, results.breakTie(new Team[] {teamA, teamB})[0]);
	}
	
	// Tests the scenario where a team wins with minimum required runners.
	// Input: A, A, A, A, A, A, A, B, B, B, B, B
	@Test
    public void testTeamWinsWithMinimumRunners() {
        teamA = new Team(Arrays.asList(new Runner(1, teamA), new Runner(2, teamA), new Runner(3, teamA), new Runner(4, teamA), new Runner(5, teamA)));
        teamB = new Team(Arrays.asList(new Runner(8, teamB), new Runner(9, teamB), new Runner(10, teamB), new Runner(11, teamB), new Runner(12, teamB)));

        Results results = new Results(Arrays.asList(teamA, teamB));
        results.printPlacement();
        
        assertEquals(15, teamA.score());
        assertEquals(50, teamB.score());

        assertEquals(teamA, results.breakTie(new Team[]{teamA, teamB})[0]);
    }

	// Tests tie-breaking logic using the 6th runner
	// Input: A, B, B, A, B, A, B, A, A, A, B, B
    @Test
    public void testTieBreakWithSixthRunner() {
        teamA = new Team(Arrays.asList(new Runner(1, teamA), new Runner(4, teamA), new Runner(6, teamA), new Runner(8, teamA), new Runner(9, teamA), new Runner(10, teamA)));
        teamB = new Team(Arrays.asList(new Runner(2, teamB), new Runner(3, teamB), new Runner(5, teamB), new Runner(7, teamB), new Runner(11, teamB), new Runner(12, teamB)));

        Results results = new Results(Arrays.asList(teamA, teamB));
        results.printPlacement();
        
        assertEquals(28, teamA.score());
        assertEquals(28, teamB.score());

        assertEquals(teamA, results.breakTie(new Team[]{teamA, teamB})[0]);
    }
    
    // Tests the scoring of an unknown team and a known team
    // Input: null, null, null, null, null, B, B, B, B, B
    @Test
    public void testTeamWithUnknownRunners() {
    	teamB = new Team(Arrays.asList(new Runner(6, teamB), new Runner(7, teamB), new Runner(8, teamB), new Runner(9, teamB), new Runner(10, teamB)));
        teamD = new Team(Arrays.asList(new Runner(1, teamD), new Runner(2, teamD), new Runner(3, teamD), new Runner(4, teamD), new Runner(5, teamD)));
        
        Results results = new Results(Arrays.asList(teamB, teamD));
        results.printPlacement();
        
        assertEquals("Unknown Team", teamD.getTeamName());
        assertEquals(40, teamB.score());
        assertEquals(15, teamD.score());
        
        assertEquals(teamD, results.breakTie(new Team[]{teamB, teamD})[0]);
    }
    
    /**
     * Tests the scoring when a team has less than the required number of runners
     * the exception is thrown when you try to create an instance of the Team class, so assertThrows is 
     * wrapped around the construction of the 'Team' object
     */
    // Input: A, A, A, B, B, B, B, B
    @Test
    public void testLessThan5Runners() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Team(Arrays.asList(new Runner(1, teamA), new Runner(2, teamA), new Runner(3, teamA)));
        });
        teamB = new Team(Arrays.asList(new Runner(4, teamB), new Runner(5, teamB), new Runner(6, teamB), new Runner(7, teamB), new Runner(8, teamB)));
    }
    
    // Tests the scoring when two team have less than the required number of runners
    // Input: A, A, A, B, B, B, B
    @Test
    public void testTwoTeamsLessThan5Runners() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Team(Arrays.asList(new Runner(1, teamA), new Runner(2, teamA), new Runner(3, teamA)));
        });
        assertThrows(IllegalArgumentException.class, () -> {
        	new Team(Arrays.asList(new Runner(4, teamB), new Runner(5, teamB), new Runner(6, teamB), new Runner(7, teamB)));
        });
    }
    
    // Tests the scoring when no teams are participating in a race
    // Input: no input
    @Test
    public void testNoTeamsParticipating() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Team(Arrays.asList());
        });
    }
    
    // Tests the scoring when there are duplicate runners in a team
    // Input: AA, ,A, A, A
    @Test
    public void testDuplicateRunners() {
    	assertThrows(IllegalArgumentException.class, () -> {
            new Team(Arrays.asList(new Runner(1, teamA), new Runner(1, teamA), new Runner(3, teamA), new Runner(4, teamA), new Runner(5, teamA)));
        });
    }
    
    // Tests the scoring when teams have more than 5 runners but there's no tie in their scores.
    // Input: A, A, A, B, B, B, B, A, A, A, B, B
    @Test
    public void testMoreThan5RunnersNoTie() {
        teamA = new Team(Arrays.asList(new Runner(1, teamA), new Runner(2, teamA), new Runner(3, teamA), new Runner(8, teamA), new Runner(9, teamA), new Runner(10, teamA)));
        teamB = new Team(Arrays.asList(new Runner(4, teamB), new Runner(5, teamB), new Runner(6, teamB), new Runner(7, teamB), new Runner(11, teamB), new Runner(12, teamB)));

        Results results = new Results(Arrays.asList(teamA, teamB));
        results.printPlacement();
        
        assertEquals(23, teamA.score());
        assertEquals(33, teamB.score());
        
        assertEquals(teamA, results.breakTie(new Team[]{teamA, teamB})[0]);
    }
    
    // Tests the scoring with multiple teams.
    // Input: A, A, A, B, B, B, B, A, A, A, B, B, C, C, C, C, C
    @Test
    public void testMultipleTeams() {
    	teamA = new Team(Arrays.asList(new Runner(1, teamA), new Runner(2, teamA), new Runner(3, teamA), new Runner(8, teamA), new Runner(9, teamA), new Runner(10, teamA)));
        teamB = new Team(Arrays.asList(new Runner(4, teamB), new Runner(5, teamB), new Runner(6, teamB), new Runner(7, teamB), new Runner(11, teamB), new Runner(12, teamB)));
        teamC = new Team(Arrays.asList(new Runner(13, teamC), new Runner(14, teamC), new Runner(15, teamC), new Runner(16, teamC), new Runner(17, teamC)));
        
        Results results = new Results(Arrays.asList(teamA, teamB, teamC));
        results.printPlacement();
        
        assertEquals(23, teamA.score());
        assertEquals(33, teamB.score());
        assertEquals(75, teamC.score());
        
        assertEquals(teamA, results.breakTie(new Team[] {teamA, results.breakTie(new Team[] {teamB, teamC})[0]})[0]);
    }  
}
