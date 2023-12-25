package CountryRaceTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import CountryRaceDev.Team;

/**
 * Test class for Team's constructor
 * This class tests various scenarios of team name assignments.
 */
public class TeamConstructorTest {

    @Test
    public void testNullTeamName() {
        Team team = new Team();
        assertEquals("Unknown Team", team.getTeamName());
    }

    @Test
    public void testEmptyTeamName() {
        Team team = new Team("");
        assertEquals("Unknown Team", team.getTeamName());
    }

    @Test
    public void testWhitespaceTeamName() {
        Team team = new Team("    ");
        assertEquals("Unknown Team", team.getTeamName());
    }

    @Test
    public void testValidTeamName() {
        Team team = new Team("TeamA");
        assertEquals("TeamA", team.getTeamName());
    }

}