//CountPlayers.js

//waiting for the web page to be fully loaded before executing the script
document.addEventListener('DOMContentLoaded', function(event) {
	event.preventDefault();
	
	//Dropdown menu for choosing a sub-action (count all players or players in a team)
	const subActionSelect = document.getElementById('subActionSelect');

	//Button which is clicked to execute the selected sub-action
	const countPlayersButton = document.getElementById('countPlayersButton');
	
	//Div containing input box for team name
	const teamNameInputContainer = document.getElementById("teamNameInputContainer");
	
	//retrieving data entered by the user from the input field
	const teamNameInput = document.getElementById("teamNameInput");
	
	//References to the Count button for Team name
	const countTeamPlayersButton = document.getElementById("countTeamPlayersButton");
	
	//Div to display the message
	const countResult = document.getElementById('countResult');
	

	//setting up an event listener for when the button is clicked
	countPlayersButton.addEventListener('click', function(event) {
		event.preventDefault();
		const selectedSubAction = subActionSelect.value;
		
		//depending on the selected sub-action, one container is displayed and the other is hidden
		if (selectedSubAction === 'countAllPlayers') {
			
			//making GET request for counting all players
			fetch(`/players/countAllPlayers`, {
				method: 'GET'

			})
				.then(response => response.text())
				.then(message => {
					countResult.textContent = message;
					setTimeout(function() {
						countResult.textContent = '';
					}, 3000);
					return
				})
		} else {
			teamNameInputContainer.style.display = "block";
		}
	});
	
	//Event listener for the button to count players by team name
	countTeamPlayersButton.addEventListener('click', function(event) {
		event.preventDefault();
		const teamName = teamNameInput.value;
		
		//checking if a team name has been entered
		if (!teamName.trim()) {
			deleteResult.textContent = "Please input a Team Name";
			return;
		}

		//making the GET request using the provided team name
		fetch(`/players/countPlayersInATeam/${teamName}`, {
			method: 'GET'
		})
			.then(response => response.text())
			.then(message => {
				countResult.textContent = message;
				setTimeout(function() {
					countResult.textContent = '';
				}, 3000);
				return;
			})
	});
})

		
		
		
		
	


