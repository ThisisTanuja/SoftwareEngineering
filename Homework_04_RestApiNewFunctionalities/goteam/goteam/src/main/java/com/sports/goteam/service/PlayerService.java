package com.sports.goteam.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sports.goteam.model.Player;
import com.sports.goteam.repository.PlayerRepository;

//Service class for managing Player entities.
@Service
public class PlayerService {
	
	/**
	 * This annotation is used to create an instance of PlayerRepository which allows
	 * the service to interact with the database using the PlayerRepository without manually
	 * creating an instance of it.
	 **/
	@Autowired
	private PlayerRepository playerRepository;
	
	public List<Player> getAllPlayers(){
		/**
		 * Uses the playerRepository to fetch all players from the database.
		 * findAll() method of PlayerRepository retrieves all records of the Player entity from the associated database table.
		 * The resultant type is Iterable<Player>, but in case there are no players in the database, it returns null.
		 * otherwise, returns a list of players.
		 **/
		List<Player> players = (List<Player>) playerRepository.findAll();
		return (players.isEmpty()) ? null : players;
	}
	
	public Player getPlayerById(Long playerId) {
		/**
		 * Uses the playerRepository to find a player by their id. 
		 * findById() method of PlayerRepository searches for a record in the database that matches the specified id
		 * The resultant type is Optional<Player> which may contain a Player object and returns it if a matching player is found in the database,
		 * or may be empty and returns null if no matching player is found.
		 **/
		return playerRepository.findById(playerId).orElse(null);
	}
	
	public List<Player> getPlayersByTeam(String teamName){
		/**
		 * Uses the playerRepository to retrieve all players from the database and converted to a Stream of Player objects using the stream() method.
		 * It then filter the list whose team name matches the parameter's team name and is collected to a new list.
		 * Returns the filtered list of players belonging to the specified team, if found or returns null otherwise
		 **/
		List<Player> players = ((List<Player>) playerRepository.findAll()).stream()
				.filter(player -> teamName.equalsIgnoreCase(player.getTeamName()))
				.collect(Collectors.toList());
		return players.isEmpty() ? null : players;
	}

	public Player createPlayer(Player player) {
		/**
		 * saves the player to the database
		 **/
		return playerRepository.save(player);
	}

	public List<Player> createManyPlayer(List<Player> players){
		/**
		 * saves a list of players to the database
		 **/
		return (List<Player>)playerRepository.saveAll(players);
	}
	
	public Player updatePlayer(Long playerId, Player updatedPlayer) {
		//Finds the existing player in the database by id
		Player existingPlayer = playerRepository.findById(playerId).orElse(null);
		//Check if the existing player existed in the database
		if(existingPlayer != null) {
			//Checks and updates the existing player's name if the updated player has a non-null name.
			if(updatedPlayer.getPlayerName() != null) {
				existingPlayer.setPlayerName(updatedPlayer.getPlayerName());
			}
			//Checks and updates the existing player's jersey number if the updated player has a non-null jersey number.
			if(updatedPlayer.getJerseyNumber() != null) {
				existingPlayer.setJerseyNumber(updatedPlayer.getJerseyNumber());
			}
			//Checks and updates the existing player's team name if the updated player has a non-null team name.
			if(updatedPlayer.getTeamName() != null) {
				existingPlayer.setTeamName(updatedPlayer.getTeamName());
			}
			//Saves the updated existing player in the database
			existingPlayer =  playerRepository.save(existingPlayer);
		}
		//Returns the existing player after necessary updates.
		return existingPlayer;
	}
	
	public boolean deletePlayerById(Long playerId) {
		//boolean flag to indicate whether the player was successfully deleted from the database.
		boolean isDeleted = false;
		//Retrieves player based on id. If found, returns a player, or null otherwise.
		Player player = playerRepository.findById(playerId).orElse(null);
		//Checks if the player existed in database.
		if(player != null) {
			//deletes the player from the database and sets the flag to true.
			playerRepository.delete(player);
			isDeleted = true;
		}
		//Returns the flag. If player was found and deleted, it returns true.
		//If no player was found or if the deletion was unsuccessful, it returns false.
		return isDeleted;
		
	}
	
