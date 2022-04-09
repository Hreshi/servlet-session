fetch("name").then(function (response) {
	response.json().then(function (data) {
		console.log("data.username --- ",data.username)
		document.getElementById('name').innerText = "Your name is " + data.username;
	})
})