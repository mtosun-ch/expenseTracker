let expenseList = document.getElementById("expense-list");
let expenseForm = document.getElementById("expense-form");

// set init date to today's date
let date = new Date();
let currDate = date.getFullYear() + "-" + (date.getMonth() + 1).toString().padStart(2, 0) + "-" + date.getDate().toString().padStart(2, 0);
document.getElementById("date").value = currDate;

// Load at initialization
fetch('http://localhost:8080/api/expenses')
.then(response => response.json())
.then(data => {
    for (let i = 0; i < data.length; i++) {
        let currElement = document.createElement("li");
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
expenseForm.addEventListener("submit", (event) => {
    event.preventDefault();
    let descriptionValue = document.getElementById("description").value;
    if (descriptionValue.length > 100) {
        alert("Maximum length of 100 characters exceeded.")
        throw new Error("Maximum length of 100 characters exceeded.");
    }
    let amountValue = document.getElementById("amount").value;
    if (amountValue < 0) {
        alert("Negative values are not allowed.");
        throw new Error("Negative values are not allowed.");
    }
    let categoryValue = document.getElementById("category").value;
    let dateValue = document.getElementById("date").value;

    fetch('http://localhost:8080/api/expenses', {
        method: 'POST',
        headers: {'Content-type': 'application/json'},
        body: JSON.stringify({amount: amountValue, description: descriptionValue, category: categoryValue, date: dateValue})
    })
    .then( () => loadExpense());
});

