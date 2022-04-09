let taskId = 1
document.getElementById("add-todo-button").addEventListener("click",function () {
	let todoInput = document.getElementById("todo-input")
	let task = todoInput.value
	todoInput.value = ""
	addTask(task)
})

function addTask(text) {
	let task = {
		id : taskId,
		title : text
	}
	taskId += 1
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
		this.parentNode.remove()
	})
	document.getElementById("todo-list").appendChild(liElement)
}