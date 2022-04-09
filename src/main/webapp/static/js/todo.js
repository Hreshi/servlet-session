let taskId = 1

document.getElementById("add-todo-button").addEventListener("click", sendAddTodoRequest)
function listener() {
	let todoList = document.getElementsByTagName("li");
	for (let i = 0;i < todoList.length;i++) {
		todoList[i].addEventListener ("click", function (element) {
			this.remove()
		})
	}
}
function sendAddTodoRequest() {
	let task = {
		id : taskId,
		title : document.getElementById("todo-input").value
	}
	taskId += 1
	addTodoToList(task)
}
function sendDeleteTodoRequest(element) {
	deleteTodoFromList(element)
}

function addTodoToList(task) {
	document.getElementById("todo-list").append(createLiElement(task))
	listener()
}
function deleteTodoFromList(element) {
	let li = element.parentNode
	li.remove()
}

function createLiElement(task) {
	let element = document.createElement("li")
	element.id = "task:" + task.id
	element.innerHTML = task.title + '<button>Delete</button>'
	return element
}