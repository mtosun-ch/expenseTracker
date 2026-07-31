let expenseList = document.getElementById("expense-list");

// Load at initialization
fetch('http://localhost:8080/api/expenses')
.then(response => response.json())
.then(data => {
    for (let i = 0; i < data.length; i++) {
        let currElement = document.createElement("li");
        if (expenseList.contains(currElement)) {
            continue;
        }
        currElement.textContent = data[i].description;
        expenseList.appendChild(currElement);
    }
});

// Load all entries of data
function loadExpenses() {
    fetch('http://localhost:8080/api/expenses')
    .then(response => response.json())
    .then(data => {
        for (let i = 0; i < data.length; i++) {
            let currElement = document.createElement("li");
            if (expenseList.contains(currElement)) {
                continue;
            }
            currElement.textContent = data[i].description;
            expenseList.appendChild(currElement);
        }
    });
}

// Load last entry of data
function loadExpense() {
    fetch('http://localhost:8080/api/expenses')
    .then(response => response.json())
    .then(data => {
        let currElement = document.createElement("li");
        currElement.textContent = data[data.length - 1].description;
        expenseList.appendChild(currElement);
    })
}

// Add entry to database
let expenseForm = document.getElementById("expense-form");
expenseForm.addEventListener("submit", (event) => {
    event.preventDefault();
    let descriptionValue = document.getElementById("description").value;
    let amountValue = document.getElementById("amount").value;
    let categoryValue = document.getElementById("category").value;

    fetch('http://localhost:8080/api/expenses', {
        method: 'POST',
        headers: {'Content-type': 'application/json'},
        body: JSON.stringify({amount: amountValue, description: descriptionValue, category: categoryValue})
    })
    .then( () => loadExpense());
});