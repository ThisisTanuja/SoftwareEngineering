package com.sports.goteam.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


/*
 * @Entity marks the Player class as a JPA entity to represent data in the database.
 * @Data is from Lombok to generate common methods automatically, like getters, setter, equals(), hashCode() and toString()
 * @Table specifies the database table name and related information.
 */
@Entity
@Data
@Table(name = "players", schema = "public")
public class Player {
	
	/*
	 * @Id annotation indicates that the playerId field is the primary key.
	 * @GeneratedValue specifies how the primary key values should be generated. 
	 * In this case, the database will automatically generate unique values for playerId.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long playerId;
	private String playerName;
	private Integer jerseyNumber;
	private String teamName;
	
}
