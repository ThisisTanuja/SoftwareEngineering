//waiting for the web page to be fully loaded before executing the script
document.addEventListener('DOMContentLoaded', function() {

	//Div to display the message
	const updateResult = document.getElementById('updateResult');

	//Button that triggers teh update action
	const updatePlayerButton = document.getElementById('updatePlayerButton')

	//setting up an event listener for when the button is clicked
	updatePlayerButton.addEventListener('click', function(event) {

		//preventing the default behaviour of the button
		event.preventDefault();

		//retrieving the values entered by the user in the respective fields
		const playerId = document.getElementById('playerId').value;
		const playerName = document.getElementById('playerName').value;
		const jerseyNumber = document.getElementById('jerseyNumber').value;
		const teamName = document.getElementById('teamName').value;
		const totalGoals = document.getElementById('totalGoals').value;
		const totalAssists = document.getElementById('totalAssists').value;

		//initializing an empty object to store player updates
		const updatedPlayer = {};

		//checking if specific fields have values, and if so, adds them to the 'updatedPlayer' object
		if (playerName) updatedPlayer.playerName = playerName;
		if (jerseyNumber) updatedPlayer.jerseyNumber = jerseyNumber;
		if (teamName) updatedPlayer.teamName = teamName;
		if (totalGoals) updatedPlayer.totalGoals = totalGoals;
		if (totalAssists) updatedPlayer.totalAssists = totalAssists;

		//If no fields were provided for update, displaying a message and exiting the function
		if (Object.keys(updatedPlayer).length === 0) {
			updateResult.textContent = 'No update fields provided.';
			setTimeout(() => {
				updateResult.textContent = '';
			}, 5000);
			return;
		}

		//sending an HTTP PUT request to the server to update the player
		fetch(`/players/updatePlayer/${playerId}`, {
			method: 'PUT',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(updatedPlayer)
		})
			.then(response => response.text())
			.then(message => {
				updateResult.textContent = message;
				setTimeout(() => {
					updateResult.textContent = '';
				}, 5000);
			})
			.catch(error => {
				console.error('Error:', error);
				updateResult.textContent = 'An error occurred. Please try again later.';
				setTimeout(() => {
					updateResult.textContent = '';
				}, 5000);
			});
	});
})
