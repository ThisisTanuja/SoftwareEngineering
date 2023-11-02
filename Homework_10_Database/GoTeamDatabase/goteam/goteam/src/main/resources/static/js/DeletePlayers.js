//DeletePlayers.js

//waiting for the entire HTML document to be loaded and parsed before executing the following script
document.addEventListener("DOMContentLoaded", function(event) {
	
	event.preventDefault();

	//Dropdown menu for choosing a sub-action (delete all players, delete player by id or delete players by team)
	const subActionSelect = document.getElementById("subActionSelect");

	//Button which is clicked to execute the selected sub-action
	const deletePlayersButton = document.getElementById("deletePlayersButton");

	//Div containing input box for ID
	const playerIdInputContainer = document.getElementById("playerIdInputContainer");

	//Div containing input box for team name
	const teamNameInputContainer = document.getElementById("teamNameInputContainer");

	//retrieving data entered by the user from the input fields
	const playerIdInput = document.getElementById("playerIdInput");
	const teamNameInput = document.getElementById("teamNameInput");

	//References to the Delete button for both ID and Team name
	const deleteIdButton = document.getElementById("deleteIdButton");
	const deleteTeamButton = document.getElementById("deleteTeamButton");

	//Div for showing results of deletion
	const deleteResult = document.getElementById("deleteResult");

	//Event listener for the main delete button
	deletePlayersButton.addEventListener('click', function() {
		const selectedSubAction = subActionSelect.value;

		//depending on the selected sub-action, one container is displayed and the other is hidden
		if (selectedSubAction === 'deleteAllPlayers') {
			
			//making DELETE request for deleting all players
			fetch('/players/deleteAllPlayers', {
				method: 'DELETE'

			})
				.then(response => response.text())
				.then(message => {
					deleteResult.textContent = message;
					setTimeout(function() {
						deleteResult.textContent = '';
					}, 3000);
					return;
				})
		} else if (selectedSubAction === 'deletePlayerById') {
			playerIdInputContainer.style.display = "block";
			teamNameInputContainer.style.display = "none";
		} else if (selectedSubAction === 'deletePlayersByTeam') {
			teamNameInputContainer.style.display = "block";
			playerIdInputContainer.style.display = "none";
		}
	});

	//Event listener for the button to delete a player by ID
	deleteIdButton.addEventListener('click', function() {
		const playerId = playerIdInput.value;

		//checking if a player ID has been entered
		if (!playerId) {
			deleteResult.textContent = "Please input a Player ID";
			return;
		}

		//making the DELETE request using the provided player ID
		fetch(`/players/deletePlayerById/${playerId}`, {
			method: 'DELETE'
		})
			.then(response => response.text()) // Changed to get text response
			.then(message => {
				deleteResult.textContent = message;
				playerIdInput.value = '';

				setTimeout(function() {
					deleteResult.textContent = '';
				}, 3000);
				return;
			})
	});

	//Event listener for the button to delete players by team name
	deleteTeamButton.addEventListener('click', function() {
		const teamName = teamNameInput.value;
		
		//checking if a team name has been entered
		if (!teamName.trim()) {
			deleteResult.textContent = "Please input a Team Name";
			return;
		}

		//making the DELETE request using the provided team name
		fetch(`/players/deletePlayersByTeam/${teamName}`, {
			method: 'DELETE'
		})
			.then(response => response.text())
			.then(message => {
				deleteResult.textContent = message;
				setTimeout(function() {
					deleteResult.textContent = '';
				}, 3000);
				return;
			})
	});
});