//GetPlayers.js

//waiting for the entire HTML document to be loaded and parsed before executing the following script
document.addEventListener("DOMContentLoaded", function() {

	//Dropdown menu for choosing a sub-action (get all players, get player by id or get players by team)
	const subActionSelect = document.getElementById("subActionSelect");

	//Button which is clicked to execute the selected sub-action
	const getPlayersButton = document.getElementById("getPlayersButton");

	//Div containing input box for ID
	const playerIdInputContainer = document.getElementById("playerIdInputContainer");

	//Div containing input box for team name
	const teamNameInputContainer = document.getElementById("teamNameInputContainer");

	//retrieving data entered by the user from the input fields
	const playerIdInput = document.getElementById("playerIdInput");
	const teamNameInput = document.getElementById("teamNameInput");

	//References for Display button for ID and Team name
	const displayIdButton = document.getElementById("displayIdButton");
	const displayTeamButton = document.getElementById("displayTeamButton");

	//Function to visually represent the player(s) data in a table format
	function displayPlayers(players) {
		const table = document.getElementById("allPlayersTable");
		const tbody = table.querySelector("tbody");

		//removing previously displayed player rows from the table
		tbody.innerHTML = "";

		//looping through the players array and adding each player's information as a new row in the table
		players.forEach(player => {
			const playerRow = document.createElement("tr");
			[player.playerId, player.playerName, player.jerseyNumber, player.teamName, player.totalGoals, player.totalAssists, player.badgeAwarded].forEach(item => {
				const td = document.createElement("td");
				td.textContent = item;
				playerRow.appendChild(td);
			});

			//adding the created row to the table body
			tbody.appendChild(playerRow);
		});

		//making the table section containing player results visible
		document.getElementById("allPlayersResult").style.display = "block";
	}

	//Function to process the server's response after making an HTTP request
	function handleServerResponse(response) {

		//attempting to parse the server response as JSON
		response.json().then(players => {

			//If the parsed data is an array of players, display them all
			if (Array.isArray(players) && players.length) {
				displayPlayers(players);
			} else if (typeof players === 'object' && players !== null) {

				// If the parsed data is just one player object, wrap it in an array and display
				displayPlayers([players]);
			} else {

				//handling any unexpected data formats from the server
				alert("Unexpected data format received.");
			}
		}).catch(error => {
			//displaying an alert if there's an issue with parsing the server's response as JSON
			alert("Error parsing JSON: " + error.message);
		});

	}

	//Event listener for the "Get Players" button
	getPlayersButton.addEventListener('click', function() {
		const selectedAction = subActionSelect.value;

		//hiding any previously shown input fields and results sections
		playerIdInputContainer.style.display = "none";
		teamNameInputContainer.style.display = "none";
		document.getElementById("allPlayersResult").style.display = "none";

		//taking action based on the selected option in the dropdown
		if (selectedAction === 'getAllPlayers') {
			fetch('/players/getAllPlayers')	//fetching all players from the server
				.then(handleServerResponse);
		} else if (selectedAction === 'getPlayerById') {
			playerIdInputContainer.style.display = "block";  //showing the input field for the player ID
		} else if (selectedAction === 'getPlayersByTeam') {
			teamNameInputContainer.style.display = "block";  //showing the input field for the team name
		}
	});

	//Event listener for the "Display" button next to the player ID input
	displayIdButton.addEventListener('click', function() {
		const playerId = playerIdInput.value;
		if (!playerId) {

			//prompting the user if they've not entered a player ID
			alert("Please input a Player ID");
			return;
		}

		//fetching player data by player ID from the server
		fetch(`/players/getPlayerById/${playerId}`)
			.then(handleServerResponse);
	});

	//Event listener for the "Display" button next to the team name input
	displayTeamButton.addEventListener('click', function() {
		const teamName = teamNameInput.value;
		if (!teamName.trim()) {

			//prompting the user if they've not entered a team name
			alert("Please input a Team Name");
			return;
		}

		//fetching player data by team name from the server
		fetch(`/players/getPlayersByTeam/${teamName}`)
			.then(handleServerResponse);
	});
});