	public boolean deletePlayersByTeam(String teamName) {
		//boolean flag to indicate whether the player was successfully deleted from the database.
		boolean areDeleted = false;
		//Retrieves all players from the database, filter those belonging to the specified team and collects the filtered players into a new list.
		List<Player> players = ((List<Player>)playerRepository.findAll()).stream()
				.filter(player -> teamName.equalsIgnoreCase(player.getTeamName()))
				.collect(Collectors.toList());
		//Checks if any players were found and filtered
		if(!players.isEmpty()) {
			//Delete all filtered players from the database and sets the flag to true.
			playerRepository.deleteAll(players);
			areDeleted = true;
		}
		//Returns the flag
		return areDeleted;
	}
	
	public boolean deleteAllPlayers() {
		boolean areDeleted = false; 
		//checks if there are any players in the database
		if(!((List<Player>)playerRepository.findAll()).isEmpty()) {
			//delete all players if any player exists and sets the flag to true.
			playerRepository.deleteAll();
			areDeleted = true;
		}
		//Returns the flag
		return areDeleted;
	}
	
	//---------------------------new functionality assignBadges() implemented------------------------------------//
	/**
	 * defines a method that takes a 'Player' object as a parameter and assigns a badge (Gold, Silver, or Bronze) to the player
	 * based on their performance, calculated from goals and assists
	 **/
	public void assignBadges(Player player){
		
		//checks if 'totalGoals' and 'totalAssists' are null
		int totalGoals = player.getTotalGoals() != null ? player.getTotalGoals() : 0;
		int totalAssists = player.getTotalAssists() != null ? player.getTotalAssists() : 0;
		
		//totalScore is calculated
		int totalScore = totalGoals + totalAssists;
		
		//checks the value of 'totalScore' to determine the badge to be awarded.
		if(totalScore >= 20) {
			player.setBadgeAwarded("Gold");
		}
		else if(totalScore >= 10 && totalScore < 20) {
			player.setBadgeAwarded("Silver");
		}
		else {
			player.setBadgeAwarded("Bronze");
		}
	}
	
	//---------------------new functionality countPlayersInATeam() function-----------//
	/**
	 * defines a method that takes @param teamName 
	 * returns a long value representing the count of players in the specified team
	 */
	public long countPlayersInATeam(String teamName) {
		
		//find all players of a particular team and calculates its size to return the count.
		List<Player> players = (List<Player>)getPlayersByTeam(teamName);
		return players != null ? players.size() : 0;	
	}
	
	//----------------------new functionality findPlayersWithSameBadge() and getTopGoalScorer() function---------//
	public List<Player> getPlayersWithSameBadge(String badgeAwarded){
		/**
		 * Uses the playerRepository to retrieve all players from the database and converted to a Stream of Player objects using the stream() method.
		 * It then filter the list of all players that have the same badge and is collected to a new list.
		 * Returns the filtered list of players belonging to the specified team, if found or returns null otherwise
		 **/
		List<Player> players = ((List<Player>) playerRepository.findAll()).stream()
				.filter(player -> badgeAwarded.equalsIgnoreCase(player.getBadgeAwarded()))
				.collect(Collectors.toList());
		return players.isEmpty() ? null : players;
	}
	
	public Player getTopGoalScorer(List<Player> players) {
		/**
		 * identifies the top goal scorer among a list of players with same badge
		 */
		Player topScorer = null;
		int maxGoals = 0;
		for(Player player : players) {
			if(player.getTotalGoals() > maxGoals) {
				maxGoals = player.getTotalGoals();
				topScorer = player;
			}
		}
		return topScorer;
	}
}
