//getting the dropdown element by its ID
const actionSelect = document.getElementById('actionSelect');

//getting the Go button element by its ID
const goButton = document.getElementById('indexGoButton');

//adding an event listener to the Go button for the click event
goButton.addEventListener('click', function() {
	
	//retrieving the selected value from the dropdown
	const selectedAction = actionSelect.value;
	
	//checking if an action is selected from the dropdown
	if (selectedAction) {
		// redirecting to the corresponding action page or handle the action as needed
		switch (selectedAction) {
			case 'getPlayers':
				window.location.href = 'GetPlayers.html'; // redirecting to Get Players page
				break;
			case 'createPlayers':
				window.location.href = 'CreatePlayers.html'; // redirecting to Create Player page
				break;
			case 'updatePlayer':
				window.location.href = 'UpdatePlayer.html'; // redirecting to Update Player page
				break;
			case 'deletePlayers':
				window.location.href = 'DeletePlayers.html'; // redirecting to Delete Players page
				break;
			case 'countPlayers':
				window.location.href = 'CountPlayers.html'; // redirecting to Count Players page
				break;
			case 'topGoalScorer':
				window.location.href = 'TopGoalScorer.html'; // redirecting to Top Goal Scorer page
				break;
				
			//handling other potential values, if any
			default:
				break;
		}
	}
});

