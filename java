<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>To-Do List App</title>

<style>
*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:Arial, sans-serif;
}

body{
    background:#f4f4f4;
    display:flex;
    justify-content:center;
    align-items:center;
    min-height:100vh;
}

.container{
    width:500px;
    background:white;
    padding:20px;
    border-radius:10px;
    box-shadow:0 0 10px rgba(0,0,0,0.2);
}

h1{
    text-align:center;
    margin-bottom:20px;
}

.input-section{
    display:flex;
    gap:10px;
}

input{
    flex:1;
    padding:10px;
    border:1px solid #ccc;
    border-radius:5px;
}

button{
    padding:10px 15px;
    border:none;
    cursor:pointer;
    border-radius:5px;
}

.add-btn{
    background:#28a745;
    color:white;
}

.filters{
    display:flex;
    justify-content:center;
    gap:10px;
    margin:20px 0;
}

.filter-btn{
    background:#007bff;
    color:white;
}

ul{
    list-style:none;
}

li{
    display:flex;
    justify-content:space-between;
    align-items:center;
    padding:10px;
    margin-bottom:10px;
    background:#f9f9f9;
    border-radius:5px;
}

.completed{
    text-decoration:line-through;
    color:gray;
}

.task-buttons button{
    margin-left:5px;
}

.edit{
    background:orange;
    color:white;
}

.delete{
    background:red;
    color:white;
}

.complete{
    background:green;
    color:white;
}
</style>
</head>

<body>

<div class="container">
    <h1>To-Do List</h1>

    <div class="input-section">
        <input type="text" id="taskInput" placeholder="Enter task">
        <button class="add-btn" id="addTask">Add</button>
    </div>

    <div class="filters">
        <button class="filter-btn" data-filter="all">All</button>
        <button class="filter-btn" data-filter="active">Active</button>
        <button class="filter-btn" data-filter="completed">Completed</button>
    </div>

    <ul id="taskList"></ul>
</div>

<script>

let tasks = JSON.parse(localStorage.getItem("tasks")) || [];
let currentFilter = "all";

const taskInput = document.getElementById("taskInput");
const addTaskBtn = document.getElementById("addTask");
const taskList = document.getElementById("taskList");

function saveTasks() {
    localStorage.setItem("tasks", JSON.stringify(tasks));
}

function renderTasks() {

    taskList.innerHTML = "";

    let filteredTasks = tasks.filter(task => {

        if(currentFilter === "active")
            return !task.completed;

        if(currentFilter === "completed")
            return task.completed;

        return true;
    });

    filteredTasks.forEach(task => {

        const li = document.createElement("li");

        li.innerHTML = `
            <span class="${task.completed ? 'completed' : ''}">
                ${task.text}
            </span>

            <div class="task-buttons">
                <button class="complete" data-id="${task.id}">
                    ${task.completed ? "Undo" : "Done"}
                </button>

                <button class="edit" data-id="${task.id}">
                    Edit
                </button>

                <button class="delete" data-id="${task.id}">
                    Delete
                </button>
            </div>
        `;

        taskList.appendChild(li);
    });
}

function addTask() {

    const text = taskInput.value.trim();

    if(text === "") {
        alert("Enter a task");
        return;
    }

    tasks.push({
        id: Date.now(),
        text: text,
        completed: false
    });

    saveTasks();
    renderTasks();

    taskInput.value = "";
}

addTaskBtn.addEventListener("click", addTask);

taskInput.addEventListener("keypress", function(e){
    if(e.key === "Enter"){
        addTask();
    }
});

taskList.addEventListener("click", function(e){

    const id = Number(e.target.dataset.id);

    if(e.target.classList.contains("delete")){

        tasks = tasks.filter(task => task.id !== id);

    }

    else if(e.target.classList.contains("complete")){

        tasks = tasks.map(task =>
            task.id === id
            ? {...task, completed: !task.completed}
            : task
        );

    }

    else if(e.target.classList.contains("edit")){

        const task = tasks.find(task => task.id === id);

        let updatedText = prompt("Edit Task", task.text);

        if(updatedText && updatedText.trim() !== ""){

            task.text = updatedText.trim();
        }
    }

    saveTasks();
    renderTasks();
});

document.querySelectorAll(".filter-btn").forEach(button => {

    button.addEventListener("click", function(){

        currentFilter = this.dataset.filter;

        renderTasks();
    });

});

renderTasks();

</script>

</body>
</html>
