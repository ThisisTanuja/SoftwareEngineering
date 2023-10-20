//TopGoalScorer.js

//waiting for the entire HTML document to be loaded and parsed before executing the following script
document.addEventListener('DOMContentLoaded', function(event) {
	event.preventDefault();

	//Button which is clicked to trigger the top goal scorer action
	const searchButton = document.getElementById('searchButton');

	//retrieving data entered by the user from the input fields
	const badgeInput = document.getElementById('badgeInput');

	//Div for showing results of top goal scorer
	const topGoalScorerResult = document.getElementById('topGoalScorerResult');
	
	// Adding an event listener to handle the click event for the 'searchButton'
	searchButton.addEventListener('click', function(event) {

		event.preventDefault();

		// Getting value from the badge input
		const badgeAwarded = badgeInput.value.trim();
		
		// Checking if the user has entered a value for the badge.
		if (badgeAwarded) {
			
			// Making a GET request to the server endpoint to fetch the top goal scorer for the specified badge
			fetch(`/players/topGoalScorer/${badgeAwarded}`, {
				method: 'GET'
			})
				.then(response => response.text())
				.then(message => {
					topGoalScorerResult.textContent = message;
					setTimeout(function() {
						topGoalScorerResult.textContent = '';
					}, 5000);
					return;
				});
		} else {
			
			// If the user hasn't entered a badge name, show an error message
			topGoalScorerResult.textContent = 'Please enter a badge name.';
		}
	});
});
