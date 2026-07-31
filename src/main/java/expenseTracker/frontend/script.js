let expenseList = document.getElementById("expense-list");
fetch('http://localhost:8080/api/expenses')
    .then(response => response.json())
    .then(data => {
        for (let i = 0; i < data.length; i++) {
            let currElement = document.createElement("li");
            currElement.textContent = data[i].description;
            expenseList.appendChild(currElement);
        }
    });

let expenseForm = document.getElementById("expense-form");
let descriptionValue; let amountValue; let categoryValue;
expenseForm.addEventListener("submit", (event) => {
    descriptionValue = document.getElementById("description").value;
    amountValue = document.getElementById("amount").value;
    categoryValue = document.getElementById("category").value;
});

fetch('http://localhost:8080/api/expenses', {
    method: 'POST',
    headers: {'Content-type': 'application/json'},

})