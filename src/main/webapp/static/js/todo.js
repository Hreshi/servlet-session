document.getElementById("add-todo-button").addEventListener("click",function () {
	let todoInput = document.getElementById("todo-input")
	let task = todoInput.value
	todoInput.value = ""
	sendAddTaskRequest(task)
})

function sendAddTaskRequest(taskTitle) {
	let data = {
		id:-1,
		title : taskTitle
	}
	let formData = JSON.stringify(data)
	console.log(formData)
	fetch("task", {method:"POST", body:formData}).then(function (response) {
		if (response.ok) {
			response.json().then(function (jsonData) {
				addTask(jsonData.id, taskTitle)
			})
		} else {
			alert("Failed to add task")
		}
	})
}

function removeTask(id, liElement) {
	console.log("In Request")
	fetch("task?id="+id, {method:"delete"}).then(function (response) {
		console.log(response.status)
		if (response.ok) {
			liElement.remove()
		} else {
			console.log("Failed to remove");
		}
	}).catch(function (error) {
		console.log(error.message);
	})
}

function addTask(taskId, text) {
	let task = {
		id : taskId,
		title : text
	}
	addTaskToList(task)
}

// adds task {id, title} to list
function addTaskToList(task) {
	let liElement = document.createElement("li")
	liElement.id = task.id
	let textElement = document.createTextNode(task.title)
	let button = document.createElement("button")
	button.innerText = "Remove"
	liElement.appendChild(textElement)
	liElement.appendChild(button)
	
	button.addEventListener("click", function () {
		removeTask(this.parentNode.id, this.parentNode)
	})
	document.getElementById("todo-list").appendChild(liElement)
}

function fetchAllTasks() {
	fetch("/task").then(function (response) {
		response.json().then(function (taskList) {
				for (let i = 0;i < taskList.length;i++) {
				addTask(taskList[i].id, taskList[i].title);
			}
		})
	}).catch(function (error) {
		console.log(error.message);
	});
}

function getAllTasks() {
	fetchAllTasks()
}

