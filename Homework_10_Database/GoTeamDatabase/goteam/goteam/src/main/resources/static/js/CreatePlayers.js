//CreatePlayer.js

//waiting for the entire HTML document to be loaded and parsed before executing the following script
document.addEventListener('DOMContentLoaded', function(event) {

	//preventing the default form submission which causes a page reload
	event.preventDefault();

	//Dropdown menu for choosing a sub-action (create single player or multiple players)
	const subActionSelect = document.getElementById('subActionSelect');

	//Button which is clicked to execute the selected sub-action
	const createPlayersButton = document.getElementById('createPlayersButton');

	//Div containing form fields for creating a single player
	const createPlayerContainer = document.getElementById('createPlayerContainer');

	//Div containing form fields for creating multiple players
	const createMultiplePlayersContainer = document.getElementById('createMultiplePlayersContainer');

	// Assuming a simple array to track added jersey numbers and team names in the current session
	let addedPlayers = [];

	//listening for a button click event
	//The purpose of this function is to show the appropriate container (single or multiple player creating)
	//based on the user's dropdown menu selection
	createPlayersButton.addEventListener('click', function(event) {

		//preventing the default form submission which causes a page reload
		event.preventDefault();

		const selectedSubAction = subActionSelect.value;

		//depending on the selected sub-action, one container is displayed and the other is hidden
		if (selectedSubAction === 'createPlayer') {
			createPlayerContainer.style.display = 'block';
			createMultiplePlayersContainer.style.display = 'none';
		} else if (selectedSubAction === 'createManyPlayers') {
			createPlayerContainer.style.display = 'none';
			createMultiplePlayersContainer.style.display = 'block';
		}
	});

	/*-------------------------------------single player creation----------------------------------------------------*/

	//References for individual player creation
	const createPlayerSubmitButton = document.getElementById('createPlayerSubmitButton');
	const createdPlayerData = document.getElementById('createdPlayerData');

	//this function handles the creating of a single player
	createPlayerSubmitButton.addEventListener('click', function(event) {

		//preventing the default form submission which causes a page reload
		event.preventDefault();

		//retrieving data entered by the user from the form fields
		const playerName = document.getElementById('playerName').value;
		const jerseyNumber = document.getElementById('jerseyNumber').value;
		const teamName = document.getElementById('teamName').value;
		const totalGoals = document.getElementById('totalGoals').value;
		const totalAssists = document.getElementById('totalAssists').value;


		//checking if all the form fields are filled
		//if any fields is left empty, an error message is displayed to the user
		if (!playerName || !jerseyNumber || !teamName || !totalGoals || !totalAssists) {
			createdPlayerData.textContent = "All fields are required. Please fill out all fields.";

			//error message is cleared after 3 seconds
			setTimeout(function() {
				createdPlayerData.textContent = '';
			}, 3000);
			return;
		}

		//preventing duplicate entry from adding
		const uniqueIdentifier = `${jerseyNumber}-${teamName}`;
		if (addedPlayers.includes(uniqueIdentifier)) {
			createdPlayerData.textContent = "Duplicate player detected in the current session. Players cannot have same jersey number in the same team. Please enter a different player.";

			//resetting form fields after successful player creation
			document.getElementById('playerName').value = "";
			document.getElementById('jerseyNumber').value = "";
			document.getElementById('teamName').value = "";
			document.getElementById('totalGoals').value = "";
			document.getElementById('totalAssists').value = "";

			setTimeout(function() {
				createdPlayerData.textContent = "";
			}, 3000);
			return;
		}

		//composing a player object based on user input
		const player = {
			playerName,
			jerseyNumber,
			teamName,
			totalGoals,
			totalAssists
		};

		console.log('Player Object:', player);

		//making a POST request to the server to create a new player
		fetch(`/players/createPlayer`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(player),
		})
			.then(response => {

				//transforming the raw response from the server into text
				return response.text();
			})
			.then(message => {

				//displaying the server's response to the user
				addedPlayers.push(uniqueIdentifier);
				createdPlayerData.textContent = message;

				//resetting form fields after successful player creation
				document.getElementById('playerName').value = "";
				document.getElementById('jerseyNumber').value = "";
				document.getElementById('teamName').value = "";
				document.getElementById('totalGoals').value = "";
				document.getElementById('totalAssists').value = "";

				//clearing the server's response message after 3 seconds
				setTimeout(function() {
					createdPlayerData.textContent = "";
				}, 3000);
				return;
			})
	});

	/*-----------------------------------------------multiple player creation---------------------------------------*/

	//References for multiple player creation
	const createMultiplePlayersSubmitButton = document.getElementById('createMultiplePlayersSubmitButton');
	const createdMultiplePlayersData = document.getElementById('createdMultiplePlayersData'); // Container for displaying messages
	const playersListTextarea = document.getElementById('playersList');

	//This function handles the creation of multiple players
	createMultiplePlayersSubmitButton.addEventListener('click', function(event) {
		// Preventing default form submission behavior
		event.preventDefault();

		let players = [];
		try {
			// Attempting to parse the JSON string containing multiple players provided by the user
			players = JSON.parse(playersListTextarea.value);

			// Ensuring the parsed data is an array
			if (!Array.isArray(players)) {
				throw new Error('Parsed data is not an array');
			}
		} catch (error) {
			// If json parsing fails, an error message is displayed to the user
			createdMultiplePlayersData.textContent = "Invalid JSON format. Please ensure you've entered a correct list of players.";
			setTimeout(function() {
				createdMultiplePlayersData.textContent = "";
			}, 3000);
			return;
		}

		// Validating each player's data in the provided list to ensure they all have the required fields
		let invalidPlayer = false;
		for (let player of players) {
			const requiredFields = ['playerName', 'jerseyNumber', 'teamName', 'totalGoals', 'totalAssists'];
			for (let field of requiredFields) {
				if (!(field in player)) {
					invalidPlayer = true;
					break;
				}
			}
			if (invalidPlayer) {
				break;
			}

			// Preventing duplicate entries from adding
			const uniqueIdentifier = `${player.jerseyNumber}-${player.teamName}`;
			if (addedPlayers.includes(uniqueIdentifier)) {
				createdMultiplePlayersData.textContent = "Duplicate player detected in the current session. Players cannot have same jersey number in the same team. Please enter different players.";
				playersListTextarea.value = '';
				setTimeout(function() {
					createdMultiplePlayersData.textContent = "";
				}, 3000);
				return;
			}
		}

		if (invalidPlayer) {
			// If any of the required fields are missing, displays an error message to the user
			createdMultiplePlayersData.textContent = "Each player must have a playerName, jerseyNumber, teamName, totalGoals, and totalAssists.";
			setTimeout(function() {
				createdMultiplePlayersData.textContent = "";
			}, 3000);
			return;
		}
		//sending the list of player objects to the server to be created
		fetch(`/players/createManyPlayers`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(players),
		})
			.then(response => {

				//transforming the server's response message to the user
				return response.text();
			})
			.then(message => {

				players.forEach(player => {
					const uniqueIdentifier = `${player.jerseyNumber}-${player.teamName}`;
					addedPlayers.push(uniqueIdentifier);
				});

				//displaying the server's respoonse message to the user
				createdMultiplePlayersData.textContent = message;

				//clearing the textarea containing the list of players
				playersListTextarea.value = '';

				//clearing the server's response message after 3 seconds
				setTimeout(() => {
					createdMultiplePlayersData.textContent = '';
				}, 3000);
				return;
			});
	});
});