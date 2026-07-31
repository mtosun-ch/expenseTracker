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