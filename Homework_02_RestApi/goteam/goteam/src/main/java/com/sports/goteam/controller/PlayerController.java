package com.sports.goteam.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sports.goteam.model.Player;
import com.sports.goteam.service.PlayerService;

/**
 * This annotation is used to identify PlayerController class as RestController which is responsible for handling
 * HTTP requests related to players.
 */
@RestController

/**
 * This annotation at the class level specifies the base URL path for all the end points in this controller.
 * All end points in this controller will be under the '/players' URL path.
 */
@RequestMapping("/players")
public class PlayerController {
	//This annotation is used to create an instance of PlayerService automatically.
	@Autowired
	private PlayerService playerService;
	
	//getAllPlayers() is mapped to the HTTP Get request for the '/getAllPlayers' end point.
	@GetMapping("/getAllPlayers")
	public ResponseEntity<Object> getAllPlayers(){
		//Retrieves all players from the database.
		List<Player> players = playerService.getAllPlayers();
		/*
		 * Checks if players were found and returns the ResponseEntity object accordingly.
		 * If found, it creates a ResponseEntity with HTTP status code of 302(found), and
		 * sets the body to return the list of players.
		 * If no players found, it creates RE with HTTP status code of 404(not found), and
		 * sets a message in the body.
		 */
		return players != null ? ResponseEntity.status(HttpStatus.FOUND).body(players) :
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("No players found!");
	}

	//getPlayerById() is mapped to the HTTP Get request for the '/getPlayerById' using @PathVariable to capture 
	//the id parameter from the URL.
	@GetMapping("/getPlayerById/{playerId}")
	public ResponseEntity<Object> getPlayerById(@PathVariable Long playerId){
		//Retrieves a specified player based on the id.
		Player  player = playerService.getPlayerById(playerId);
		/**
		 * Checks if the player is found.
		 * If yes, returns the player with HTTP status code Found.
		 * If no, returns a message with HTTP status code Not_Found.
		 */
		return player != null ? ResponseEntity.status(HttpStatus.FOUND).body(player) :
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("No player found!");
		
	}
	
	//getPlayersByTeam() is mapped to the HTTP Get request for the '/getPlayersByTeam' using @PathVariable to capture
	//the team name parameter from the URL.
	@GetMapping("/getPlayersByTeam/{teamName}")
	public ResponseEntity<Object> getPlayersByTeam(@PathVariable String teamName) {
		//Retrieves a list of players based on the team name.
	    List<Player> players = playerService.getPlayersByTeam(teamName);
	    /**
	     * Checks if players are found.
	     * If yes, returns the list with HTTP status code Found.
	     * If no, returns a message with HTTP status code Not_Found.
	     */
	    return players != null ? ResponseEntity.status(HttpStatus.FOUND).body(players) :
	    	ResponseEntity.status(HttpStatus.NOT_FOUND).body("No players found in that team!");
	}

	//createPlayer() is mapped to the HTTP Post request for the '/createPlayer' with @RequestBody Player object.
    @PostMapping("/createPlayer")
    public ResponseEntity<Object> createPlayer(@RequestBody(required = false) Player player) {
    	/**
    	 * checks if the request body is null.
    	 * if not, adds a player to the database.
    	 */
    	Player createdPlayer = null;
    	if(player != null) {
    		createdPlayer = playerService.createPlayer(player);
    	}
        /**
         * If player is added, a message is displayed with HTTP status Created.
         * If player is not added successfully, a message is displayed with HTTP status Bad_Request.
         */
        return createdPlayer != null ? ResponseEntity.status(HttpStatus.CREATED).body("Player is created successfully!") : 
        	ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sorry! Request body is missing. Player cannot be created!");
    }
    
    //createManyPlayers() is mapped to the HTTP Post request for the '/createManyPlayers' with @RequestBody of list of players.
    @PostMapping("/createManyPlayers")
      public ResponseEntity<Object> createManyPlayers(@RequestBody(required = false) List<Player> players){
    	/*
    	 * Checks if the request body is null.
    	 * if not, add the list to the database.
    	 */
    	List<Player> createdPlayers = null;
    	if(players != null) {
    		createdPlayers = playerService.createManyPlayer(players);
    	}
    	
    	/**
    	 * If player is added, a message is displayed with HTTP status Created.
    	 * If player is not added successfully, a message is displayed with HTTP status Bad_Request.
    	 */
    	return createdPlayers != null ? ResponseEntity.status(HttpStatus.CREATED).body("Players are created successfully!") :
    		ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sorry! Request body is missing. Players cannot be created!");
    }
    
    //updatePlayer() is mapped to the HTTP Put request for the '/updatePlayer' using @PathVariable to capture id and @RequestBody Player object.
    @PutMapping("/updatePlayer/{playerId}")
    public ResponseEntity<Object> updatePlayer(@PathVariable Long playerId, @RequestBody Player updatePlayer){
    	//Updates the existingPlayer with the updatePlayer
    	Player updatedPlayer = playerService.updatePlayer(playerId, updatePlayer);
    	/**
    	 * if updated successfully, returns the updated player with HTTP status code OK.
    	 * If existingPlayer not found, a message is displayed with HTTP status code Not_Found.
    	 */
    	return updatedPlayer != null ? ResponseEntity.status(HttpStatus.OK).body(updatedPlayer) :
    		ResponseEntity.status(HttpStatus.NOT_FOUND).body("No player found to update!");
 
    }
    
    //deletePlayerById() is mapped to the HTTP Delete request for the '/deletePlayerById' using @PathVariable to capture id.
    @DeleteMapping({"/deletePlayerById/{playerId}"})
    public ResponseEntity<Object> deletePlayerById(@PathVariable Long playerId) {
    	//Deletes a specified player and returns true if successful, or false otherwise.
        Boolean isDeleted = playerService.deletePlayerById(playerId);
        /**
         * If deletion is successful, returns a message and HTTP status code OK.
         * If not, returns a message and HTTP status code Bad_Request.
         */
        return isDeleted ? ResponseEntity.status(HttpStatus.OK).body("Player deleted successfully!") :
        	ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No player found to delete!");
    }
    
    //deletePlayersByTeam() is mapped to the HTTP Delete request for the '/deletePlayersByTeam' using @PathVariable to capture the team name.
    @DeleteMapping("/deletePlayersByTeam/{teamName}")
    public ResponseEntity<Object> deletePlayersByTeam(@PathVariable String teamName){
    	//Delete players belongs to a specified team and returns true if successful, or false otherwise.
    	Boolean areDeleted = playerService.deletePlayersByTeam(teamName);
    	/*
    	 * If deletion is successful, returns a message and HTTP status code OK.
    	 * If not, returns a message and HTTP status code Bad_Request.
    	 */
    	return areDeleted ? ResponseEntity.status(HttpStatus.OK).body("Players deleted successfully!") :
    		ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No players found in that team to delete!");
    }
    
    //deleteAllPlayers() is mapped to the HTTP Delete request for the '/deleteAllPlayers'
    @DeleteMapping("/deleteAllPlayers")
    public ResponseEntity<Object> deleteAllPlayers(){
    	//Delete all players, and returns true if successful, or false otherwise.
    	Boolean areDeleted = playerService.deleteAllPlayers();
    	/**
    	 * If deletion is successful, returns a message and HTTP status code OK.
    	 * If not, returns a message and HTTP status code Bad_Request.
    	 */
    	return areDeleted ? ResponseEntity.status(HttpStatus.OK).body("All players deleted successfully!") :
    		ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No players found to delete!");
    }
	
	

}
