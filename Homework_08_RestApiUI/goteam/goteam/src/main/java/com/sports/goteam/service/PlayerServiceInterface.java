package com.sports.goteam.service;

import java.util.List;
import java.util.Optional;

import com.sports.goteam.model.Player;

//This interface defines the services used for managing Player entities. 
public interface PlayerServiceInterface {

	// Retrieves a list of all player. Returns a list of Player objects.
	List<Player> getAllPlayers();

	// Retrieves a player by their id. Returns an optional containing the player if
	// found, or an empty Optional if not found.
	Optional<Player> getPlayerById(Long playerId);

	// Retrieves a list of players by their team name. Returns a list of Player
	// objects belonging to the specified team.
	List<Player> getPlayersByTeam(String teamName);

	// Creates a new player. Returns an optional containing the created player, or
	// an empty optional if creation fails.
	Optional<Player> createPlayer(Player player);

	// Creates multiple players. Returns a list of created player objects.
	List<Player> createManyPlayers(List<Player> player);

	// Updates an existing player with the specified id. Returns an optional
	// containing the updated player if successful,
	// or an empty optional if update fails.
	Optional<Player> updatePlayer(Long playerId, Player player);

	// Deletes all players. Returns true if the deletion is successful,
	// false otherwise.
	Boolean deleteAllPlayers();

	// Deletes a player by their id. Return true if the deletion is successful,
	// false otherwise.
	Boolean deletePlayerById(Long playerId);

	// Deletes all players belonging to a specific team. Returns true if the
	// deletion is successful,
	// false otherwise.
	Boolean deletePlayersByTeam(String teamName);
	
	//----------------------------------------------------------------//
		//new functionality
		List<Player> assignBadges();
}
