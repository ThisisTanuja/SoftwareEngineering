/**
 * The {@code CountryRaceDev} package organizes related classes and interfaces 
 * for a simulation of a cross-country race.
 */
package CountryRaceDev;

import java.util.Objects;

/**
 * Represents an individual participant in a cross-country race.
 * Each {@code Runner} object encapsulates information about the runner's 
 * placement in the race and the team to which they belong.
 * <p>
 * For instance, a {@code Runner} might have a placement of 3, indicating they 
 * finished third in the race.
 * </p>
 */
public class Runner {
	
	/** The position or ranking of the runner in a race. */
	private int place;
	
	/** The team to which the runner belongs. */
	private Team team;
	
	/**
	 * Constructs a new {@code Runner} with the specified placement and associated team.
	 *
	 * @param place The placement or ranking of the runner in the race. 
	 * @param team The team to which the runner belongs.
	 */
	public Runner(int place, Team team) {
		this.place = place;
		this.team = team;
	}

	/**
	 * Retrieves the placement or ranking of the runner in the race.
	 *
	 * @return The placement of the runner.
	 */
	public int getPlace() {
		return place;
	}

	/**
	 * Retrieves the team to which the runner belongs.
	 *
	 * @return The team of the runner.
	 */
	public Team getTeam() {
		return team;
	}
	
	/**
	 * Compares this Runner to the specified object. The result is {@code true} if and only if the argument is not
	 * {@code null} and is a {@code Runner} object that represents the same place as this object.
	 *
	 * @param object the object to compare this {@code Runner} against.
	 * @return {@code true} if the given object represents a {@code Runner} equivalent to this runner, {@code false} otherwise.
	 */
	@Override
	public boolean equals(Object object) {
	    if (this == object) return true;
	    if (object == null || getClass() != object.getClass()) return false;
	    Runner runner = (Runner) object;
	    return place == runner.place;
	}

	/**
	 * Returns a hash code for this runner. The hash code is computed based on the place of the runner.
	 *
	 * @return a hash code value for this object.
	 */
	@Override
	public int hashCode() {
	    return Objects.hash(place);
	}
}
