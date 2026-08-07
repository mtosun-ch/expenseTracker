let expenseList = document.getElementById("expense-list");
let expenseForm = document.getElementById("expense-form");
let yearlyList = document.getElementById("yearly-list");
let monthlyList = document.getElementById("monthly-list");

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

// Load mapping at initialization
fetch('http://localhost:8080/api/account/yearlyMapping')
    .then(response => response.json())
    .then(data => {
        let map = Object.entries(data);
        map.forEach(element => {
            let year = element[0];
            let total = element[1];
            let currElement = document.createElement("li");
            currElement.textContent = year + ": " + total;
            yearlyList.appendChild(currElement);
            let set = 0;
            currElement.addEventListener("click", () => {
                if (set == 0) {
                    set = 1;
                    loadMappings(year);
                }
            })
        });
    });

// Load all mappings from the data
function loadMappings(year) {
    fetch('http://localhost:8080/api/account/monthlyMapping?year=' + year)
        .then(reponse => reponse.json())
        .then(data => {
            let monthMap = Object.entries(data);
            monthMap.forEach(element => {
                let month = element[0];
                let total = element[1];
                let monthlyElement = document.createElement("li");
                monthlyElement.textContent = month + ": " + total;
                monthlyList.appendChild(monthlyElement);
            })
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
    if (dateValue > currDate) {
        alert("Dates in the future are not allowed");
        throw new Error("Dates in the future are not allowed");
    }

    fetch('http://localhost:8080/api/expenses', {
        method: 'POST',
        headers: { 'Content-type': 'application/json' },
        body: JSON.stringify({ amount: amountValue, description: descriptionValue, category: categoryValue, date: dateValue })
    })
        .then(() => loadExpense());
});


// Testing
// fetch('http://localhost:8080/api/account/monthlyMapping?year=2025')
//     .then(response => response.json())
//     .then(data => console.log(data))